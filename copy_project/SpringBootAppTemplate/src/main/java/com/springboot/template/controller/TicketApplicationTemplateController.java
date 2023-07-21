package com.springboot.template.controller;

import java.io.IOException;
import java.util.Set;

import com.springboot.template.service.TicketApplicationTemplateService;
import com.springboot.template.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.template.entities.ProjectInput;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.TransformerException;

@RestController
public class TicketApplicationTemplateController {
    @Autowired
    private TicketApplicationTemplateService applicationTemplateService;

    @GetMapping("/template")
    public ResponseEntity<HttpStatus> downloadZipFile(@RequestBody ProjectInput projectInput, HttpServletResponse response) throws IOException, TransformerException {
        applicationTemplateService.downloadZipFile(projectInput, response);
       applicationTemplateService.deleteFileAfterZipDownload(projectInput);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listOfDependency")
    public ResponseEntity<Set<String>> getAllDependency() {
        return new ResponseEntity<>(MapUtil.getDependencyMap().keySet(), HttpStatus.OK);

    }


}
