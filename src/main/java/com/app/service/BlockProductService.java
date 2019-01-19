package com.app.service;

import java.util.List;

public interface BlockProductService {

    int block(List<Long> ids, String description);
}
