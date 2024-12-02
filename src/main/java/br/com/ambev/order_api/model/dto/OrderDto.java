package br.com.ambev.order_api.model.dto;

import br.com.ambev.order_api.model.Product;
import br.com.ambev.order_api.model.enuns.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private UUID orderId;
    private String name;
    private List<ProductDto> products;
    private OrderStatus status;

}
