package com.acme.facamsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlunoNotValidException extends ResponseStatusException {

    public AlunoNotValidException(String msg) {
        super(HttpStatus.BAD_REQUEST, msg);
    }

}
