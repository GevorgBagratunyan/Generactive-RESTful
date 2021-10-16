package com.generactive.controller;

import com.generactive.service.GroupService;
import com.generactive.service.dto.GroupDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    public GroupDTO get(@PathVariable Long id) {
        return groupService.get(id);
    }

    @GetMapping
    public List<GroupDTO> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/group/{name}")
    public GroupDTO getByName(@PathVariable String name) {
        return groupService.getByName(name);
    }

    @PostMapping
    public GroupDTO save(@RequestBody GroupDTO groupDTO) {
        return groupService.create(groupDTO);
    }

    @PutMapping("/group")
    public void update(@RequestBody GroupDTO groupDTO) {
        groupService.update(groupDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }

    @PutMapping
    public void setParent(@RequestParam Long groupId,
                          @RequestParam Long parentId) {
        groupService.setParent(groupId, parentId);
    }

}
