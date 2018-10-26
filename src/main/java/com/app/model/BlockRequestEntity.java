package com.app.model;

import java.util.List;

public class BlockRequestEntity {

    private List<Long> ids;

    private String blockReason;

    private ProductsType productsType;

    public BlockRequestEntity() {
    }

    public BlockRequestEntity(List<Long> ids, String blockReason, ProductsType productsType) {
        this.ids = ids;
        this.blockReason = blockReason;
        this.productsType = productsType;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
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
        return "BlockRequestEntity{" +
                "ids=" + ids +
                ", blockReason='" + blockReason + '\'' +
                ", productsType=" + productsType +
                '}';
    }
}
