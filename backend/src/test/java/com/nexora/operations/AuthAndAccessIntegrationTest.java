package com.nexora.operations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class AuthAndAccessIntegrationTest {
  @Autowired MockMvc mvc;
  @Autowired ObjectMapper json;

  @Test void registrationCreatesReadOnlyUserWhoCanViewCustomers() throws Exception {
    String response = mvc.perform(post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
        .content("{\"displayName\":\"New User\",\"email\":\"new.user@example.test\",\"password\":\"Secure@12345\",\"confirmPassword\":\"Secure@12345\"}"))
        .andExpect(status().isOk()).andExpect(jsonPath("$.role").value("AUDITOR")).andReturn().getResponse().getContentAsString();
    JsonNode body = json.readTree(response);
    mvc.perform(get("/api/v1/customers").header("Authorization", "Bearer " + body.path("accessToken").asText()))
        .andExpect(status().isOk()).andExpect(jsonPath("$[0].customerNumber").value("CUST-001248"));
  }

  @Test void invalidRegistrationPasswordReturnsValidationProblem() throws Exception {
    mvc.perform(post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
        .content("{\"displayName\":\"Weak User\",\"email\":\"weak.user@example.test\",\"password\":\"weak\",\"confirmPassword\":\"weak\"}"))
        .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorCode").value("VALIDATION_ERROR"));
  }
}
