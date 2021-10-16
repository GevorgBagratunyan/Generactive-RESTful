package com.generactive.service.util;

import com.generactive.model.Group;
import com.generactive.repository.GroupRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class HierarchyValidator {

    private static GroupRepository repository;

    public HierarchyValidator(GroupRepository repository) {
        this.repository = repository;
    }

    public static boolean isValid(Group group, Long parentIdToSet) {

         if(group.getSubGroups().isEmpty()) {
             return true;
         }

        Integer parentLvlToSet = 0;
        getLvl(parentIdToSet, parentLvlToSet);

        Integer currentParentLvl = 0;
        getLvl(group.getParent().getId(), currentParentLvl);

        if(parentLvlToSet==currentParentLvl) {
            return true;
        } else return false;
    }

    private static void getLvl(Long id, Integer lvl) {
        Group group = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        if(group.getParent()!=null){
            lvl++;
            Long parentId = group.getParent().getId();
            getLvl(parentId, lvl);
        }
    }
}
