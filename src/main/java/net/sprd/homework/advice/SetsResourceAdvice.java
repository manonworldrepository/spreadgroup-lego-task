package net.sprd.homework.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import net.sprd.homework.response.ValidationResponse;

import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class SetsResourceAdvice {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
	
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ValidationResponse> handleYearValidationErrorMessage(Exception e, BindingResult result) throws Exception {
		if (result.hasFieldErrors("year")) {
			Iterator<FieldError> errors = result.getFieldErrors("year").iterator();
			
			while(errors.hasNext()) {
				FieldError currentError = errors.next();
				
				for (String errorCode : currentError.getCodes()) {
					if (errorCode.equals("typeMismatch.year")) {						
						return new ResponseEntity<ValidationResponse>(
							new ValidationResponse(
								400, 
								"Parameter 'year' was supplied with invalid value '" + result.getFieldValue("year") + "'"
							), 
							HttpStatus.BAD_REQUEST
						);
					}
				}
			}
		}
		
		throw e;
	}

}


