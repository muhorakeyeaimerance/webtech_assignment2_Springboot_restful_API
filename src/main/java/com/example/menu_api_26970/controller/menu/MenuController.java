package com.example.menu_api_26970.controller.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.menu_api_26970.model.menu.MenuItem;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menuList = new ArrayList<>();

    public MenuController() {

        menuList.add(new MenuItem(1L, "Chicken Wings", "Spicy fried wings", 6.5, "Appetizer", true));
        menuList.add(new MenuItem(2L, "Beef Burger", "Grilled beef burger", 8.0, "Main Course", true));
        menuList.add(new MenuItem(3L, "Vegetable Pasta", "Pasta with vegetables", 7.5, "Main Course", true));
        menuList.add(new MenuItem(4L, "Chocolate Cake", "Rich chocolate dessert", 4.5, "Dessert", true));
        menuList.add(new MenuItem(5L, "Fruit Juice", "Fresh mango juice", 3.0, "Beverage", true));
        menuList.add(new MenuItem(6L, "French Fries", "Crispy fries", 3.5, "Appetizer", false));
        menuList.add(new MenuItem(7L, "Ice Cream", "Vanilla ice cream", 2.5, "Dessert", true));
        menuList.add(new MenuItem(8L, "Grilled Fish", "Served with rice", 9.0, "Main Course", false));
    }

    @GetMapping
    public List<MenuItem> getAllItems() {
        return menuList;
    }

    @GetMapping("/{id}")
    public MenuItem getItemById(@PathVariable Long id) {

        for (MenuItem item : menuList) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        throw new RuntimeException("Menu item not found");
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getByCategory(@PathVariable String category) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuList) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }

        return result;
    }

    @GetMapping("/available")
    public List<MenuItem> getAvailableItems(@RequestParam boolean available) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuList) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }

        return result;
    }

    @GetMapping("/search")
    public List<MenuItem> searchByName(@RequestParam String name) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuList) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItem addMenuItem(@RequestBody MenuItem item) {
        menuList.add(item);
        return item;
    }

    @PutMapping("/{id}/availability")
    public MenuItem toggleAvailability(@PathVariable Long id) {

        for (MenuItem item : menuList) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return item;
            }
        }

        throw new RuntimeException("Menu item not found");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        menuList.removeIf(item -> item.getId().equals(id));
    }
}
