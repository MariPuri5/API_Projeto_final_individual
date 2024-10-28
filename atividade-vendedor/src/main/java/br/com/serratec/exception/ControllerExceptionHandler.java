package br.com.serratec.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
			erros.add(erro.getField() + ": " + erro.getDefaultMessage());

		}
		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos inválidos", LocalDateTime.now(),
				erros);
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
	
	

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
	}
	
	//usa essa no lugar do @Override quando a classe ainda não existe 
	//e tem que criar do zero, como nesse caso do email
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());
		ErroResposta erroResposta = new ErroResposta(HttpStatus.NOT_FOUND.value(), 
				"Não encontrado", LocalDateTime.now(), erros);		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta); //só funcionou com o (bodybuilder).body();
	}
}
