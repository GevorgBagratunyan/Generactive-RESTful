package com.generactive.exception.item;

public class ItemSearchIllegalArgumentException extends RuntimeException{

    public ItemSearchIllegalArgumentException(String message) {
        super(message + "\nLimit and offset must be grater or equal to 0." +
                " Sort type mus be in ASC and DESC. " +
                "The search-by-field name must be the same as the original field name");
    }


}
