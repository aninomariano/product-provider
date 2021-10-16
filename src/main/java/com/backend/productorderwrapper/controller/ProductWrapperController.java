package com.backend.productorderwrapper.controller;

import com.backend.productorderwrapper.dto.Product;
import com.backend.productorderwrapper.exception.ExternalApiErrorException;
import com.backend.productorderwrapper.exception.NotFoundException;
import com.backend.productorderwrapper.service.ProductProviderService;
import com.backend.productorderwrapper.service.api_service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductWrapperController {

    private final ProductProviderService productProviderService;

    @Autowired
    public ProductWrapperController(final ProductProviderService productProviderService) {
        this.productProviderService = productProviderService;
    }

    @GetMapping(value="/{id}/similar", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getSimilarProducts(@PathVariable int id) {
        return ResponseEntity.of(Optional.of(productProviderService.getSimilarProducts(id)));
    }
}
