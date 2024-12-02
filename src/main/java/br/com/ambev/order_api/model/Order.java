package br.com.ambev.order_api.model;

import br.com.ambev.order_api.model.enuns.OrderStatus;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order implements Serializable {

    private UUID orderId;
    private String name;
    private List<Product> products;
    private OrderStatus status;

}
