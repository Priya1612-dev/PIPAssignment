//package com.EmployeeSystemManagement.JsonUtils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class JsonUtil {
//    private static final ObjectMapper objectMapper=new ObjectMapper();
//    public static <T> List<T> parseJsonFile(String filePath, Class<T> targetType) throws IOException {
//        String fileContent=readFileContent(filePath);
//        return objectMapper.readValue(fileContent,new TypeReference<List<T>>(){});
//    }
//    private  static String readFileContent(String filePath) throws IOException {
//        return Files.readString(Paths.get(filePath));
//    }
//}
