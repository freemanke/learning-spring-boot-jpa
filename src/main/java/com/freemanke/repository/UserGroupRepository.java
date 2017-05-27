package com.freemanke.repository;

import com.freemanke.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
}
