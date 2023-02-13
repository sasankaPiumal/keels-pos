package com.pointOfSale.Keels.pointofsale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerActiveStateResponseDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private boolean activeState;
}
