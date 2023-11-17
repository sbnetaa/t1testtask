package ru.terentyev.t1testtask.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.terentyev.t1testtask.exceptions.MissingAnalizeKeyException;

@Service
public class AnalysisServiceImpl implements AnalysisService {

	private ObjectMapper objectMapper;
	
	
	@Autowired
	public AnalysisServiceImpl(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}


/**
 * Метод анализирует строку на количество вхождений каждого символа.
 * Сначала он преобразует полученный JSON в Map.
 * Далее если Map не содержит ключ 'analize', выбрасывается исключение.
 * Затем метод посредством стрима посимвольно анализирует строку и собирает это все в другой неотсортированный TreeMap.
 * Вторая часть метода перебирает TreeMap посредством стрима и после сортировки собирает это все в отсортированный LinkedHashMap.
 * И далее преобразует этот LinkedHashMap в JSON для возврата из метода.
 * @param json Ввод для анализа.
 * @return JSON с проанализированной строкой.
 * @throws IOException если ввод некорректен, например отсутствует ключ 'analize'.
 */
	public String takeEntriesCount(String json) throws IOException {
		Map<String, String> mapFromJson = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});	
	if (!mapFromJson.containsKey("analize")) throw new MissingAnalizeKeyException("JSON does not contains key 'analize'");
	String toAnalize = mapFromJson.get("analize");
	LinkedHashMap<String, Long> map = Arrays.stream(toAnalize.split(""))
			.collect(Collectors.collectingAndThen(Collectors.groupingBy(e -> e, Collectors.counting())
					, m -> m.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					  .collect(Collectors.toMap(Map.Entry::getKey,
	                            Map.Entry::getValue,
	                            (e1, e2) -> e1, LinkedHashMap::new))));
	return objectMapper.writeValueAsString(map);
	}
}

