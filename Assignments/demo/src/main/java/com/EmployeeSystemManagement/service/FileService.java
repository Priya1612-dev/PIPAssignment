package com.EmployeeSystemManagement.service;

import com.jcraft.jsch.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
            session.setConfig("StrictHostKeyChecking", "no");
            //session.setTimeout(15000);
            session.connect();
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            System.out.println(channel.pwd());
            System.out.println(channel.getHome());
            System.out.println(channel.isConnected());
           //channel.cd("Documents");
            channel.cd(sftpDirectory);
            channel.get(fileName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            channel.get(fileName, outputStream);
            String fileContent = outputStream.toString();
            channel.disconnect();
            session.disconnect();
            return fileContent;
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            JSch jsch = new JSch();
//            Session session = jsch.getSession(sftpUsername, sftpHost, sftpPort);
//            session.setPassword(sftpPassword);
//            session.setConfig(config);
//            session.connect();
//            System.out.println("Connected");
//
//            Channel channel = session.openChannel("exec");
//            ((ChannelExec) channel).setCommand("ls -ltr");
//            channel.setInputStream(null);
//            ((ChannelExec) channel).setErrStream(System.err);
//
//            InputStream in = channel.getInputStream();
//            channel.connect();
//            byte[] tmp = new byte[1024];
//            while (true) {
//                while (in.available() > 0) {
//                    int i = in.read(tmp, 0, 1024);
//                    if (i < 0) break;
//                    System.out.print(new String(tmp, 0, i));
//                }
//                if (channel.isClosed()) {
//                    System.out.println("exit-status: " + channel.getExitStatus());
//                    break;
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception ee) {
//                }
//            }
//            channel.disconnect();
//            session.disconnect();
//            System.out.println("DONE");



        } catch (JSchException e) {
            throw new RuntimeException(e);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }


    }

}




  //  }
//    protected void configure(OpenSshConfig.Host hc, Session session) {
//        if (!GitMonitoringService.this.strictHostKeyCheckingEnabled) {
//            session.setConfig("StrictHostKeyChecking", "no");
//        }
//    }
//}