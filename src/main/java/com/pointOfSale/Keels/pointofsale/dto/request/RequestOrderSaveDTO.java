package com.pointOfSale.Keels.pointofsale.dto.request;

import com.pointOfSale.Keels.pointofsale.entity.Customer;
import com.pointOfSale.Keels.pointofsale.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private Date orderDate;
    private Double total;
    private int customers;
    private List<RequestOrderDetailsSave> orderDetails;
}
