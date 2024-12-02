package br.com.ambev.order_api.service.impl;

import br.com.ambev.order_api.factory.OrderFactory;
import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class ProcessorOrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ProcessorOrderServiceImpl processorOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveOrderAndLogSuccess() {
        Order order = OrderFactory.order();
        processorOrderService.saveOrder(order);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void shouldCalculateTotalOrder() {
        Order order = OrderFactory.order();
        double total = processorOrderService.calculateTotal(order);
        assertThat(total).isEqualTo(486.0);
    }
}
