package com.warehouse_cloud.warehouse.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import com.warehouse_cloud.warehouse.Attributes;
import com.warehouse_cloud.warehouse.Warehouse;
import com.warehouse_cloud.warehouse.Order;
import com.warehouse_cloud.warehouse.Attributes.Type;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.Errors;

@Slf4j
@Controller
@SessionAttributes("warehouseOrder")
@RequestMapping("/design")
public class DesignWarehouseController {
    
    @ModelAttribute
    public void addAttributesToModel(Model model){
        List<Attributes> ingredients = Arrays.asList(
            new Attributes("2KK", "2000 tons", Type.CAPACITY),
            new Attributes("1KK", "1000 tons", Type.CAPACITY),
            new Attributes("RICH", "Premium class", Type.CLASS),
            new Attributes("POOR", "Normal class", Type.CLASS),
            new Attributes("RSI3", "Room size 300 m3", Type.SPACE),
            new Attributes("RSI5", "Room size 500 m3", Type.SPACE)
        );

        Type[] types = Attributes.Type.values();
        for(Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name="warehouseOrder")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name="warehouse")
    public Warehouse warehouse(){
        return new Warehouse();
    }

    private Iterable<Attributes> filterByType(
            List<Attributes> ingredients, Type type
    ){
        return ingredients.stream()
        .filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());
    }

    @PostMapping
    public String processWarehouse(
            @Valid Warehouse warehouse, Errors errors, @ModelAttribute Order warehouseOrder
    ){
        if (errors.hasErrors()){
            return "design";
        }
        warehouseOrder.addWarehouse(warehouse);
        log.info("Processing warehouse: {}", warehouse);
        return "redirect:/orders/current";
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }
}
