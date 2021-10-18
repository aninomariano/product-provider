package com.backend.productorderwrapper.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product {

     private final long id;
     private final String name;
     private final double price;
     private final boolean availability;

     private Product(Builder builder) {
          id = builder.id;
          name = builder.name;
          price = builder.price;
          availability = builder.availability;
     }

     public Product(long id, String name, double price, boolean availability) {
          this.id = id;
          this.name = name;
          this.price = price;
          this.availability = availability;
     }

     public static Builder newBuilder() {
          return new Builder();
     }

     public long getId() {
          return id;
     }

     public String getName() {
          return name;
     }

     public double getPrice() {
          return price;
     }

     public boolean isAvailability() {
          return availability;
     }

     @Override
     public String toString() {
          return "Product{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", price=" + price +
                  ", availability=" + availability +
                  '}';
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;

          if (o == null || getClass() != o.getClass()) return false;

          Product product = (Product) o;

          return new EqualsBuilder()
                  .append(id, product.id)
                  .append(price, product.price)
                  .append(availability, product.availability)
                  .append(name, product.name)
                  .isEquals();
     }

     @Override
     public int hashCode() {
          return new HashCodeBuilder(17, 37)
                  .append(id)
                  .append(name)
                  .append(price)
                  .append(availability)
                  .toHashCode();
     }

     /**
      * {@code Product} builder static inner class.
      */
     public static final class Builder {
          private long id;
          private String name;
          private double price;
          private boolean availability;

          private Builder() {
          }

          /**
           * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
           *
           * @param val the {@code id} to set
           * @return a reference to this Builder
           */
          public Builder withId(long val) {
               id = val;
               return this;
          }

          /**
           * Sets the {@code name} and returns a reference to this Builder so that the methods can be chained together.
           *
           * @param val the {@code name} to set
           * @return a reference to this Builder
           */
          public Builder withName(String val) {
               name = val;
               return this;
          }

          /**
           * Sets the {@code price} and returns a reference to this Builder so that the methods can be chained together.
           *
           * @param val the {@code price} to set
           * @return a reference to this Builder
           */
          public Builder withPrice(double val) {
               price = val;
               return this;
          }

          /**
           * Sets the {@code availability} and returns a reference to this Builder so that the methods can be chained together.
           *
           * @param val the {@code availability} to set
           * @return a reference to this Builder
           */
          public Builder withAvailability(boolean val) {
               availability = val;
               return this;
          }

          /**
           * Returns a {@code Product} built from the parameters previously set.
           *
           * @return a {@code Product} built with parameters of this {@code Product.Builder}
           */
          public Product build() {
               return new Product(this);
          }
     }
}
