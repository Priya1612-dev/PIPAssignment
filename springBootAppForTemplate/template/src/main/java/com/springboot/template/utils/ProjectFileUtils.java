package com.springboot.template.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.springboot.template.entities.ProjectInput;

@Component
public class ProjectFileUtils {

	public static void updateXMLElementValue(String filePath, ProjectInput input)
			throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList groupId = doc.getElementsByTagName("groupId");
			if (groupId.getLength() > 0) {
				Element groupIdElement = (Element) groupId.item(0);
				groupIdElement.setTextContent(input.getGroupId());

			}
			NodeList artifactId = doc.getElementsByTagName("artifactId");
			if (artifactId.getLength() > 0) {
				Element artifactIdElement = (Element) artifactId.item(0);
				artifactIdElement.setTextContent(input.getArtifactId());

			}

			NodeList name = doc.getElementsByTagName("name");
			if (name.getLength() > 0) {
				Element nameElement = (Element) name.item(0);
				nameElement.setTextContent(input.getName());

			}

			NodeList description = doc.getElementsByTagName("description");
			if (description.getLength() > 0) {
				Element descriptionElement = (Element) description.item(0);
				descriptionElement.setTextContent(input.getDescription());

			}

			NodeList packaging = doc.getElementsByTagName("packaging");
			if (packaging.getLength() > 0) {
				Element packagingElement = (Element) packaging.item(0);
				packagingElement.setTextContent(input.getPackaging());

			}

			writeXMLFile(doc, filePath);

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

	}

	public static void writeXMLFile(Document doc, String filePath)
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		doc.getDocumentElement().normalize();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filePath));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
		System.out.println("XML file updated successfully");
	}

	public static void CreatezipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				CreatezipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}

}
