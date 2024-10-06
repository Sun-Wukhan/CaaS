package com.yeswekhan.construction_service;

import com.yeswekhan.construction_service.dto.ConstructionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ConstructionServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void contextLoads() {

	}

	@Test
	void shouldCreateConstruction() throws Exception {

		ConstructionRequest constructionRequest = getConstructionRequest();
		String constructionRequestString = objectMapper.writeValueAsString(constructionRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/construction")
				.contentType(MediaType.APPLICATION_JSON)
				.content(constructionRequestString))
				.andExpect(status().isCreated());
	}

	private ConstructionRequest getConstructionRequest() {
		return ConstructionRequest.builder().name("100 Stink Road").description("Mock House").price(BigDecimal.valueOf(10000)).build();
	}

}
