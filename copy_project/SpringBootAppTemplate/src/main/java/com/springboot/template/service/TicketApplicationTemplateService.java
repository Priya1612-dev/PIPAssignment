package com.springboot.template.service;

import com.springboot.template.entities.ProjectInput;
import com.springboot.template.utils.ProjectFileUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

@Service
public class TicketApplicationTemplateService {
    @Autowired
    private ProjectFileUtils fileUtil;

    @Value("${filepath.ticketApplication}")
    private String ticketAppFilepath;

    @Value("${filepath.sourceToCopy}")
    private String sourceToCopyFilepath;
    @Value("${filepath.projectTemplate}")
    private String projectTemplateFilepath;

    @Value("${filepath.projectTemplateTicketApp}")
    private String projectTemplateTicketAppFilepath;
    @Value("${filepath.srcMainJava}")
    private String srcMainJavaFilepath;

    @Value("${mainMethodFileName}")
    private String mainMethodFileName;

    @Value("${filepath.project}")
    private String projectFilePath;
    @Value("${fileExtension}")
    private String fileExtension;
    @Value("${fileDownloadFormat}")
    private String fileDownloadFormat;
    @Value("${fileToEdit}")
    private String fileToEdit;
    public void downloadZipFile(ProjectInput projectInput, HttpServletResponse response)
            throws IOException, TransformerFactoryConfigurationError,
            TransformerException {

        fileUtil.copyFileUsingStream(sourceToCopyFilepath, projectTemplateFilepath);

        File fileToRename = new File(projectTemplateTicketAppFilepath);
        fileToRename.renameTo(new File(projectFilePath+ projectInput.getArtifactId()));

        renameMainMethod(projectInput);

        renameClassName(projectInput);
        File fileToDelete = new File(projectTemplateFilepath,
                mainMethodFileName);
        fileToDelete.delete();

        fileUtil.updateXMLElementValue(projectFilePath+ projectInput.getArtifactId() + fileToEdit, projectInput);

        zipFileUpdate(projectInput, response);
    }

    private void zipFileUpdate(ProjectInput projectInput, HttpServletResponse response) throws IOException {
        String zipFile = projectInput.getArtifactId() + fileDownloadFormat;
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(projectFilePath+ projectInput.getArtifactId());
        fileUtil.createzipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
        setResponseAsZip(response, zipFile);
    }

    private static void setResponseAsZip(HttpServletResponse response, String zipFile) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + zipFile;
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(zipFile));
        try{
        byte[] buffer1 = new byte[8192];

        int byteReads = -1;

        while ((byteReads = inputStream.read(buffer1)) != -1) {

            outputStream.write(buffer1, 0, byteReads);

        }
        inputStream.close();
        outputStream.close();
    }finally{
       inputStream.close();
    }}

    private void renameClassName(ProjectInput projectInput) {
        try {
            String content = Files.lines(Paths.get(projectFilePath+ projectInput.getArtifactId()+
                            srcMainJavaFilepath+ projectInput.getArtifactId()+fileExtension))
                    .map(line -> line.replace(mainMethodFileName, projectInput.getArtifactId()))
                    .collect(Collectors.joining("\n"));
            Files.write(Paths.get(projectFilePath +
                    projectInput.getArtifactId() + srcMainJavaFilepath +
                    projectInput.getArtifactId() + fileExtension), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renameMainMethod(ProjectInput projectInput) {
        File mainMethodFileRename = new File(projectFilePath
                + projectInput.getArtifactId() + srcMainJavaFilepath, mainMethodFileName+fileExtension);
        File newFileName = new File(projectFilePath
                + projectInput.getArtifactId() + srcMainJavaFilepath, projectInput.getArtifactId() + fileExtension);
        mainMethodFileRename.renameTo(newFileName);
    }


    public void deleteFileAfterZipDownload(ProjectInput projectInput) throws IOException {
        File fileToDelete1 = new File(projectTemplateFilepath, (projectInput.getArtifactId()));
        FileUtils.deleteDirectory(new File(fileToDelete1.toURI()));
        File file = new File(projectInput.getArtifactId() + fileDownloadFormat);
        file.delete();
    }
}
