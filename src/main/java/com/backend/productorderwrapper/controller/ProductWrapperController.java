package com.backend.productorderwrapper.controller;

import com.backend.productorderwrapper.service.ProductProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
