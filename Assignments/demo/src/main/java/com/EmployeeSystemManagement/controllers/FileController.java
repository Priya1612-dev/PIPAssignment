package com.EmployeeSystemManagement.controllers;

import com.EmployeeSystemManagement.service.FileService;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
public class FileController {

    @Autowired
   private FileService fileService;
    @GetMapping("/readFileFromSftp")
    public ResponseEntity<String> readFileFromSftp(@RequestParam String fileName){
        String fileFromSftp=fileService.readFileFromSftp(fileName);
        return new ResponseEntity<>(fileFromSftp,HttpStatus.OK);
    }

}
