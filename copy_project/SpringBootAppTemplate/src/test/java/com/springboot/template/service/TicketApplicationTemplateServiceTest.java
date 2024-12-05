package com.springboot.template.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.util.*;
import java.util.zip.ZipOutputStream;

import com.springboot.template.entities.ProjectInput;
import com.springboot.template.utils.MapUtil;
import com.springboot.template.utils.ProjectFileUtils;
import com.springboot.template.utils.StringConstants;
import jakarta.servlet.ServletOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.xml.transform.TransformerException;

@ExtendWith(SpringExtension.class)
public class TicketApplicationTemplateServiceTest {
        @Mock
        private MapUtil mapUtil;
        @Mock
        private ProjectFileUtils fileUtil;
        @Mock
        private StringConstants constants;

        @InjectMocks
        private TicketApplicationTemplateService templateService;







    }

