package com.backend.productorderwrapper.service;

import com.backend.productorderwrapper.dto.Product;

import java.util.List;

public interface ProductProviderService {

    List<Product> getSimilarProducts(final int id);
}
