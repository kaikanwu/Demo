package com.kk.firstappdemo.controller;


import com.kk.firstappdemo.domain.User;
import com.kk.firstappdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    private final UserRepository userRepository;


    /**
     * 这里是使用构造器的方式来注入，并标注 <code>@Autowired</code>
     * @param userRepository
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * 通过 Post 请求来存储用户
     * @param name
     * @return
     */
    @PostMapping("/person/save")
    public User save(@RequestParam String name) {

        User user = new User();
        user.setName(name);

        if (userRepository.save(user)) {
            System.out.printf("用户对象：%s 保存成功！\n", user);
        }

        return user;

    }
}
