package com.springboot.template.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.template.entities.ProjectInput;
import com.springboot.template.utils.ProjectFileUtils;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	@Autowired
	private ProjectFileUtils fileUtil;

	@GetMapping("/template")
	public void downloadZipFile(@RequestBody ProjectInput projectInput, HttpServletResponse response)
			throws IOException, TransformerConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		String fileName = "Files\\downloads\\template\\TicketApplication\\src\\main\\java\\com\\hm\\TicketApplication.java";
		String newFileName = projectInput.getName();
		File sourceTemplateFile = new File("Files\\template");
		File destinationFile = new File("Files\\downloads");
		fileUtil.copyFileUsingStream(sourceTemplateFile, destinationFile);
		fileUtil.updateXMLElementValue("Files\\downloads\\template\\TicketApplication\\pom.xml", projectInput);

		String sourceFile = "Files\\downloads";
		String zipFile = projectInput.getArtifactId() + ".zip";

		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zipOut = new ZipOutputStream(fos);

		File fileToZip = new File(sourceFile);
		fileUtil.CreatezipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();

		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + zipFile;
		response.setHeader(headerKey, headerValue);
		ServletOutputStream outputStream = response.getOutputStream();
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(zipFile));
		byte[] buffer1 = new byte[8192];

		int byteReads = -1;

		while ((byteReads = inputStream.read(buffer1)) != -1) {

			outputStream.write(buffer1, 0, byteReads);

		}

		inputStream.close();
		outputStream.close();

		fileUtil.mainMethodReplace(fileName, newFileName);
		File file = new File(newFileName);
		File file1 = new File("Files\\downloads\\template\\TicketApplication\\src\\main\\java\\com\\hm\\ABCD.java");
		boolean exists = file.exists();
		System.out.println();
		boolean b = file.renameTo(file1);
		System.out.println(b);

	}


}
