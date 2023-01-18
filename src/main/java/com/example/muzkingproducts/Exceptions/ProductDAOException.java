package com.example.muzkingproducts.Exceptions;

public class ProductDAOException extends Exception {
    public ProductDAOException() {
    }
    public ProductDAOException(String message) {
        super(message);
    }
    public ProductDAOException(Throwable cause) {
        super(cause);
    }
    public ProductDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
