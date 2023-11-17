package ru.terentyev.t1testtask.exceptions;

import java.io.IOException;

public class MissingAnalizeKeyException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingAnalizeKeyException(String message) {
		super(message);
	}
}
