package com.app.model.outcome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMsgResponse {

    private String uuid;
    private String title;

    public ProductMsgResponse(String uuid) {
        this.uuid = uuid;
    }
}
