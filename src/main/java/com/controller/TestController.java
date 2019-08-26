package com.controller;

import com.bean.Person;
import com.validator.group.AddPerson;
import com.validator.group.UpdatePerson;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author: hehui
 * Date: 2019/8/26
 * @Description:
 */
@Component
public class TestController {

    void testAdd(@Validated({AddPerson.class}) Person person){
        System.out.println("OK");
    }

    void testUpdate(@Validated({UpdatePerson.class}) Person person){
        System.out.println("OK");
    }

}
