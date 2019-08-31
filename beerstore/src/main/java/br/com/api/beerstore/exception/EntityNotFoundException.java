package br.com.api.beerstore.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException() {
        super("beers-6", HttpStatus.BAD_REQUEST);
    }
}
