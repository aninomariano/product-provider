package com.backend.productorderwrapper.controller;

import com.backend.productorderwrapper.dto.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductWrapperController {

    @GetMapping(value="/{id}/similar", produces = "application/json")
    public @ResponseBody List<Product> getSimilarProducts(@PathVariable int id) {
        return null;
    }
}
