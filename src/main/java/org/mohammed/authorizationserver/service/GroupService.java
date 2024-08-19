package org.mohammed.authorizationserver.service;

import jakarta.transaction.Transactional;
import org.mapstruct.Named;
import org.mohammed.authorizationserver.dto.GroupPostDto;
import org.mohammed.authorizationserver.exception.GroupAlreadyExistsException;
import org.mohammed.authorizationserver.exception.GroupNotFoundException;
import org.mohammed.authorizationserver.mapper.GroupMapper;
import org.mohammed.authorizationserver.model.Group;
import org.mohammed.authorizationserver.repository.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Named("findById")
    public Group findById(Long id) {
        var group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new GroupNotFoundException("Group with id: " + id + " does not exist");
        }
        return group.get();
    }

    public Group findByName(String name) {
        var group = groupRepository.findByName(name);
        if (group.isEmpty()) {
            throw new GroupNotFoundException("Group with name: " + name + " does not exist");
        }
        return group.get();
    }

    public Page<Group> findAll(int page, int size) {
        return groupRepository.findAll(PageRequest.of(page, size));
    }

    public Group create(GroupPostDto dto) {
        if (groupRepository.findByName(dto.name()).isPresent()) {
            throw new GroupAlreadyExistsException("Group " + dto.name() + " already exists");
        }
        return groupRepository.save(groupMapper.toEntity(dto));
    }

    public Group update(GroupPostDto dto, Long id) {
        var group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new GroupNotFoundException("Group with id: " + id + " does not exist");
        }
        var updatedEntity = group.map(g -> {
            g.setName(dto.name());
            return groupRepository.save(g);
        });
        return updatedEntity.get();
    }

    public void delete(Long id) {
        var group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new GroupNotFoundException("Group with id: " + id + " does not exist");
        }
        groupRepository.delete(group.get());
    }

}
