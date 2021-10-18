package com.backend.productorderwrapper.controller;

import com.backend.productorderwrapper.dto.Product;
import com.backend.productorderwrapper.service.ProductProviderService;
import com.backend.productorderwrapper.service.ProductProviderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductWrapperControllerTest {
    
    private ProductWrapperController productWrapperController;
    private ProductProviderService productProviderService;
    
    @BeforeEach
    void setUp() {
        productProviderService = mock(ProductProviderServiceImpl.class);
        productWrapperController = new ProductWrapperController(productProviderService);
    }

    @Test
    void whenTryToGetProductsThenMustGoAllOk() {
        final List<Product> products = List.of(Product.newBuilder().withId(2).build());
        when(productProviderService.getSimilarProducts(1)).thenReturn(products);

        assertThat(productWrapperController.getSimilarProducts(1).getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
        assertThat(productWrapperController.getSimilarProducts(1).getBody()).isEqualTo(products);
    }
}
