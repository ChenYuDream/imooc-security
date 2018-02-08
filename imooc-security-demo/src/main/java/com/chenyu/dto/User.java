package com.chenyu.dto;

import com.chenyu.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author yu_chen
 * @create 2018-02-08 16:34
 **/
@Data
public class User {

    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    @MyConstraint(message = "测试约束")
    @JsonView(UserSimpleView.class)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private String password;


    @JsonView(UserSimpleView.class)
    private String id;


    private Date birthDay;
}
