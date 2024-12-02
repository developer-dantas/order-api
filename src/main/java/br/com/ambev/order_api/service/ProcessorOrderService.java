package br.com.ambev.order_api.service;

import br.com.ambev.order_api.model.Order;

public interface ProcessorOrderService {

    void saveOrder(Order order);

    double calculateTotal(Order order);

    boolean isDuplicateOrder(Order order);
}
