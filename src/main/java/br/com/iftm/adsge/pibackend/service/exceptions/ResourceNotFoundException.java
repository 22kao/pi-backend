package br.com.iftm.adsge.pibackend.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
