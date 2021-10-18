package com.backend.productorderwrapper.service.api_service;

import com.backend.productorderwrapper.dto.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;

@Component
public class ProductApiServiceImpl implements ProductApiService {
    private static final Logger LOGGER = LogManager.getLogger(ProductApiServiceImpl.class);
    private static final String API_PATH = "http://localhost:3001/product/";
    private static final String SIMILAR_IDS = "/similarids";

    private static final Supplier<HttpClientErrorException> NOT_FOUND_EXCEPTION_SUPPLIER = () ->
            new HttpClientErrorException(HttpStatus.NOT_FOUND);

    private final RestTemplate restTemplate;

    @Autowired
    public ProductApiServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Integer> getSimilarIds(final int id) throws HttpClientErrorException {
        final Integer [] response = restTemplate.getForObject(API_PATH + id + SIMILAR_IDS, Integer[].class);
        if (response != null && response.length != 0) {
            return Arrays.stream(response).collect(Collectors.toList());
        }
        throw NOT_FOUND_EXCEPTION_SUPPLIER.get();
    }

    @Override
    @ExceptionHandler
    public Optional<Product> getProductById(final int id) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(API_PATH + id, Product.class));
        } catch (final HttpServerErrorException e) {
            /*
             I consider that in this case it would not be a good practice if we stop the flow with runtime errors.
             Here it would be important to take these values in metrics
             and have real-time visualization of these errors in clients such as Datadog or NewRelic
             */
            LOGGER.atError().log("[event:get_product_server_error][method:getSimilarId][status:{}][message{}]",
                    e.getStatusCode(),
                    e.getMessage());
        } catch (final HttpClientErrorException e) {
            /*
            Idem for this catch
             */
            LOGGER.atError().log("[event:get_product_client_error][method:getProductById][status:{}][message{}]",
                    e.getStatusCode(),
                    e.getMessage());
        } catch (final ResourceAccessException e) {
            /*
            Idem for this catch
             */
            LOGGER.atError().log("[event:get_product_timeout][method:getProductById][status:{}][message{}]",
                    GATEWAY_TIMEOUT,
                    e.getMessage());
        }
        return Optional.empty();
    }


}
