package br.com.ambev.order_api.service.producer;

import br.com.ambev.order_api.config.RabbitMQConfig;
import br.com.ambev.order_api.factory.OrderFactory;
import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.model.ResumeOrderTotalPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class OrderProducerTest {

    public static final double TOTAL_ORDER = 100.0;
    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private OrderProducer orderProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendOrderToRabbitMQ() {
        ResumeOrderTotalPrice orderTotalPrice = OrderFactory.resumeOrderTotalPrice();
        orderProducer.sendOrder(orderTotalPrice);
        verify(rabbitTemplate, times(1))
                .convertAndSend(RabbitMQConfig.QUEUE_TOTAL_ORDER, orderTotalPrice);
    }
}
