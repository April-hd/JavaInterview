package com.hut;

import com.hut.dao.UserMongoDao;
import com.hut.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    @Autowired
    private UserMongoDao userMongoDao;

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> userList = userMongoDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
