package com.EmployeeSystemManagement.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

        Logger logger = LoggerFactory.getLogger(testController.class);

        @RequestMapping("/test")
        public String test() {
            this.logger.warn("This is working message");
            return "Testing message";
        }
}
