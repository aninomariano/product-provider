package com.backend.productorderwrapper.service;

import com.backend.productorderwrapper.dto.Product;
import com.backend.productorderwrapper.service.api_service.ProductApiService;
import com.backend.productorderwrapper.service.api_service.ProductApiServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductProviderServiceTest {

    private ProductProviderService productProviderService;
    private ProductApiService productApiService;

    @BeforeEach
    void setUp() {
        productApiService = mock(ProductApiServiceImpl.class);
        productProviderService = new ProductProviderServiceImpl(productApiService);
    }

    @Test
    void whenTryToGetMultipleProductsThenMustGoAllOk() {
        final Product firstProduct = Product.newBuilder()
                .withId(2)
                .withAvailability(true)
                .withName("dress")
                .withPrice(6.99)
                .build();
        final Product secondProduct = Product.newBuilder()
                .withId(4)
                .withAvailability(true)
                .withName("shoes")
                .withPrice(11.99)
                .build();
        when(productApiService.getSimilarIds(1)).thenReturn(List.of(2, 4));
        when(productApiService.getProductById(2)).thenReturn(Optional.of(firstProduct));
        when(productApiService.getProductById(4)).thenReturn(Optional.of(secondProduct));

        final List<Product> products = productProviderService.getSimilarProducts(1);
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.contains(firstProduct)).isTrue();
        assertThat(products.contains(secondProduct)).isTrue();
    }

    @Test
    void whenTryToGetOneProductThenMustGoAllOk() {
        final Product firstProduct = Product.newBuilder()
                .withId(5)
                .withAvailability(false)
                .withName("dress")
                .withPrice(6.99)
                .build();
        when(productApiService.getSimilarIds(1)).thenReturn(List.of(5));
        when(productApiService.getProductById(5)).thenReturn(Optional.of(firstProduct));

        final List<Product> products = productProviderService.getSimilarProducts(1);
        assertThat(products.size()).isEqualTo(1);
        assertThat(products.contains(firstProduct)).isTrue();
    }
}
