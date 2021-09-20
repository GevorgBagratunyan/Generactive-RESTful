package com.generactive.service.crud;


public interface Read<ID, Response>{
    Response get(ID id);
}
