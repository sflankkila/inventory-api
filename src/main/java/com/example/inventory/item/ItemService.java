package com.example.inventory.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service for fetching {@link Item}s from the {@link ItemRepository}
 */
@Service
@EnableMapRepositories
public class ItemService {

    private final CrudRepository<Item, Long> repository;

    /**
     * Public constructor
     * @param repository {@link CrudRepository} to be injected
     */
    public ItemService(final CrudRepository<Item, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultItems());
    }

    /**
     * Default list of items
     * TODO: Try setting up an S3 CDN with my own images (https://aws.amazon.com/cloudfront/getting-started/S3/)
     * @return list of default items
     */
    private static List<Item> defaultItems() {
        return List.of(
                new Item(1L, "Burger", 599L, "Tasty",
                        "https://cdn.auth0.com/blog/whatabyte/burger-sm.png"),
                new Item(2L, "Pizza", 299L, "Cheesy",
                        "https://cdn.auth0.com/blog/whatabyte/pizza-sm.png"),
                new Item(3L, "Tea", 199L, "Informative",
                        "https://cdn.auth0.com/blog/whatabyte/tea-sm.png")
        );
    }

    /**
     * Find all items
     * @return list of items
     */
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    /**
     * Find item using an id
     * @param id the id to use
     * @return found item
     */
    public Optional<Item> find(final Long id) {
        return repository.findById(id);
    }

    /**
     * Create an item
     * @param item item to be created
     * @return created item
     */
    public Item create(final Item item) {
        //TODO: Change the Item id to be of type UUID..
        final Item copy = new Item(new Date().getTime(), item.getName(), item.getPrice(),
                item.getDescription(), item.getImage());
        return repository.save(copy);
    }

    /**
     * Update an item
     * @param id the id of the item to be updated
     * @param newItem the new item
     * @return updated item
     */
    public Optional<Item> update(final Long id, final Item newItem) {
        return repository.findById(id).map(oldItem -> {
            final Item updated = oldItem.updateWith(newItem);
            return repository.save(updated);
        });
    }

    /**
     * Delete an item
     * @param id the item to be deleted
     */
    public void delete(final Long id) {
        repository.deleteById(id);
    }

}
