package com.example.SpringBootBasic.exception;
import java.lang.Exception;

public class ExceptionService extends  Exception   {
    private String message;

    public ExceptionService(String message) {
        super(message);
        this.message = message;
    }
}
