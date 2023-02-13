package com.pointOfSale.Keels.pointofsale.service;

import com.pointOfSale.Keels.pointofsale.dto.CustomerDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateNaneAddressNicRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateQueryRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerActiveStateResponseDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerIdAddressPhoneNicReaponseDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    CustomerDTO getCustomerByID(int id) throws FileNotFoundException;

    List<CustomerDTO> getAllCustomers();

    String updateCustomer(int id, CustomerUpdateRequestDTO customerUpdateRequestDTO);

    String deleteCustomer(int id);

    List<CustomerDTO> getByName(String name) throws FileNotFoundException;


    List<CustomerActiveStateResponseDTO> customerState() throws FileNotFoundException;

    List<CustomerActiveStateResponseDTO> getInactiveCustomers() throws FileNotFoundException;

    String updateByQuery(CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO, int id);

    CustomerDTO getDataByNic(String nic);

    CustomerIdAddressPhoneNicReaponseDTO getFewDataOfCustomer(int id);

    String updateNICAddressNicCustomer(CustomerUpdateNaneAddressNicRequestDTO customerUpdateNaneAddressNicRequestDTO, int ids);

    CustomerDTO getCustomerStatesByID(int id);
}


