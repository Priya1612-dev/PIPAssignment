package com.springboot.template.exceptions;

public class ZipDownloadFailedException extends RuntimeException {
    public ZipDownloadFailedException(String message) {
        super(message);
    }
}
