package com.appsdeveloperblog.estore.paymentsservice.core.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "payments")
@Data
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = -2278327629298269897L;

    @Id
    private String paymentId;

    private String orderId;
}
