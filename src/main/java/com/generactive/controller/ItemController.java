package com.generactive.controller;

import com.generactive.service.ItemService;
import com.generactive.service.criteria.ItemSearchCriteria;
import com.generactive.service.dto.ItemDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDTO> getAll(@RequestBody ItemSearchCriteria criteria) {
        return itemService.getAll(criteria);
    }


    @GetMapping("/{id}")
    public ItemDTO get(@PathVariable long id) {
        return itemService.get(id);
    }

    @GetMapping("/by-price")
    public List<ItemDTO> allByPriceRange(@RequestParam double from,
                                         @RequestParam double to) {
        return itemService.allByPriceRange(from, to);
    }

    @GetMapping("/search")
    public ItemDTO getByName(@RequestBody ItemSearchCriteria criteria) {
        return itemService.getByName(criteria);
    }

    @PostMapping
    public ItemDTO save(@RequestBody ItemDTO itemDTO,
                        @AuthenticationPrincipal UserDetails user) {
        itemDTO.setCreatedBy(user.getUsername());
        return itemService.create(itemDTO);
    }

    @PutMapping
    public void update(@RequestBody ItemDTO item) {
        itemService.update(item);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/set-group")
    public void setGroup(@RequestParam Long itemId,
                         @RequestParam Long groupId) {
        itemService.setGroup(itemId, groupId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
