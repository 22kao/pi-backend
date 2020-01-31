package br.com.iftm.adsge.pibackend.controller.exceptions;

public enum ErrorMessage {

    DATABASE("Database error"),
    RESOURCE_NOT_FOUND("Resource not found"),
    JSON_PARSE_ERROR("Invalid Json"),
    ARGUMENT_INVALID("Argument invalid"),
    INTERNAL("Internal error");

    private String message;

    ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
