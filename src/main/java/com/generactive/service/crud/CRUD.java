package com.generactive.service.crud;

public interface CRUD<Request, Response, ID> extends
        Create<Request, Response>,
        Read<ID, Response>,
        Update<ID, Request, Response>,
        Delete<ID>{
    @Override
    Response create(Request request);

    @Override
    void delete(ID id);

    @Override
    Response get(ID id);

    @Override
    Response update(ID id, Request request);
}
