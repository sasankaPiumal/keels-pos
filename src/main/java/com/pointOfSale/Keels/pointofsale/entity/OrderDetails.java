package com.pointOfSale.Keels.pointofsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orderDetails")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_details_id",length = 200,nullable = false)
    private int orderDetailsId;

    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;

    @Column(name = "qty",length = 200,nullable = false)
    private double qty;

    @Column(name = "total",nullable = false)
    private Double amount;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "Item_FK_id",nullable = false)
    private Item items;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "Order_FK_id",nullable = false)
    private Order orders;

}
