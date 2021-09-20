package com.generactive.service;

import com.generactive.repository.ItemRepository;
import com.generactive.service.crud.CRUD;
import com.generactive.transfer.Request;
import com.generactive.transfer.Response;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements CRUD<Request, Response,Long> {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Response create(Request request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Response get(Long id) {
        return null;
    }

    @Override
    public Response update(Long id, Request request) {
        return null;
    }
}
