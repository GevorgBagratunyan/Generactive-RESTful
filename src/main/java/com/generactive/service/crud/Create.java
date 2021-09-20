package com.generactive.service.crud;

public interface Create<Request, Response>{
    Response create(Request request);
}
