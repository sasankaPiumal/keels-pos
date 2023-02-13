package com.pointOfSale.Keels.pointofsale.util.Mappers;

import com.pointOfSale.Keels.pointofsale.entity.Customer;
import com.pointOfSale.Keels.pointofsale.dto.CustomerDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerActiveStateResponseDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerIdAddressPhoneNicReaponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO entityToDto(Customer customer);

    List<CustomerDTO> entityListToDtoLIst(List<Customer> customers);

    List<CustomerActiveStateResponseDTO> entityToCustomerActiveStateResponseDTO(List<Customer> customers);

    CustomerIdAddressPhoneNicReaponseDTO customerFewData(Customer customer);
}

