package com.EmployeeSystemManagement.controllers;

import org.example.SFTPConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {
    @Value("${SFTP_USERNAME}")
    private String sftpUsername;
    @Value("${SFTP_PORT}")
    private int sftpPort;
    @Value("${SFTP_PASSWORD}")
    private String sftpPassword;
    @Value("${SFTP_HOST}")
    private String sftpHost;
    @Value("${SFTP_DIRECTORY}")
    private String sftpDirectory;

    private SFTPConnect sftpConnect;
    @GetMapping("/readFileFromSftp")
    public ResponseEntity<String> readFileFromSftp(@RequestParam String fileName,
                                                   String sftpHost,String sftpUsername,
                                                    String sftpPassword,String sftpDirectory){
      String fileContent=sftpConnect.readFileFromSftp(sftpHost,sftpPort,sftpUsername,sftpPassword,sftpDirectory,fileName);
      return new ResponseEntity<>(fileContent,HttpStatus.OK);
    }

}
