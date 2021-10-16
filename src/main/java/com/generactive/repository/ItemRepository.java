package com.generactive.repository;

import com.generactive.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    List<Item> findByBasePriceGreaterThanAndBasePriceLessThan(Double from, Double to);
    Optional<Item> findByName(String name);
}
