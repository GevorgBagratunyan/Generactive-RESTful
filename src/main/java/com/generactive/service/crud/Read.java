package com.generactive.service.crud;


public interface Read<DTO, ID>{
    DTO get(ID id);
}
