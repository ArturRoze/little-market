package com.app.controller;

import com.app.domain.ProductResponse;
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
    public ProductResponse handleProductException(ProductException pex) {
        System.out.println(pex.getMessage());
        return new ProductResponse(pex.getMessage(), pex.getProductsMsg());
    }
}
