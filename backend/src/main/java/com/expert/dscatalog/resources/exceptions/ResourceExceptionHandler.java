package com.expert.dscatalog.resources.exceptions;

import com.expert.dscatalog.services.exceptions.DatabaseException;
import com.expert.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.resources.exceptions.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler( ResourceNotFoundException.class )
    public ResponseEntity<StandardError> entityNotFound(
            ResourceNotFoundException e, HttpServletRequest request ){

        StandardError err = new StandardError();
        err.setTimeStamp( Instant.now() );
        err.setStatus( HttpStatus.NOT_FOUND.value() );
        err.setError( "Resource Not Found" );
        err.setMessage( e.getMessage() );
        err.setPath( request.getRequestURI() );

        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( err );
    }

    @ExceptionHandler( DatabaseException.class )
    public ResponseEntity<StandardError> database(
            DatabaseException e, HttpServletRequest request ){

        StandardError err = new StandardError();
        err.setTimeStamp( Instant.now() );
        err.setStatus( HttpStatus.BAD_REQUEST.value() );
        err.setError( "Database Exception - Can't delete category, leaving products without one" );
        err.setMessage( e.getMessage() );
        err.setPath( request.getRequestURI() );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( err );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<StandardError> validation(
            MethodArgumentNotValidException e, HttpServletRequest request ){

        ValidationError err = new ValidationError();
        err.setTimeStamp( Instant.now() );
        err.setStatus( HttpStatus.UNPROCESSABLE_ENTITY.value() );
        err.setError( "Validation Exception" );
        err.setMessage( e.getMessage() );
        err.setPath( request.getRequestURI() );

        for ( FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status( HttpStatus.UNPROCESSABLE_ENTITY ).body( err );
    }
}