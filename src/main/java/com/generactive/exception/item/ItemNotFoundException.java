package com.generactive.exception.item;

public class ItemNotFoundException extends RuntimeException{


    public ItemNotFoundException(String id) {
        super(String.format("Item by id :  {%s}  - Not found", id));
    }
}
