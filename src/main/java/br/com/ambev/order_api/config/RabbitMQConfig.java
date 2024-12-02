package br.com.ambev.order_api.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "orders";
    public static final String QUEUE_TOTAL_ORDER = "total.order";
    public static final String QUEUE_DUPLICATE_ORDER = "duplicate.order";

    @Bean(name = QUEUE_NAME)
    public Queue OrderQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean(name = QUEUE_TOTAL_ORDER)
    public Queue OrderTotalQueue() {
        return new Queue(QUEUE_TOTAL_ORDER, false);
    }

    @Bean(name = QUEUE_DUPLICATE_ORDER)
    public Queue OrderDuplicateQueue() {
        return new Queue(QUEUE_DUPLICATE_ORDER, false);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate) {
        return new RabbitAdmin(rabbitTemplate);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initialAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
