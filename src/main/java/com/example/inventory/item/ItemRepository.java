package com.example.inventory.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * In-memory repository for {@link Item}
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
