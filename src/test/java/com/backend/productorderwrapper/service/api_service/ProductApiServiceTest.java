package com.backend.productorderwrapper.service.api_service;

import com.backend.productorderwrapper.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductApiServiceTest {

    private static final String API_PATH = "http://localhost:3001/product/";
    private static final String SIMILAR_IDS_PATH = "/similarids";

    private RestTemplate restTemplate;
    private ProductApiService productApiService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        productApiService = new ProductApiServiceImpl(restTemplate);
    }

    @Test
    public void whenTryToGetSimilarIdsThenMustGoAllOk() {
        final Integer[] ids = {2, 4};
        when(restTemplate.getForObject(
                eq(API_PATH + 1 + SIMILAR_IDS_PATH),
                eq(Integer[].class))
        ).thenReturn(ids);

        assertThat(productApiService.getSimilarIds(1).size()).isEqualTo(2);
    }

    @Test
    public void whenTryToGetSimilarIdsAndResponseIsEmptyThenMustThrowRespectiveError() {
        final Integer[] ids = {};
        when(restTemplate.getForObject(
                eq(API_PATH + 1 + SIMILAR_IDS_PATH),
                eq(Integer[].class))
        ).thenReturn(ids);

        assertThatThrownBy(() -> productApiService.getSimilarIds(1))
                .isInstanceOf(HttpClientErrorException.class);
    }

    @Test
    public void whenTryToGetSimilarIdsAndResponseNullThenMustThrowRespectiveError() {
        when(restTemplate.getForObject(
                eq(API_PATH + 1 + SIMILAR_IDS_PATH),
                eq(Integer[].class))
        ).thenReturn(null);

        assertThatThrownBy(() -> productApiService.getSimilarIds(1))
                .isInstanceOf(HttpClientErrorException.class);
    }

    @Test
    public void whenTryToGetProductByIdThenMustGoAllOk() {
        final Product product = Product.newBuilder()
                .withId(1)
                .withName("shirt")
                .build();
        when(restTemplate.getForObject(
                eq(API_PATH + 1),
                eq(Product.class))
        ).thenReturn(product);

        assertThat(productApiService.getProductById(1).get()).isEqualTo(product);
    }

    @Test
    public void whenTryToGetProductByIdAndServiceReturnsBusinessErrorThenMustLogRespectiveError() {
        when(restTemplate.getForObject(
                eq(API_PATH + 1),
                eq(Product.class))
        ).thenThrow(HttpClientErrorException.class);

        assertDoesNotThrow(() -> productApiService.getProductById(1));
    }

    @Test
    public void whenTryToGetProductByIdAndServiceReturnsServerErrorThenMustLogRespectiveError() {
        when(restTemplate.getForObject(
                eq(API_PATH + 1),
                eq(Product.class))
        ).thenThrow(HttpServerErrorException.class);

        assertDoesNotThrow(() -> productApiService.getProductById(1));
    }

    @Test
    public void whenTryToGetProductByIdAndOperationFailWithTimeoutThenMustLogRespectiveError() {
        when(restTemplate.getForObject(
                eq(API_PATH + 1),
                eq(Product.class))
        ).thenThrow(ResourceAccessException.class);

        assertDoesNotThrow(() -> productApiService.getProductById(1));
    }
}
