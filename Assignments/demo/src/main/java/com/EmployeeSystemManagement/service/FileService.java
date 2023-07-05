package com.EmployeeSystemManagement.service;

import com.jcraft.jsch.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayOutputStream;
@Service
public class FileService {
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

    public String readFileFromSftp(String fileName) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(sftpUsername, sftpHost, sftpPort);
            session.setPassword(sftpPassword);
            //configure();
            session.setTimeout(15000);
            session.connect();
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            channel.cd(sftpDirectory);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            channel.get(fileName, outputStream);
            String fileContent = outputStream.toString();
            channel.disconnect();
            session.disconnect();
            return fileContent;


        } catch (JSchException e) {
            throw new RuntimeException(e);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }
//    protected void configure(OpenSshConfig.Host hc, Session session) {
//        if (!GitMonitoringService.this.strictHostKeyCheckingEnabled) {
//            session.setConfig("StrictHostKeyChecking", "no");
//        }
//    }
}