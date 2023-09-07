package com.inventarioautomotriz.inventario.exceptions;

public class MercanciaException extends RuntimeException {

    public MercanciaException(String message) {
        super(message);
    }

    public MercanciaException(String message, Throwable cause) {
        super(message, cause);
    }

}
