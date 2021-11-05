package ru.vsb.calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.vsb.calculator.model.Request;
import ru.vsb.calculator.model.Response;
import ru.vsb.calculator.service.CalculatorService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RequestControllerTest {
    @Autowired
    CalculatorService calculatorService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addTest() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.
               post("/calculator/add")
               .content(objectMapper.writeValueAsString(new Request(1, 2)))
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(3));
    }

    @Test
    public void subtractTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        post("/calculator/subtract")
                        .content(objectMapper.writeValueAsString(new Request(1, 2)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(-1));
    }

    @Test
    public void multiplyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        post("/calculator/multiply")
                        .content(objectMapper.writeValueAsString(new Request(2, 2)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(4));
    }


    @Test
    public void divideTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        post("/calculator/divide")
                        .content(objectMapper.writeValueAsString(new Request(4, 2)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(2));
    }


}