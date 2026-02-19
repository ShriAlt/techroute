package com.xworkz.orderservice.entity;

import com.xworkz.orderservice.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{


}
