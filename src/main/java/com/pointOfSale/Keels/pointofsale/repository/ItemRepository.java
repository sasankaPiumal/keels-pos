package com.pointOfSale.Keels.pointofsale.repository;

import com.pointOfSale.Keels.pointofsale.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepository extends JpaRepository<Item,Integer> {

    List<Item> findAllByActiveSatateEquals(boolean status);

    long countAllByActiveSatateEquals(boolean state);

    Page<Item> findAllByActiveSatateEquals(boolean activeState, Pageable of);
}
