package com.mwu.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mwu.common.APIResponse;
import com.mwu.common.ApiResponseCodes;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public APIResponse<String> handleApiException(ApiException ex) {
        log.error("ApiException: {}", ex.getMessage());
        return new APIResponse<>(ApiResponseCodes.FAILED.getCode(), ApiResponseCodes.FAILED.getStatus(), ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public APIResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new APIResponse<>(ApiResponseCodes.FAILED.getCode(), ApiResponseCodes.FAILED.getStatus(), errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public APIResponse<String> handleGenericException(Exception ex) {
        log.error(ex.getMessage());
        return new APIResponse<>(ApiResponseCodes.FAILED.getCode(), ApiResponseCodes.FAILED.getStatus(), ex.getMessage());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public APIResponse<String> handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());
        return new APIResponse<>(ApiResponseCodes.FAILED.getCode(), ApiResponseCodes.FAILED.getStatus(), ex.getMessage());
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public APIResponse<String> handleValidationException(ValidationException ex) {
        log.error(ex.getMessage());
        return new APIResponse<>(ApiResponseCodes.FAILED.getCode(), ApiResponseCodes.FAILED.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(CurrencyMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public APIResponse<String> handleCurrencyMismatchException(CurrencyMismatchException ex) {
        log.error(ex.getMessage());
        return new APIResponse<>(ApiResponseCodes.FAILED.getCode(), ApiResponseCodes.FAILED.getStatus(), ex.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public APIResponse<String> handleHttpException(Exception ex) {
        log.error(ex.getMessage(), ex);
        String message = "";

        if (ex.getCause() != null && ex.getCause() instanceof InvalidFormatException) {
            final String regex = "Enum class: \\[([^\\]]+)\\];";
            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(ex.getMessage());

            if (matcher.find()) {
                log.debug("Full match: {}", matcher.group(0));
                String matchedGroupValue = matcher.group(1);
                log.debug("matchedGroupValue: {}", matchedGroupValue);

                message = "Supported Values are: " + matchedGroupValue;
            }
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            message = ex.getMessage();
        } else {
            message = "Type not supported";
        }

        return new APIResponse<>(message, "999", null);
    }



}
