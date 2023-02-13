package com.pointOfSale.Keels.pointofsale.controller;

import com.pointOfSale.Keels.pointofsale.dto.CustomerDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateNaneAddressNicRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateQueryRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerActiveStateResponseDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerIdAddressPhoneNicReaponseDTO;
import com.pointOfSale.Keels.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(
            path = {"/customer-save"}
    )
    public String CustomerSave(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        String id = customerService.saveCustomer(customerSaveRequestDTO);
        return id;
    }

    @GetMapping(
            path = {"/get-customer-by-id"},
            params = {"ids"}
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "ids") int id) throws FileNotFoundException {
        CustomerDTO cutomerDTO = customerService.getCustomerByID(id);
        return cutomerDTO;
    }

    @GetMapping(
            path = {"/get-all-customers"}
    )
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        return customerDTOS;
    }

    @PutMapping(
            path = {"/customer-update"},
            params = {"id"}
    )
    public String customerUpdate(@RequestParam(value = "id") int id, @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        String updated = customerService.updateCustomer(id, customerUpdateRequestDTO);
        return updated;
    }

    @DeleteMapping(
            path = {"/delete-customer/{deleteId}"}
    )
    public String customerDelete(@PathVariable(value = "deleteId") int id) {
        String deleted = customerService.deleteCustomer(id);
        return deleted;
    }

    //------------------ get by NAME ------------------------
    @GetMapping(
            path = {"/get-by-name"},
            params = {"name"}
    )
    public List<CustomerDTO> getByName(@RequestParam(value = "name") String name) throws FileNotFoundException {
        List<CustomerDTO> customerDTOS = customerService.getByName(name);
        return customerDTOS;
    }

    @GetMapping(
            path = {"/get-customer-by-state"}
    )
    public List<CustomerActiveStateResponseDTO> getCustomerByState() throws FileNotFoundException {
        List<CustomerActiveStateResponseDTO> customerActiveStateResponseDTOS = customerService.customerState();
        return customerActiveStateResponseDTOS;
    }

    @GetMapping(
            path = {"/get-inacticve-customers"}
    )
    public List<CustomerActiveStateResponseDTO> getInactives() throws FileNotFoundException {
        List<CustomerActiveStateResponseDTO> customerInactives = customerService.getInactiveCustomers();
        return customerInactives;
    }

    @PutMapping(
            path = {"/update-by-query"},
            params = {"id"}
    )
    public String updateByQuery(@RequestBody CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO, @RequestParam(value = "id") int id) {
        String quryUpdate = customerService.updateByQuery(customerUpdateQueryRequestDTO, id);
        return quryUpdate;
    }

    @GetMapping(
            path = {"/get-by-nic"},
            params = {"nic"}
    )
    public CustomerDTO getByNIC(@RequestParam(value = "nic") String nic) {
        CustomerDTO customerDTO = customerService.getDataByNic(nic);
        return customerDTO;
    }

    @GetMapping(
            path = {"/customer-id-Address-nic-phone/{id}"}
    )
    public CustomerIdAddressPhoneNicReaponseDTO customerIdAddresNicPhoneData(@PathVariable(value = "id") int id) {
        CustomerIdAddressPhoneNicReaponseDTO customerIdAddressPhoneNicReaponseDTO = customerService.getFewDataOfCustomer(id);
        return customerIdAddressPhoneNicReaponseDTO;
    }

    @PutMapping(
            path = {"/update-name-address-nic"},
            params = {"ids"}
    )
    public String updateCustomerNameAddressNic(
            @RequestBody CustomerUpdateNaneAddressNicRequestDTO customerUpdateNaneAddressNicRequestDTO,
            @RequestParam(value = "ids") int ids) {
        String updated = customerService.updateNICAddressNicCustomer(customerUpdateNaneAddressNicRequestDTO, ids);
        return updated;
    }

    @GetMapping(
            path = {"/get-customer-active-by-id"},
            params = {"id"}
    )
    public CustomerDTO getActiveCustomerById(@RequestParam(value = "id") int id){
        CustomerDTO customerDTO = customerService.getCustomerStatesByID(id);
        return customerDTO;
    }




}
