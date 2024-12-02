package br.com.ambev.order_api.repository;

import br.com.ambev.order_api.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {

    boolean existsByName(String orderName);
}
