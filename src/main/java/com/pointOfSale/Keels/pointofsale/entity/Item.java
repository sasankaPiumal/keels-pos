package com.pointOfSale.Keels.pointofsale.entity;

import com.pointOfSale.Keels.pointofsale.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id",length = 100,nullable = false)
    private int itemId;

    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_unit",length = 100,nullable = false)
    private MeasuringUnitType measuringUnit;

    @Column(name = "balance_qty",length = 200,nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price",length = 100,nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price",length = 100,nullable = false)
    private double sellingPrice;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private boolean activeSatate;


    @OneToMany(mappedBy = "items")
    private Set<OrderDetails> orderDetails;


    public Item(String itemName, MeasuringUnitType measuringUnit, double balanceQty, double supplierPrice, double sellingPrice) {
        this.itemName = itemName;
        this.measuringUnit = measuringUnit;
        this.balanceQty = balanceQty;
        this.supplierPrice = supplierPrice;
        this.sellingPrice = sellingPrice;
    }
}
