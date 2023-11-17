package ru.terentyev.t1testtask.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public interface AnalysisService {
	String takeEntriesCount(String json) throws IOException;
}
