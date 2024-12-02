package br.com.ambev.order_api.service.impl;

import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.repository.OrderRepository;
import br.com.ambev.order_api.service.ProcessorOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProcessorOrderServiceImpl implements ProcessorOrderService {

    public static final String PEDIDO_SALVO_NO_BANCO_DE_DADOS = "Pedido salvo no banco de dados: {}";
    public static final String CALCULANDO_TOTAL_DO_PEDIDO = "Calculando total do pedido {}";
    public static final String TOTAL_DO_PEDIDO_CALCULADO = "Total do pedido calculado: {}";
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void saveOrder(Order order) {
            orderRepository.save(order);
            log.info(PEDIDO_SALVO_NO_BANCO_DE_DADOS, order);
    }

    @Override
    public double calculateTotal(Order order) {
        log.info(CALCULANDO_TOTAL_DO_PEDIDO, order);
        double totalPriceOrder = order.getProducts().stream()
                .mapToDouble(product -> product.getPrice() * product.getAmount())
                .sum();
        log.info(TOTAL_DO_PEDIDO_CALCULADO, Optional.of(totalPriceOrder));
        return totalPriceOrder;
    }

    @Override
    public boolean isDuplicateOrder(Order order) {
        // Verificar se já existe um pedido com o mesmo nome e orderId
        return orderRepository.existsByName(order.getName());
    }


}
