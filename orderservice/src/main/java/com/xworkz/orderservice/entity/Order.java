package com.xworkz.orderservice.entity;

import com.xworkz.orderservice.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    private UUID id;

    private UUID customerId; // extracted from JWT

    @ElementCollection
    private List<OrderItem> products; // productId, quantity, price snapshot

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String createdBy;
    private String updatedBy;
}
