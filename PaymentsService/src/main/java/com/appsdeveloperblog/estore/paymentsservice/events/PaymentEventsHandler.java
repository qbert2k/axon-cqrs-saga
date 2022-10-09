package com.appsdeveloperblog.estore.paymentsservice.events;

import com.appsdeveloperblog.estore.core.commands.events.PaymentProcessedEvent;
import com.appsdeveloperblog.estore.paymentsservice.core.data.PaymentEntity;
import com.appsdeveloperblog.estore.paymentsservice.core.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventsHandler {

    public static Logger LOGGER = LoggerFactory.getLogger(PaymentEventsHandler.class);

    private final PaymentRepository paymentRepository;

    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        LOGGER.info("PaymentProcessedEvent is called for orderId: " + event.getOrderId());

        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(event, paymentEntity);

        paymentRepository.save(paymentEntity);
    }
}
