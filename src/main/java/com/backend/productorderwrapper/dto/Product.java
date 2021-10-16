package com.backend.productorderwrapper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Product {

     private final long id;
     private final String name;
     private final double price;
     private final boolean availability;

}
