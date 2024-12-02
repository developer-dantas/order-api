package br.com.ambev.order_api.service.producer;

import br.com.ambev.order_api.config.RabbitMQConfig;
import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.model.ResumeOrderTotalPrice;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderProducer {

    public static final String ENVIANDO_VALOR_TOTAL_DO_PEDIDO_PARA_RABBIT_MQ = "Enviando valor total do pedido para RabbitMQ: {}";
    public static final String TOTAL_ENVIADO_PARA_A_FILA = "Total enviado para a fila: {}";
    public static final String RETORNANDO_MENSAGEM_DE_DUPLICIDADE_DE_PEDIDO_PARA_O_SERVICO_A = "Retornando mensagem de duplicidade de pedido para o servico A";
    public static final String PEDIDO_DUPLUCADO_NÃO_SERÁ_POSSIVEL_PROCESSAR = "Pedido duplucado, não será possivel processar";
    public static final String MENSAGEM_DE_DUPLIUCIDADE_ENVIADA = "Mensagem de dupliucidade enviada para a fila duplicate.order";
    private final RabbitTemplate rabbitTemplate;

    public void sendOrder(ResumeOrderTotalPrice resumeOrderTotalPrice) {
        log.info(ENVIANDO_VALOR_TOTAL_DO_PEDIDO_PARA_RABBIT_MQ, resumeOrderTotalPrice);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_TOTAL_ORDER, resumeOrderTotalPrice);
        log.info(TOTAL_ENVIADO_PARA_A_FILA, RabbitMQConfig.QUEUE_TOTAL_ORDER);
    }

    public void sendOrderDuplucate() {
        log.info(RETORNANDO_MENSAGEM_DE_DUPLICIDADE_DE_PEDIDO_PARA_O_SERVICO_A);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_DUPLICATE_ORDER, PEDIDO_DUPLUCADO_NÃO_SERÁ_POSSIVEL_PROCESSAR);
        log.info(MENSAGEM_DE_DUPLIUCIDADE_ENVIADA, RabbitMQConfig.QUEUE_DUPLICATE_ORDER);
    }
}
