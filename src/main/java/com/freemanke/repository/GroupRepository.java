package com.freemanke.repository;

import com.freemanke.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
