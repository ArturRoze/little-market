package com.app.controller;

import com.app.domain.MyResponse;
import com.app.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductException.class)
    @ResponseBody
    public MyResponse handleProductException(ProductException pex) {
        System.out.println("=========!!!!!=========");
        System.out.println(pex.getMessage());
        return new MyResponse(pex.getMessage());
    }
}
