package com.springboot.template.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.template.entities.ProjectInput;
import com.springboot.template.utils.ProjectFileUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

		fileUtil.updateXMLElementValue("Files\\template\\template\\pom.xml", projectInput);

		String sourceFile = "Files\\template";
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

	}

}
