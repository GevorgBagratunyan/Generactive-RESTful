package com.generactive.service;

import com.generactive.repository.GroupRepository;
import com.generactive.service.crud.CRUD;
import com.generactive.transfer.Request;
import com.generactive.transfer.Response;
import org.springframework.stereotype.Service;

@Service
public class GroupService implements CRUD<Request, Response,Long> {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Response create(Request request) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Response get(Long aLong) {
        return null;
    }

    @Override
    public Response update(Long aLong, Request request) {
        return null;
    }
}
