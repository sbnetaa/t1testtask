package ru.terentyev.t1testtask.controllers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.terentyev.t1testtask.services.AnalysisService;

@WebMvcTest(AnalysisController.class)
public class AnalysisControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AnalysisService analysisService;
	
	
	@Test
	@DisplayName("JSON request to service must not return null")
	public void givenStringAsInput_whenRequestToService_thenNotNull() throws IOException {
		String input = "{\r\n"
				+ "    \"analize\" : \"daaabbbbbcc\"\r\n"
				+ "}";
		
		Mockito.when(analysisService.takeEntriesCount(input)).thenReturn("{\"b\":5,\"a\":3,\"c\":2,\"d\":1}");
		
		assertNotNull(analysisService.takeEntriesCount(input));
		
		
	}
}
