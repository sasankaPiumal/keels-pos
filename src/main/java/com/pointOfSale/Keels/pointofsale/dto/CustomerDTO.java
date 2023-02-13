package com.pointOfSale.Keels.pointofsale.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private ArrayList customerPhoneNos;
    private String nic;
    private boolean activeState;
}
