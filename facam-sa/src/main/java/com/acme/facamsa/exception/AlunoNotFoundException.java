package com.acme.facamsa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlunoNotFoundException extends ResponseStatusException {

    public AlunoNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }

}
