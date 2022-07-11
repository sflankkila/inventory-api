package com.example.inventory.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link Item}
 */
@RestController
@RequestMapping("api/inventory/items")
public class ItemController {

    private final ItemService itemService;

    /**
     * Public constructor
     * @param itemService service to be injected
     */
    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok().body("pong");
    }
}
