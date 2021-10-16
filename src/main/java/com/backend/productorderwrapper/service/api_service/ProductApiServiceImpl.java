package com.backend.productorderwrapper.service.api_service;

import com.backend.productorderwrapper.dto.Product;
import com.backend.productorderwrapper.exception.ExternalApiErrorException;
import com.backend.productorderwrapper.exception.NotFoundException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class ProductApiServiceImpl implements ProductApiService {
    private static final String API_PATH = "http://localhost:3001/product/";
    private static final String SIMILAR_IDS = "/similarids";

    private static final Supplier<NotFoundException> NOT_FOUND_EXCEPTION = NotFoundException::new;

    private final RestTemplate restTemplate;

    @Autowired
    public ProductApiServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Integer> getSimilarIds(final int id) {
        final Integer [] response = restTemplate.getForObject(API_PATH + id + SIMILAR_IDS, Integer[].class);
        if (response != null) {
            return Arrays.stream(response).collect(Collectors.toList());
        }
        throw NOT_FOUND_EXCEPTION.get();
    }

    @Override
    public Optional<Product> getProductById(final int id) {
        final Product response;
        response = restTemplate.getForObject(API_PATH + id, Product.class);
        if (response != null) {
            return Optional.of(response);
        }
        throw NOT_FOUND_EXCEPTION.get();
    }

}
