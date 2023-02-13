package com.pointOfSale.Keels.pointofsale.dto.request;

import com.pointOfSale.Keels.pointofsale.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemUpdateRequestDTO {
    private String itemName;
//    private String measuringUnit;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
}
