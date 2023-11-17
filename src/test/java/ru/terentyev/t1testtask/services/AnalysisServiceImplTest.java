package ru.terentyev.t1testtask.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.terentyev.t1testtask.exceptions.MissingAnalizeKeyException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@SpringBootTest
public class AnalysisServiceImplTest {
	
	@Autowired
	private AnalysisServiceImpl analysisServiceImpl;
	
	
	@Test
	@DisplayName("Correct string as input must be analized correctly")
	public void givenCorrectInputString_whenEqual_thenCorrect() throws IOException  {
		assertEquals("{\"b\":5,\"a\":3,\"c\":2,\"d\":1}", analysisServiceImpl.takeEntriesCount("{\r\n"
				+ "    \"analize\" : \"daaabbbbbcc\"\r\n"
				+ "}"));
		assertThat("{\"b\":2,\"a\":4,\"c\":2,\"d\":1}", not(equalTo(analysisServiceImpl.takeEntriesCount("{\r\n"
				+ "    \"analize\" : \"daaabbbbbcc\"\r\n"
				+ "}"))));
		
	}
	
	@Test
	@DisplayName("Incorrect string as input must throw IOException")
	public void givenIncorrectInputString_mustThrowIOException() throws IOException {
		assertThrows(IOException.class, () -> analysisServiceImpl.takeEntriesCount(""));
		assertThrows(MissingAnalizeKeyException.class, () -> analysisServiceImpl.takeEntriesCount("{\r\n"
				+ "    \"check\" : \"daaabbbbbcc\"\r\n"
				+ "}"));
		
	}
}
