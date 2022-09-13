package com.GabrielBorja.nttdataTest.Exception;

import org.springframework.http.HttpStatus;

public class ExceptionCodes {

	public static final String SUCCESS_CODE = "200.001";

	public static final String NO_CONTENT_CODE = "400.002";
	public static final String NO_CONTENT = "No CONTENT";
	public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

	public ExceptionCodes(String noContentCode, String noContent, HttpStatus badRequest) {
		super(
		);
	}


}
