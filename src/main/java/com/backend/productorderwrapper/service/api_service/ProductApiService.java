package com.backend.productorderwrapper.service.api_service;

import com.backend.productorderwrapper.dto.Product;

import java.util.List;
import java.util.Optional;

public interface ProductApiService {

    List<Integer> getSimilarIds(final int id);

    Optional<Product> getProductById(final int id);

}
