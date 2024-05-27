package com.example.Template.Method.Pattern.mapper;

import com.example.Template.Method.Pattern.abstraction.AbstractBaseMapper;
import com.example.Template.Method.Pattern.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends AbstractBaseMapper<User> {
    @Override
    @Select("SELECT * FROM users WHERE id = #{id}")
    List<User> executeSelectOne(String id);

    @Override
    default void postSelect(List<User> user) {
        // Log the operation
        System.out.println("Successfully selected " + user.size() + " users.");

    }

}
