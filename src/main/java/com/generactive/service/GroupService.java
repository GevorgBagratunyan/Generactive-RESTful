package com.generactive.service;

import com.generactive.model.Group;
import com.generactive.repository.GroupRepository;
import com.generactive.service.crud.CRUD;
import com.generactive.service.dto.GroupDTO;
import com.generactive.service.util.HierarchyValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroupService implements CRUD<GroupDTO, Long> {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public GroupDTO create(GroupDTO groupDTO) {

        Group group = new Group();
        BeanUtils.copyProperties(groupDTO, group);
        return groupDTO;
    }

    @Override
    public GroupDTO get(Long id) {

        Group group = groupRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        GroupDTO groupDTO = new GroupDTO();
        BeanUtils.copyProperties(group, groupDTO);
        return groupDTO;
    }

    @Override
    public void update(GroupDTO groupDTO) {

        Group group = new Group();
        BeanUtils.copyProperties(groupDTO, group);
        groupRepository.save(group);
    }

    @Override
    public void delete(Long id) {

        groupRepository.deleteById(id);
    }

    public List<GroupDTO> getAll() {

        List<Group> groups = groupRepository.findAll();
        List<GroupDTO> groupDTOs = new ArrayList<>();
        for (Group group : groups) {
            GroupDTO groupDTO = new GroupDTO();
            BeanUtils.copyProperties(group, groupDTO);
            groupDTOs.add(groupDTO);
        }
        return groupDTOs;
    }

    public GroupDTO getByName(String name) {

        Group group = groupRepository.findByName(name);
        GroupDTO groupDTO = new GroupDTO();
        BeanUtils.copyProperties(group, groupDTO);
        return groupDTO;
    }

    public void setParent(Long groupId, Long parentIdToSet) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(NoSuchElementException::new);
        Group parent = groupRepository.findById(parentIdToSet)
                .orElseThrow(NoSuchElementException::new);

        if(HierarchyValidator.isValid(group, parentIdToSet)) {
            group.setParent(parent);
        } else throw new IllegalArgumentException("You can not set that parent to the group, " +
                "because it's out of allowed hierarchy");


        groupRepository.save(group);
    }
}
