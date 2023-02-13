package com.pointOfSale.Keels.pointofsale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerIdAddressPhoneNicReaponseDTO {
    private int customerId;
    private String customerAddress;
    private ArrayList customerPhoneNos;
    private String nic;
}
