package com.example.Template.Method.Pattern.abstraction;

import java.util.List;

public interface AbstractBaseMapper<T> {
    default List<T> selectOne(String id) {
        List<T> result = executeSelectOne(id);
        postSelect(result);
        return result;
    }

    List<T> executeSelectOne(String id);

    default void postSelect(List<T> result) {
        // default implementation does nothing
    }

}
