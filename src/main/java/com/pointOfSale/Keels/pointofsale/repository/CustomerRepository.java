package com.pointOfSale.Keels.pointofsale.repository;

import com.pointOfSale.Keels.pointofsale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findAllByCustomerNameEquals(String name);

    List<Customer> findAllByActiveStateEquals(boolean b);

    @Modifying
    @Query(value = "update customers set cutomer_name=?1 , nic=?2  where customer_id=?3",nativeQuery = true)
    void updateCustomerByNameAndNic(String customerName, String nic, int id);

    Optional<Customer> findByActiveStateIs(String nic);

    Optional<Customer> findByNicEquals(String nic);
}
