package com.store.purchase.config.rabbit.connections;

import com.store.purchase.config.rabbit.RabbitmqConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConnection {

    private AmqpAdmin amqpAdmin;

    public RabbitmqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String name) {
        return new Queue(name, true, false, false);
    }

    private DirectExchange exchange() {
        return new DirectExchange(RabbitmqConstants.EXCHANGE_AMQ);
    }

    private Binding relationship(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        Queue buy_queue = this.queue(RabbitmqConstants.QUEUE_BUY);
        DirectExchange exchange = this.exchange();
        Binding binding = this.relationship(buy_queue, exchange);

        this.amqpAdmin.declareQueue(buy_queue);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(binding);
    }
}
