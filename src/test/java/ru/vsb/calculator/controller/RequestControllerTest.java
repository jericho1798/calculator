package ru.vsb.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.vsb.calculator.service.CalculatorService;

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
    public void addTestWithNormalArguments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/calculator/add")
                        .param("firstArg", "1")
                        .param("secondArg", "2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    @Test
    public void addTestWithOneNonIntArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/calculator/add")
                        .param("firstArg", "a")
                        .param("secondArg", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("For input string: \"a\" argument must be a number!"));
    }

    @Test
    public void addTestWithNoArguments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/calculator/add")
                        .param("firstArg", "")
                        .param("secondArg", ""))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void subtractTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/calculator/subtract")
                        .param("firstArg", "1")
                        .param("secondArg", "2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("-1"));
    }

    @Test
    public void multiplyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/calculator/multiply")
                        .param("firstArg", "2")
                        .param("secondArg", "2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("4"));
    }


    @Test
    public void divideTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/calculator/divide")
                        .param("firstArg", "4")
                        .param("secondArg", "2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2"));
    }

    @Test
    public void divideByZeroTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/calculator/divide")
                        .param("firstArg", "4")
                        .param("secondArg", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Divide by 0!"));
    }

}