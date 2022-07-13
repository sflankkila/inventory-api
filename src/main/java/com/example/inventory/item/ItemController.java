package com.example.inventory.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        final List<Item> items = itemService.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> find(@PathVariable("id") final Long id) {
        final Optional<Item> item = itemService.find(id);
        return ResponseEntity.of(item);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        final Item created = itemService.create(item);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping
    public ResponseEntity<Item> update(@PathVariable("id") final Long id,
            @RequestBody final Item updatedItem) {
        final Optional<Item> updated = itemService.update(id, updatedItem);

        return updated.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    final Item created = itemService.create(updatedItem);
                    final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}").buildAndExpand(created.getId()).toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable("id") final Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
