package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @PostConstruct
    void init() {
        if (itemRepository.count() == 0) {
            List<Item> items = Arrays.stream(new String[]{"Iron Sword", "Iron Axe", "Dragon Sword", "Elven Bow", "Ebony Dagger"})
                    .map(Item::new)
                    .collect(Collectors.toList());
            itemRepository.saveAll(items);
        }
    }

    public Item findExistedByNameOrSave(Item item) {
        return itemRepository.findByNameIgnoreCase(item.getName()).orElse(itemRepository.save(item));
    }

    public List<Item> all() {
        return itemRepository.findAll();
    }
}
