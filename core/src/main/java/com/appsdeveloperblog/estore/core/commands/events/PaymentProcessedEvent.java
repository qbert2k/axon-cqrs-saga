package com.appsdeveloperblog.estore.core.commands.events;

import lombok.Data;

@Data
public class PaymentProcessedEvent {

    private final String orderId;
    private final String paymentId;
}
