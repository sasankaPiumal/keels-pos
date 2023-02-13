package com.pointOfSale.Keels.pointofsale.service.impl;

import com.pointOfSale.Keels.pointofsale.entity.Customer;
import com.pointOfSale.Keels.pointofsale.dto.CustomerDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateNaneAddressNicRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateQueryRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerActiveStateResponseDTO;
import com.pointOfSale.Keels.pointofsale.dto.response.CustomerIdAddressPhoneNicReaponseDTO;
import com.pointOfSale.Keels.pointofsale.exception.NotFoundException;
import com.pointOfSale.Keels.pointofsale.repository.CustomerRepository;
import com.pointOfSale.Keels.pointofsale.service.CustomerService;
import com.pointOfSale.Keels.pointofsale.util.Mappers.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public String saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = new Customer(
                customerSaveRequestDTO.getCustomerName(),
                customerSaveRequestDTO.getCustomerAddress(),
                customerSaveRequestDTO.getCustomerPhoneNos(),
                customerSaveRequestDTO.getNic()
        );
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getCustomerId() + " saved successfully.";
        } else {
            return "Customer already exists.";
        }
    }

    @Override
    public CustomerDTO getCustomerByID(int id) throws FileNotFoundException {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.get().getCustomerId(),
//                    customer.get().getCustomerName(),
//                    customer.get().getCustomerAddress(),
//                    customer.get().getCustomerPhoneNos(),
//                    customer.get().getNic(),
//                    customer.get().isActiveState()
//            );
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;
        } else {
            throw new FileNotFoundException("Customer Not Found by id " + id);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
//        List<CustomerDTO> customerDTOS = new ArrayList<>();

//        for (Customer c :customers ) {
//            CustomerDTO customerDTO = new CustomerDTO(
//                    c.getCustomerId(),
//                    c.getCustomerName(),
//                    c.getCustomerAddress(),
//                    c.getCustomerPhoneNos(),
//                    c.getNic(),
//                    c.isActiveState()
//            );
//            customerDTOS.add(customerDTO);
//        }
//
//        return customerDTOS;

        List<CustomerDTO> customerDTOList = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {
        }.getType());
        return customerDTOList;
    }

    @Override
    public String updateCustomer(int id, CustomerUpdateRequestDTO customerUpdateRequestDTO) {

        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.getReferenceById(id);

            customer.setCustomerName(customerUpdateRequestDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateRequestDTO.getCustomerAddress());
            customer.setCustomerPhoneNos(customerUpdateRequestDTO.getCustomerPhoneNos());
            customer.setNic(customerUpdateRequestDTO.getNic());
            customer.setActiveState(customerUpdateRequestDTO.isActiveState());

            customerRepo.save(customer);
            return customer.getCustomerId() + " updated successfully.";
        } else {
            return id + " customer does not exists.";
        }
    }

    @Override
    public String deleteCustomer(int id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return id + " deleted successfully.";
        } else {
            return id + " customer does not exists.";
        }
    }

    @Override
    public List<CustomerDTO> getByName(String name) throws FileNotFoundException {
        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(name);

        if (customers.size() != 0) {
//            List<CustomerDTO> customerDTOS = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {
//            }.getType());
//            return customerDTOS;

            List<CustomerDTO> customerDTOList = customerMapper.entityListToDtoLIst(customers);
            return customerDTOList;

        } else {
            throw new FileNotFoundException("Customer not exists.");
        }

    }

    @Override
    public List<CustomerActiveStateResponseDTO> customerState() throws FileNotFoundException {

        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        if (customers.size() != 0) {
            List<CustomerActiveStateResponseDTO> customerActiveStateResponseDTO = customerMapper
                    .entityToCustomerActiveStateResponseDTO(customers);
            return customerActiveStateResponseDTO;
        } else {
            throw new FileNotFoundException("Not Found ! ");
        }
    }

    @Override
    public List<CustomerActiveStateResponseDTO> getInactiveCustomers() throws FileNotFoundException {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(false);

        if (customers.size() != 0) {

            List<CustomerActiveStateResponseDTO> customerActiveStateResponseDTOS = customerMapper
                    .entityToCustomerActiveStateResponseDTO(customers);
            return customerActiveStateResponseDTOS;
        } else {
            throw new FileNotFoundException("Not found ");
        }
    }

    @Override
    public String updateByQuery(CustomerUpdateQueryRequestDTO customerUpdateQueryRequestDTO, int id) {

        if (customerRepo.existsById(id)) {

            customerRepo.updateCustomerByNameAndNic(customerUpdateQueryRequestDTO.getCustomerName(),
                    customerUpdateQueryRequestDTO.getNic(), id);
            return "ID " + id + " customer updated successfully.";
        } else {
            return " Not found such customer.";
        }
    }

    @Override
    public CustomerDTO getDataByNic(String nic){
        Optional<Customer> customer = customerRepo.findByNicEquals(nic);
        if (customer.isPresent()) {
//            CustomerDTO customerDTO = modelMapper.map(customer.get(),CustomerDTO.class);
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;
        } else {
            throw new NotFoundException("Customer not found.");
        }
    }

    @Override
    public CustomerIdAddressPhoneNicReaponseDTO getFewDataOfCustomer(int id)  {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            CustomerIdAddressPhoneNicReaponseDTO customerIdAddressPhoneNicReaponseDTO = customerMapper
                    .customerFewData(customer.get());
            return customerIdAddressPhoneNicReaponseDTO;
        } else {
            throw new NotFoundException("Not found customer ib id " + id);
        }
    }

    @Override
    public String updateNICAddressNicCustomer(CustomerUpdateNaneAddressNicRequestDTO customerUpdateNaneAddressNicRequestDTO,
                                              int ids) {
        if (customerRepo.existsById(ids)) {
            Customer customer = customerRepo.getReferenceById(ids);
            customer.setCustomerName(customerUpdateNaneAddressNicRequestDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateNaneAddressNicRequestDTO.getCustomerAddress());
            customer.setNic(customerUpdateNaneAddressNicRequestDTO.getNic());

            customerRepo.save(customer);
            return " customer id " + customer.getCustomerId() + " updated successfully.";
        } else {
            return "Customer not exist by id " + ids;
        }
    }

    @Override
    public CustomerDTO getCustomerStatesByID(int id) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isPresent()){
            if (customerOptional.get().isActiveState() == true){
                CustomerDTO customerDTO = customerMapper.entityToDto(customerOptional.get());
                return customerDTO;
            }
            else {
                throw new NotFoundException(" Not exists customer by state.");
            }
        }
        else{
            return new CustomerDTO();
        }

    }


}
