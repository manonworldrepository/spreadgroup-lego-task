package net.sprd.homework.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.sqlite.SQLiteException;

@ControllerAdvice
public class FavoritesResourceAdvice {
	
	@ExceptionHandler(SQLiteException.class)
	public ResponseEntity<?> handleDuplicateFavoriteEntryException(Exception e) throws Exception {
		if (e.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		
		throw e;
	}

}
