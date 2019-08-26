package com.bean;

import com.validator.group.AddPerson;
import com.validator.group.UpdatePerson;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

/**
 * @author: hehui
 * Date: 2019/8/26
 * @Description:
 */
@Data
public class Person {
    @NotBlank(groups = {AddPerson.class})
    private String name;
    @NotBlank(groups = {UpdatePerson.class})
    private Integer id;
    @Range(min = 0,max = 2,message = "性别必须在[0,2]",groups={Default.class})
    private Integer age;
}
