package com.generactive.service;

import com.generactive.repository.UserRepository;
import com.generactive.service.crud.CRUD;
import com.generactive.transfer.Request;
import com.generactive.transfer.Response;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CRUD<Request, Response, Long> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
