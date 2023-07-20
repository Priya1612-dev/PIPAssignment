package com.springboot.template.utils;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
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

import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.springboot.template.entities.ProjectInput;

@Component
public class ProjectFileUtils {

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        if (source.isDirectory()) {
            File newDest = new File(dest, source.getName());
            newDest.mkdirs();
            File[] files = source.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
                    copyFileUsingStream(file, newDest);
                }
            }
        } else {
            Path destPath = new File(dest, source.getName()).toPath();
            Files.copy(source.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void updateXMLElementValue(String filePath, ProjectInput input)
            throws TransformerFactoryConfigurationError, TransformerException, DOMException {

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
            Set<String> dependencyInput = input.getDependency();
            System.out.println(dependencyInput);
            Element groupIdOfDependency = null;
            Element artifactIdOfDependency = null;
            Element dependency;
            Element dependenciesElement;
            System.out.println(dependencyInput.size());

            Map<String, List<String>> map = MapUtil.getDependencyMap();
            for (String s : dependencyInput) {
                String groupIdValue;
                String artifactIdValue;
                NodeList dependencies = doc.getElementsByTagName("dependencies");
                switch (s) {
                    case "Spring Web":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Spring Web").get(0);
                        artifactIdValue = map.get("Spring Web").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);

                        break;
                    case "Spring GraphQL":
                        System.out.println(s);
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Spring GraphQL").get(0);
                        artifactIdValue = map.get("Spring GraphQL").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);

                        break;
                    case "Thymeleaf":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Thymeleaf").get(0);
                        artifactIdValue = map.get("Thymeleaf").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Spring Security":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Spring Security").get(0);
                        artifactIdValue = map.get("Spring Security").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Spring Data Jpa":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Spring Data Jpa").get(0);
                        artifactIdValue = map.get("Spring Data Jpa").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Spring Data JDBC":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Spring Data JDBC").get(0);
                        artifactIdValue = map.get("Spring Data JDBC").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "MySQL Driver":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("MySQL Driver").get(0);
                        artifactIdValue = map.get("MySQL Driver").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "H2 Database":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("H2 Database").get(0);
                        artifactIdValue = map.get("H2 Database").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Validation":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Validation").get(0);
                        artifactIdValue = map.get("Validation").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    case "Lombok":
                        dependency = doc.createElement("dependency");
                        dependenciesElement = (Element) dependencies.item(0);
                        dependenciesElement.appendChild(dependency);
                        groupIdOfDependency = doc.createElement("groupId");
                        artifactIdOfDependency = doc.createElement("artifactId");
                        dependency.appendChild(groupIdOfDependency);
                        dependency.appendChild(artifactIdOfDependency);
                        groupIdValue = map.get("Lombok").get(0);
                        artifactIdValue = map.get("Lombok").get(1);
                        groupIdOfDependency.setTextContent(groupIdValue);
                        artifactIdOfDependency.setTextContent(artifactIdValue);
                        break;
                    default:
                        break;
                }
            }

            writeXMLFile(doc, filePath);
        } catch (SAXException | ParserConfigurationException |

                 IOException e1) {
            e1.printStackTrace();
        }

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

    public static void mainMethodReplace(String filePath, String inputValue) {

        Path path = Paths.get(filePath);
        System.out.println("filePath :"+filePath);
        try (
                Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            List<String> list = stream.map(line -> line.replace
                            ("TicketApplication", inputValue + "Application"))
                    .collect(Collectors.toList());
            System.out.println("inputValue :"+inputValue);
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //		File mainMethodFile = new File(filePath);
//		File newFile=new File(inputValue);
//		String mainMethodName = "SpringBootTemplateApplication";
//		
//		try {
//			FileReader fileReader = new FileReader(mainMethodFile);	
//			String s;
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			while((s=bufferedReader.readLine())!=null) {
//				s.replaceAll(mainMethodName, inputValue);
//				mainMethodFile.renameTo(newFile);
//			}}
//			catch(Exception e) {
//				e.printStackTrace();
//		}
    }

}
