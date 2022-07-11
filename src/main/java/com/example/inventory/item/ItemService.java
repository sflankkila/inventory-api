package com.example.inventory.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
