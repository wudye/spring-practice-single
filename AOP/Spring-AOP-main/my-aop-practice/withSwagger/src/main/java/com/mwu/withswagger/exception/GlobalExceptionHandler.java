

package com.mwu.withswagger.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mwu.withswagger.model.ErrorResponseDTO;



import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// When an InvalidInputException is thrown, the InvalidWalletBalanceException method is invoked.
	// This method constructs an ErrorResponseDTO object, which contains details about the error,
	// such as the timestamp, status code, reason phrase, exception message, and the request URI.
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ com.mwu.withswagger.exception.InvalidInputException.class })
	public ErrorResponseDTO InvalidWalletBalanceException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

}