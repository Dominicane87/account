package com.gorin.account.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.gorin.account.DTO.OperationDto;
import com.gorin.account.service.AccountService;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestController.class)
public class RestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;


    @org.junit.Test
    public void addOperation() throws Exception {
        String uri = "/operation";
        OperationDto operation = new OperationDto();
        operation.setIdAccount(1L);
        operation.setSumm(new BigDecimal(2));
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(operation);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, inputJson);
        Mockito.verify(service).addOperation(operation);
    }


}