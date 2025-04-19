package io.andresgois.webflux.controller.exceptions;


import io.andresgois.webflux.service.exception.ObjectNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExcetionHandle {

	@ExceptionHandler(DuplicateKeyException.class)
	ResponseEntity<Mono<StandardError>> handleDuplicateKeyException(DuplicateKeyException ex,
																	ServerHttpRequest request
	) {
		return ResponseEntity.badRequest()
				.body(Mono.just(
						StandardError.builder()
								.timestamp(now())
								.status(BAD_REQUEST.value())
								.error(BAD_REQUEST.getReasonPhrase())
								.message(verifyDupKey(ex.getMessage()))
								.path(request.getPath().toString())
								.build()
				));
	}

	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<Mono<ValidationError>> validationError(
			WebExchangeBindException ex, ServerHttpRequest request
	) {
		ValidationError error = new ValidationError(
				LocalDateTime.now(), request.getPath().toString(),
				BAD_REQUEST.value(), "Validation error", "Error on validation attributes"
		);

		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just(error));
	}

	private String verifyDupKey(String message) {
		if (message.contains("indice_email_unico dup key")) {
			return "E-mail already registered";
		}
		return "Dup key exception";
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	ResponseEntity<Mono<StandardError>> objectNotFoundException(ObjectNotFoundException ex,
																	ServerHttpRequest request
	) {
		return ResponseEntity.status(NOT_FOUND)
				.body(Mono.just(
						StandardError.builder()
								.timestamp(now())
								.status(NOT_FOUND.value())
								.error(NOT_FOUND.getReasonPhrase())
								.message(ex.getMessage())
								.path(request.getPath().toString())
								.build()
				));
	}
}

