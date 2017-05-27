package com;

import com.freemanke.repository.GroupRepository;
import com.freemanke.repository.UserGroupRepository;
import com.freemanke.repository.UserRepository;
import com.freemanke.entity.Group;
import com.freemanke.entity.User;
import com.freemanke.entity.UserGroup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Before
    public void beforeEach() {
        userGroupRepository.deleteAll();
        groupRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void should_return_user_given_user_id() {
        User user = User.builder().username("freeman").build();
        userRepository.save(user);

        User find = userRepository.findOne(user.getId());

        Assert.assertNotNull(find);
        Assert.assertEquals(0, find.getGroups().size());
    }

    @Test
    public void should_return_group_given_group_id() {
        Group group = Group.builder().name("group1").build();
        groupRepository.save(group);

        Assert.assertNotNull(groupRepository.findOne(group.getId()));
    }

    @Test
    public void should_return_user_and_user_groups_given_user_id() {
        Group groupFirst = Group.builder().name("group 01").build();
        Group groupSecond = Group.builder().name("group 02").build();
        groupRepository.save(groupFirst);
        groupRepository.save(groupSecond);
        User user = User.builder().username("1").build();
        userRepository.save(user);
        userGroupRepository.save(UserGroup.builder().userId(user.getId()).groupId(groupFirst.getId()).build());
        userGroupRepository.save(UserGroup.builder().userId(user.getId()).groupId(groupSecond.getId()).build());

        User findFirst = userRepository.findOne(user.getId());
        Assert.assertEquals(2, findFirst.getGroups().size());
    }
}
