package ru.terentyev.t1testtask.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ru.terentyev.t1testtask.exceptions.MissingAnalizeKeyException;
import ru.terentyev.t1testtask.services.AnalysisService;

/**
 * Программа принимает POST запрос по адресу '/analize' с JSON'ом ключ 'analize' и строку, которую нужно проанализировать, в качестве значения ключа,
 * например {"analize":"daabbbbccc"} и возвращает JSON, в котором ключами выступают встретившиеся хотябы один раз в строке символы,
 * а значениями - количество вхождений этих символов в строке. Сортировка осуществлена в порядке убывания вхождений.
 * Отправить такой запрос можно через программу Postman.
 * @author Терентьев Дмитрий
 */

@RestController
@RequestMapping
public class AnalysisController {

	private AnalysisService analysisService;
	

	@Autowired
	public AnalysisController(AnalysisService analysisService) {
		super();
		this.analysisService = analysisService;
	}
	
	/**
	 * Метод, принимающий от клиента JSON для анализа строки. 
	 * Передает принятый JSON сервису для анализа.
	 * @param json принятый от клиента JSON
	 * @return JSON с результатами анализа строки
	 * @throws IOException в случае невалидности переданного JSON'а, например отсутствие ключа 'analize'
	 */
	@PostMapping("/analize")
	public String takeEntriesCount(@RequestBody String json) throws IOException {			
			return analysisService.takeEntriesCount(json);	
	}
	
	@ExceptionHandler
	private ResponseEntity<String> handleException(IOException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
