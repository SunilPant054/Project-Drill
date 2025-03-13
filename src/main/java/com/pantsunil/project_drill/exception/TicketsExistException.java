package com.pantsunil.project_drill.exception;

public class TicketsExistException extends RuntimeException{

    public TicketsExistException(String message){
        super(message);
    }
}
