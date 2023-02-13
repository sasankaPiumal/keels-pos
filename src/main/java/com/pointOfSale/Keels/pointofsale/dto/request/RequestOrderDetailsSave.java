package com.pointOfSale.Keels.pointofsale.dto.request;

import com.pointOfSale.Keels.pointofsale.entity.Item;
import com.pointOfSale.Keels.pointofsale.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {

    private String itemName;
    private double qty;
    private Double amount;
    private int items;
//    private int orders;
}
