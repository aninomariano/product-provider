package com.backend.productorderwrapper.service;

import com.backend.productorderwrapper.dto.Product;
import com.backend.productorderwrapper.service.api_service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProductProviderServiceImpl implements ProductProviderService {

    final ProductApiService productApiService;

    @Autowired
    public ProductProviderServiceImpl(final ProductApiService productApiService) {
        this.productApiService = productApiService;
    }

    @Override
    @ExceptionHandler
    public List<Product> getSimilarProducts(final int id) {
        final List<Integer> products= productApiService.getSimilarIds(id);
        return products.stream()
                .parallel()
                .flatMap(this::executeProductList).collect(Collectors.toList());
    }

    @ExceptionHandler
    private Stream<Product> executeProductList(final int productId) {
        return productApiService.getProductById(productId).stream();
    }
}
