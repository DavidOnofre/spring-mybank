package com.kodigo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodigo.model.Client;
import com.kodigo.util.Constant;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientControllerTest {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("Successful test findAll")
    @Test
    @Order(1)
    void findAll() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(Constant.CLIENTS)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    @DisplayName("Successful test findById")
    @Test
    @Order(2)
    void findById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(Constant.CLIENTS)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapToJson(getClient())))
                .andReturn();

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(Constant.CLIENTS + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.delete(Constant.CLIENTS + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    @DisplayName("Successful test deleteById")
    @Test
    @Order(3)
    void deleteById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(Constant.CLIENTS)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapToJson(getClient())))
                .andReturn();

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(Constant.CLIENTS + "/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(204, mockMvcResult.getResponse().getStatus());
    }

    @DisplayName("Successful test save")
    @Test
    @Order(4)
    void save() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post(Constant.CLIENTS)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapToJson(getClient())))
                .andReturn();

        assertEquals(201, mockMvcResult.getResponse().getStatus());
    }

    @DisplayName("Successful test edit")
    @Test
    @Order(5)
    void edit() throws Exception {

        Client client = getClient();
        client.setIdClient(3);
        client.setName("Dario Onofre");

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.put(Constant.CLIENTS)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapToJson(client)))
                .andReturn();

        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    private String mapToJson(Client client) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(client);
    }

    private Client getClient() {
        Client client = new Client();
        client.setUserClient("kodigo10");
        client.setPasswordClient("123456780");
        client.setStateClient(true);
        client.setName("David Onofre");
        client.setGender("M");
        client.setAge(34);
        client.setIdentification("1719382980");
        client.setAddress("Tambo del Inca");
        client.setPhone("0988555596");
        return client;
    }

}