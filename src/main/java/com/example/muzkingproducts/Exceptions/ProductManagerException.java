package com.example.muzkingproducts.Exceptions;

public class ProductManagerException extends Exception {
    public ProductManagerException() {
    }
    public ProductManagerException(String message) {
        super(message);
    }
    public ProductManagerException(Throwable cause) {
        super(cause);
    }
    public ProductManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
