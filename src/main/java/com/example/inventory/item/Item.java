package com.example.inventory.item;

import org.springframework.data.annotation.Id;

/**
 * DTO for containing information about an Item
 */
public class Item {

    private final Long id;
    private final String name;
    private final Long price;
    private final String description;
    private final String image;

    /**
     * Public constructor
     * @param id the id
     * @param name the name of the item
     * @param price the price of the item
     * @param description the item description
     * @param image the image of the item
     */
    public Item(final Long id, final String name, final Long price, final String description, final String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    /**
     * Getter for id
     * @return the id
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Getter for name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for price
     * @return the price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Getter for description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for image
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Method to update the values for an item, keeping the current id
     * @param item the item to update
     * @return an updated item
     */
    public Item updateWith(final Item item) {
        return new Item(this.id, item.name, item.price, item.description, item.image);
    }
}
