package com.app.model;

import java.util.List;

public class BlockRequest {

    private List<Integer> ids;

    private String blockReason;

    private ProductsType productsType;

    public BlockRequest() {
    }

    public BlockRequest(List<Integer> ids, String blockReason, ProductsType productsType) {
        this.ids = ids;
        this.blockReason = blockReason;
        this.productsType = productsType;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public ProductsType getProductsType() {
        return productsType;
    }

    public void setProductsType(ProductsType productsType) {
        this.productsType = productsType;
    }

    @Override
    public String toString() {
        return "BlockRequest{" +
                "ids=" + ids +
                ", blockReason='" + blockReason + '\'' +
                ", productsType=" + productsType +
                '}';
    }
}
