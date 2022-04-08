package com.dreampro.ws.exceptions;

import com.dreampro.ws.dtos.errors.ApiError;
import com.dreampro.ws.dtos.errors.ErrorResponse;
import com.dreampro.ws.utils.Messages;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<ApiError> errors = new ArrayList<ApiError>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      ApiError apiError = new ApiError(error.getDefaultMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
          HttpStatus.BAD_REQUEST.value());
      errors.add(apiError);
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      ApiError apiError = new ApiError(error.getDefaultMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
          HttpStatus.BAD_REQUEST.value());
      errors.add(apiError);
    }
    ErrorResponse errorResponse = new ErrorResponse("failed", errors);
    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    String error = ex.getParameterName() + " parameter is missing";

    ApiError apiError = new ApiError(error, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    ErrorResponse errorResponse = new ErrorResponse("failed", apiError);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    String error = ex.getLocalizedMessage();

    ex.printStackTrace();
    ApiError apiError = new ApiError(error, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    ErrorResponse errorResponse = new ErrorResponse("failed", apiError);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
    List<ApiError> errors = new ArrayList<ApiError>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      String err = violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
          + violation.getMessage();
      ApiError apiError = new ApiError(err, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
      errors.add(apiError);
    }

    ErrorResponse errorResponse = new ErrorResponse("failed", errors);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
      WebRequest request) {
    String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

    ApiError apiError = new ApiError(error, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());

    ErrorResponse errorResponse = new ErrorResponse("failed", apiError);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

    ApiError apiError = new ApiError(error, ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
    ErrorResponse errorResponse = new ErrorResponse("failed", apiError);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + ", "));

    ApiError apiError = new ApiError(builder.toString(), ex.getLocalizedMessage(),
        HttpStatus.METHOD_NOT_ALLOWED.value());
    ErrorResponse errorResponse = new ErrorResponse(Messages.FAILED, apiError);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

    ApiError apiError = new ApiError(builder.substring(0, builder.length() - 2), ex.getLocalizedMessage(),
        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    ErrorResponse errorResponse = new ErrorResponse("failed", apiError);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler({ EntitiesNotFoundException.class })
  public ResponseEntity<Object> handleEntitiesNotFoundException(EntitiesNotFoundException ex, WebRequest request) {
    ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value());
    ErrorResponse errorResponse = new ErrorResponse("failed", apiError);
    return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ex.printStackTrace();
    int code = 500;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String msg = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    String fullMsg = "";
    if (ex.getMessage() != null && !"".equals(ex.getMessage())) {
      fullMsg = ex.getMessage();
    } else {
      fullMsg = Messages.DEFAULT_ERROR_MESSAGE;
    }
    String statusmsg = Messages.FAILED;
    if (ex instanceof ValueDuplicateException || ex instanceof RequestValidationException) {
      code = 400;
      status = HttpStatus.BAD_REQUEST;
      msg = HttpStatus.BAD_REQUEST.getReasonPhrase();
    } else if (ex instanceof AccessDeniedException) {
      code = 401;
      status = HttpStatus.UNAUTHORIZED;
      msg = HttpStatus.UNAUTHORIZED.getReasonPhrase();
    }
    ApiError apiError = new ApiError(fullMsg, msg, code);
    ErrorResponse errorResponse = new ErrorResponse(statusmsg, apiError);
    return new ResponseEntity<>(errorResponse, new HttpHeaders(), status);
  }
}
