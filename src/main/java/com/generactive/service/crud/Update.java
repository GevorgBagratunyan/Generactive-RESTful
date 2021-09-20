package com.generactive.service.crud;

public interface Update<ID,Request, Response>{
    Response update(ID id, Request request);
}
