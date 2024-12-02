package br.com.ambev.order_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeOrderTotalPrice {

    private Order order;
    private Double totalPrice;

}
