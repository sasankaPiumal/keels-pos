package com.pointOfSale.Keels.pointofsale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateRequestDTO {
    private String customerName;
    private String customerAddress;
    private ArrayList customerPhoneNos;
    private String nic;
    private boolean activeState;
}
