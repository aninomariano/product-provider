package com.backend.productorderwrapper.service;

import com.backend.productorderwrapper.dto.Product;
import com.backend.productorderwrapper.exception.NotFoundException;
import com.backend.productorderwrapper.service.api_service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public List<Product> getSimilarProducts(final int id) {
        final List<Integer> products = productApiService.getSimilarIds(id);
        return products.stream()
                .parallel()
                .flatMap(productId -> executeProductList(productId)).collect(Collectors.toList());
    }

    private Stream<Product> executeProductList(final int productId) {
        try {
            return productApiService.getProductById(productId).stream();
        } catch (final NotFoundException e) {

        }
        return null;
    }
}
