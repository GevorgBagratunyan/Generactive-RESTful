package com.generactive.service;

import com.generactive.exception.item.ItemNotFoundException;
import com.generactive.exception.item.ItemSearchIllegalArgumentException;
import com.generactive.model.Group;
import com.generactive.model.Item;
import com.generactive.model.User;
import com.generactive.repository.GroupRepository;
import com.generactive.repository.ItemRepository;
import com.generactive.repository.UserRepository;
import com.generactive.service.criteria.ItemSearchCriteria;
import com.generactive.service.criteria.PageableImp;
import com.generactive.service.crud.CRUD;
import com.generactive.service.dto.ItemDTO;
import com.generactive.service.specification.GenericSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService implements CRUD<ItemDTO, Long> {

    private final ItemRepository itemRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ItemDTO create(ItemDTO itemDTO) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        User user = userRepository.findByUsername(itemDTO.getCreatedBy());
        item.setCreatedBy(user);
        Item saved = itemRepository.save(item);
        BeanUtils.copyProperties(saved, itemDTO);
        return itemDTO;
    }

    @Override
    public ItemDTO get(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.valueOf(id)));
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(item, itemDTO);
        if (item.getGroup() != null) {
            itemDTO.setGroupName(item.getGroup().getName());
        }
        return itemDTO;
    }

    @Override
    public void update(ItemDTO itemDTO) {

        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        itemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public List<ItemDTO> getAll(ItemSearchCriteria criteria) {
        Integer limit = criteria.getLimit();
        Integer offset = criteria.getOffset();
        String sort = criteria.getSort();
        String orderByFieldName = criteria.getOrderByFieldName();

        validateSearch(criteria);

        Sort srt;
        if(sort==null) {
            srt = Sort.by(Sort.Direction.ASC, "id");
        } else if (sort.equalsIgnoreCase("ASC")) {
            srt = Sort.by(Sort.Direction.ASC, orderByFieldName);
        } else {
            srt = Sort.by(Sort.Direction.DESC, orderByFieldName);
        }

        Pageable pageable = new PageableImp(limit, offset, srt);

        List<Item> items = itemRepository.findAll(pageable).getContent();
        return mapToItemDTOs(items);
    }


    public List<ItemDTO> allByPriceRange(double from, double to) {

        List<Item> items = itemRepository.findByBasePriceGreaterThanAndBasePriceLessThan(from, to);
        return mapToItemDTOs(items);
    }

    public ItemDTO getByName(ItemSearchCriteria criteria) {
        String name = criteria.getName();
        Item item = itemRepository.findByName(name)
                .orElseThrow(() -> new ItemNotFoundException(String.valueOf(criteria.getId())));
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(item, itemDTO);
        if (item.getGroup() != null) {
            itemDTO.setGroupName(item.getGroup().getName());
        }

        return itemDTO;
    }

    public void setGroup(Long itemID, Long groupID) {
        Item item = itemRepository.findById(itemID)
                .orElseThrow(() -> new ItemNotFoundException(String.valueOf(itemID)));
        Group group = groupRepository.findById(groupID)
                .orElseThrow(NoSuchElementException::new);
        item.setGroup(group);
        itemRepository.save(item);
    }

    private List<ItemDTO> mapToItemDTOs(List<Item> items) {
        List<ItemDTO> itemDTOs = new ArrayList<>();

        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO();
            BeanUtils.copyProperties(item, itemDTO);
            if(item.getGroup()!=null) {
                itemDTO.setGroupName(item.getGroup().getName());
            }
            itemDTOs.add(itemDTO);
        }

        return itemDTOs;
    }

    private void validateSearch(ItemSearchCriteria criteria) {

        Integer limit = criteria.getLimit();
        Integer offset = criteria.getOffset();
        String sort = criteria.getSort();
        String orderByFieldName = criteria.getOrderByFieldName();
        String message =  "limit = " + limit + ", offset = " + offset +
                ", sort = " + sort + ", orderByFieldName = " + orderByFieldName;

        if(limit<0 || offset<0 || (!sort.equalsIgnoreCase("ASC") && !sort.equalsIgnoreCase("DESC"))) {
            throw new ItemSearchIllegalArgumentException(message);
        }

        //This part of code checks all declared fields in Item.class
        Field[] fields = Item.class.getDeclaredFields();
        boolean fieldIsExists = false;
        for (Field f : fields) {
            String fieldName = f.getName();
            if(orderByFieldName.equalsIgnoreCase(fieldName)) {
                fieldIsExists = true;
            }
        }
        if(!fieldIsExists) {
            throw new ItemSearchIllegalArgumentException("Field Name : " + orderByFieldName + " DOES NOT EXIST");
        }
    }
}
