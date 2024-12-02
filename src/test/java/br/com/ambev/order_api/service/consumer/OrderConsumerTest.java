package br.com.ambev.order_api.service.consumer;

import br.com.ambev.order_api.factory.OrderFactory;
import br.com.ambev.order_api.mapper.OrderMapper;
import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.model.ResumeOrderTotalPrice;
import br.com.ambev.order_api.model.dto.OrderDto;
import br.com.ambev.order_api.model.enuns.OrderStatus;
import br.com.ambev.order_api.service.ProcessorOrderService;
import br.com.ambev.order_api.service.producer.OrderProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class OrderConsumerTest {

    public static final String ORDER_ID = "orderId";
    public static final double TOTAL_ORDER_PRICE = 60.0;
    @Mock
    private ProcessorOrderService processorOrderService;

    @Mock
    private OrderProducer orderProducer;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderConsumer orderConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldProcessOrdersAndSendTotalToRabbitMQ() {
        OrderDto orderDto = OrderFactory.orderDto();
        Order expectedOrder = orderMapper.INSTANCE.toModel(orderDto);

        expectedOrder.setStatus(OrderStatus.PROCESSING);

        double totalOrderPrice = TOTAL_ORDER_PRICE;

        when(processorOrderService.calculateTotal(expectedOrder)).thenReturn(totalOrderPrice);

        orderConsumer.processOrder(orderDto);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(processorOrderService, times(1)).saveOrder(orderCaptor.capture());

        Order capturedOrder = orderCaptor.getValue();

        assertThat(capturedOrder)
                .usingRecursiveComparison()
                .ignoringFields(ORDER_ID)
                .isEqualTo(expectedOrder);

        ArgumentCaptor<ResumeOrderTotalPrice> totalCaptor = ArgumentCaptor.forClass(ResumeOrderTotalPrice.class);
        verify(orderProducer, times(1)).sendOrder(totalCaptor.capture());
        ResumeOrderTotalPrice capturedTotal = totalCaptor.getValue();
        assertThat(capturedTotal.getTotalPrice()).isEqualTo(totalOrderPrice);
        assertThat(capturedTotal.getOrder())
                .usingRecursiveComparison()
                .ignoringFields(ORDER_ID)
                .isEqualTo(expectedOrder);
    }

}
