package com.ebay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.ResourceAccessException;

import java.util.logging.Logger;

public class MissingAccessTokenException extends ResourceAccessException {

    final int statusCode;

    public MissingAccessTokenException(String msg) {
        super(msg);
        statusCode = HttpStatus.UNAUTHORIZED.value();
        Logger logger = Logger.getGlobal();
        logger.severe(msg);
    }
}
