package com.chenyu.controller;

import com.chenyu.dto.User;
import com.chenyu.dto.UserQueryCondition;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器
 *
 * @author yu_chen
 * @create 2018-02-08 16:32
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping
    public User createUser(@Valid @RequestBody User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        user.setId("1");
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> selectUser(UserQueryCondition userQueryCondition, @PageableDefault(page = 1, size = 20) Pageable pageable) {
        System.out.println(userQueryCondition);
        System.out.println(pageable);
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }


    /**
     * 按ID进行用户查询
     *
     * @param id
     * @return
     */
    @GetMapping("{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User selectUserById(@PathVariable("id") String id) {
        User user = new User();
        user.setUserName("tom");
        return user;
    }

    @DeleteMapping("{id:\\d+}")
    public int deleteUser(@PathVariable("id") String id) {
        System.out.println(id);
        return 0;
    }

    @PutMapping("{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setId("1");
        return user;
    }
}
