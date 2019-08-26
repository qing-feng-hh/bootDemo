package com.controller;

import com.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: hehui
 * Date: 2019/8/27
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestControllerTest {

    @Autowired
    private TestController testController;

    @Test
    public void testAdd() {
        Person person = new Person();
        testController.testAdd(person);
    }

    @Test
    public void testUpdate() {
        Person person = new Person();
        person.setName("1");
        testController.testUpdate(person);
    }
}
