package com.app.exception;

import com.app.domain.outcome.ProductMsgResponse;

import java.util.List;

public class ProductException extends RuntimeException {

    private List<ProductMsgResponse> productsMsg;

    public ProductException(String message, List<ProductMsgResponse> productsMsg) {
        super(message);
        this.productsMsg = productsMsg;
    }

    public List<ProductMsgResponse> getProductsMsg() {
        return productsMsg;
    }

    public void setProductsMsg(List<ProductMsgResponse> productsMsg) {
        this.productsMsg = productsMsg;
    }
}
