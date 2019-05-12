package com.rmpksoft.aop.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionAdvice {

	@Value("${spring.http.multipart.max-file-size}")
	private String maxSize;

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> paramMissErrorHandler(MissingServletRequestParameterException e) throws Exception {
		Map<String, String> error = new HashMap<>();
		error.put("error", "message" + e.getParameterName() + "param");
		return error;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> illegalArgumentErrorHandler(IllegalArgumentException e) throws Exception {
		Map<String, String> message = new HashMap<>();
		message.put("error", e.getMessage());
		return message;
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> uploadErrorHandler(MultipartException e) throws Exception {
		Map message = new HashMap();
		if (e.getCause().getMessage()
				.contains("org.apache.tomcat.common.http.fileupload.FileUploadBase$FileSizeLimitExceededException")) {
			message.put("error", "Upload file" + maxSize);
		} else {
			message.put("error", "Commong error");
		}
		return message;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> requestHandlingNoHandlerFound() {
        Map message = new HashMap();
        message.put("error","router is not exists");
        return message;
    }


}
