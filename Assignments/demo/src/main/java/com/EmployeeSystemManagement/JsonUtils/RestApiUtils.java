//package com.EmployeeSystemManagement.JsonUtils;
//
//import com.EmployeeSystemManagement.DataMapper.Employee;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//
//import java.io.Closeable;
//import java.io.IOException;
//import java.net.http.HttpHeaders;
//import java.net.http.HttpResponse;
//import java.util.List;
//
//public class RestApiUtils {
//    public static void triggerPostCalls(String apiUrl, String authToken, List<Employee> employees){
//        for(Employee emp: employees){
//    try{
//        //String authToken=retrieveAuthTokenFromRedis();
//        //List<Employee> employees=JsonUtil.parseJsonFile("", Employee.class);
//        //RestApiUtils.triggerPostCalls(apiUrl,authToken,employees);
//        ObjectMapper objectMapper=new ObjectMapper();
//        String payload=objectMapper.writeValueAsString(emp);
//        CloseableHttpClient httpClient=createHttpClient(authToken);
//        HttpPost httpPost=new HttpPost(apiUrl);
//        StringEntity entity=new StringEntity(payload, ContentType.APPLICATION_JSON);
//        httpPost.setEntity(entity);
//        httpPost.setHeader(HttpHeaders.AUTHORIZATION,"Bearer"+authToken);
//
//
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
//        }
//    }
//
//    private String retrieveAuthTokenFromRedis(){
//        try(RedisProperties.Jedis jedis=new RedisProperties.Jedis("localhost",6379)){
//            return jedis.get("authToken");
//        }
//    }
//}
