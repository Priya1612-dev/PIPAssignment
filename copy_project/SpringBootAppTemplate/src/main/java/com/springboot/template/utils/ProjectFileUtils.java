package com.springboot.template.utils;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.springboot.template.exceptions.ZipDownloadFailedException;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.springboot.template.entities.ProjectInput;

@Component
public class ProjectFileUtils {
    private ProjectFileUtils() {
    }
    public static void copyFileUsingStream(String source, String dest) throws IOException {
        Path sourcePath=Paths.get(source);
        Path destinationPath=Paths.get(dest);
        if(!Files.exists(destinationPath)){
            Files.createDirectories(destinationPath);
        }
        try {
            Files.walk(sourcePath).forEach(sources -> {
                try {
                    Path targetPath = destinationPath.resolve(sourcePath.relativize(sources));
                    if (Files.isDirectory(sources)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.copy(sources, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    new ZipDownloadFailedException("Failed to copy file");
                }
            });
        }finally {
            Files.walk(sourcePath).close();
        }}

    public static void updateXMLElementValue(String filePath, ProjectInput input)
            throws TransformerFactoryConfigurationError, TransformerException, DOMException {

        File xmlFile = new File(filePath);
       DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            editPom(doc, "groupId", input.getGroupId());
            editPom(doc, "artifactId", input.getArtifactId());
            editPom(doc, "name", input.getName());
            editPom(doc, "description", input.getDescription());
            editPom(doc, "packaging", input.getPackaging());
            Set<String> dependencyInput = input.getDependency();

            updateDependency(doc, dependencyInput);

            writeXMLFile(doc, filePath);
        } catch (SAXException | ParserConfigurationException |

                 IOException e1) {
            e1.printStackTrace();
        }

    }

    private static void updateDependency(Document doc, Set<String> dependencyInput) {
        Map<String, List<String>> map = MapUtil.getDependencyMap();
        for (String inputDependency : dependencyInput) {
            NodeList dependencies = doc.getElementsByTagName("dependencies");
            switch (inputDependency) {
                case StringConstants.SPRING_WEB ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.SPRING_WEB, StringConstants.SPRING_WEB);
                case StringConstants.H2_DATABASE ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.H2_DATABASE, StringConstants.H2_DATABASE);
                case StringConstants.LOMBOK ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.LOMBOK, StringConstants.LOMBOK);
                case StringConstants.VALIDATION ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.VALIDATION, StringConstants.VALIDATION);
                case StringConstants.MYSQL_DRIVER ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.MYSQL_DRIVER, StringConstants.MYSQL_DRIVER);
                case StringConstants.SPRING_DATA_JDBC ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.SPRING_DATA_JDBC, StringConstants.SPRING_DATA_JDBC);
                case StringConstants.SPRING_DATA_JPA ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.SPRING_DATA_JPA, StringConstants.SPRING_DATA_JPA);
                case StringConstants.SPRING_SECURITY ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.SPRING_SECURITY, StringConstants.SPRING_SECURITY);
                case StringConstants.THYMELEAF ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.THYMELEAF, StringConstants.THYMELEAF);
                case StringConstants.SPRING_GRAPHQL ->
                        dependencyTagCreation(doc, map, dependencies, StringConstants.SPRING_GRAPHQL, StringConstants.SPRING_GRAPHQL);
                default->
                    dependencyTagCreation(doc, map, dependencies, null, null);

                 }
        }
    }

    private static void editPom(Document doc, String artifactId, String input) {
        NodeList name = doc.getElementsByTagName(artifactId);
        if (name.getLength() > 0) {
            Element artifactIdElement = (Element) name.item(0);
            artifactIdElement.setTextContent(input);
        }
    }

    private static void dependencyTagCreation(Document doc, Map<String, List<String>> map, NodeList dependencies,String artifactId,String groupId) {
        Element dependency = doc.createElement("dependency");
        Element dependenciesElement = (Element) dependencies.item(0);
        dependenciesElement.appendChild(dependency);
        Element groupIdOfDependency = doc.createElement("groupId");
        Element artifactIdOfDependency = doc.createElement("artifactId");
        dependency.appendChild(groupIdOfDependency);
        dependency.appendChild(artifactIdOfDependency);
        String groupIdValue = map.get(groupId).get(0);
        String artifactIdValue = map.get(artifactId).get(1);
        groupIdOfDependency.setTextContent(groupIdValue);
        artifactIdOfDependency.setTextContent(artifactIdValue);
    }

    public static void writeXMLFile(Document doc, String filePath)
            throws TransformerFactoryConfigurationError,TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public static void createzipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        FileInputStream fis = new FileInputStream(fileToZip);
        try{
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
                        createzipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                    }
                    return;
                }

                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }

        }
    finally {
            fis.close();
        }
    }

    public static void mainMethodReplace(String filePath, String inputValue) {

        Path path = Paths.get(filePath);
        try (
                Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            List<String> list = stream.map(line -> line.replace
                            ("TicketApplication", inputValue + "Application"))
                    .toList();
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
