package br.com.ambev.order_api.service.consumer;

import br.com.ambev.order_api.mapper.OrderMapper;
import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.model.ResumeOrderTotalPrice;
import br.com.ambev.order_api.model.dto.OrderDto;
import br.com.ambev.order_api.model.enuns.OrderStatus;
import br.com.ambev.order_api.service.ProcessorOrderService;
import br.com.ambev.order_api.service.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    public static final String RECEBENDO_LISTA_DE_PEDIDOS = "Recebendo lista de pedidos: {}";
    public static final String ORDERS = "orders";

    private final ProcessorOrderService processorOrderService;
    private final OrderProducer orderProducer;
    private final OrderMapper orderMapper;

    @Transactional
    @RabbitListener(queues = OrderConsumer.ORDERS)
    public void processOrder(OrderDto orderDto) {
        log.info(RECEBENDO_LISTA_DE_PEDIDOS, orderDto);
        Order order = orderMapper.INSTANCE.toModel(orderDto);
        if (processorOrderService.isDuplicateOrder(order)) {
            log.warn("Pedido duplicado detectado: {}", order);
            orderProducer.sendOrderDuplucate();
            return;
        }
        order.setStatus(OrderStatus.PROCESSING);
        processorOrderService.saveOrder(order);
        double totalOrders = processorOrderService.calculateTotal(order);
        ResumeOrderTotalPrice resumeOrderTotalPrice = new ResumeOrderTotalPrice();
        resumeOrderTotalPrice.setOrder(order);
        resumeOrderTotalPrice.setTotalPrice(totalOrders);
        orderProducer.sendOrder(resumeOrderTotalPrice);
    }

}
