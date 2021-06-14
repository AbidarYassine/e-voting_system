package com.fstg.vote_electronique.client.exception;

import com.fstg.vote_electronique.client.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;


@ControllerAdvice
public class AppExceptionHandler {
    /*  Candidate Exception Debut */
    @ExceptionHandler(value = {CandidateNotFoundException.class})
    public ResponseEntity<Object> handlerCandidateException(CandidateNotFoundException ex, WebRequest req) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = {CandidateAlreadyExist.class})
    public ResponseEntity<Object> handlerCandidateAlreadyExistsException(CandidateAlreadyExist ex, WebRequest req) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);

    }
    /*  Candidate Exception FIN */

    /*  Voter Exception DEBUT */
    @ExceptionHandler(value = {VoterNotFoundException.class})
    public ResponseEntity<Object> handlerVoterNotFoundException(VoterNotFoundException ex, WebRequest req) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {VoterAlreadyExiste.class})
    public ResponseEntity<Object> handlerVoterAlreadyExistsException(VoterAlreadyExiste ex, WebRequest req) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    /*  Voter Exception FIN */

    //      Manipulation globale d'exception
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handlerOtherException(Exception ex, WebRequest req) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage().toString());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
