package com.appsdeveloperblog.estore.ordersservice.command.rest;

import com.appsdeveloperblog.estore.ordersservice.command.commands.CreateOrderCommand;
import com.appsdeveloperblog.estore.ordersservice.core.model.OrderStatus;
import com.appsdeveloperblog.estore.ordersservice.core.model.OrderSummary;
import com.appsdeveloperblog.estore.ordersservice.query.FindOrderQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;

    public OrdersCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public OrderSummary createOrder(@Valid @RequestBody CreateOrderRestModel createOrderRestModel) {
        String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
        String orderId = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(orderId)
                .userId(userId)
                .productId(createOrderRestModel.getProductId())
                .quantity(createOrderRestModel.getQuantity())
                .addressId(createOrderRestModel.getAddressId())
                .orderStatus(OrderStatus.CREATED)
                .build();

        SubscriptionQueryResult<OrderSummary, OrderSummary> queryResult = queryGateway.subscriptionQuery(
                new FindOrderQuery(orderId),
                ResponseTypes.instanceOf(OrderSummary.class),
                ResponseTypes.instanceOf(OrderSummary.class));

        try {
            commandGateway.sendAndWait(createOrderCommand);
            return queryResult.updates().blockFirst();
        } finally {
            queryResult.close();
        }
    }
}
