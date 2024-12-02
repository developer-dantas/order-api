package br.com.ambev.order_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private UUID productId;
    private String name;
    private Integer amount;
    private double price;
}
