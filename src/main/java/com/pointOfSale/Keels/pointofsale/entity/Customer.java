package com.pointOfSale.Keels.pointofsale.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "customers")
@TypeDefs({
        @TypeDef(name = "json",typeClass = JsonType.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Customer_id",length = 100,nullable = false)
    private int customerId;

    @Column(name = "cutomer_name",length = 100,nullable = false)
    private String customerName;

    @Column(name = "customer_address",length = 200,nullable = false)
    private String customerAddress;

    @Type(type = "json")
    @Column(name = "customer_phoneNo",columnDefinition = "json")
    private ArrayList customerPhoneNos;

    @Column(name = "nic",length = 12,unique = true)
    private String nic;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private boolean activeState;

    @OneToMany(mappedBy = "customers")
    private Set<Order> orders;

    public Customer(String customerName, String customerAddress, ArrayList customerPhoneNos, String nic) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNos = customerPhoneNos;
        this.nic = nic;
    }
}
