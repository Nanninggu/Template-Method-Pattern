package com.example.Template.Method.Pattern.abstraction;

import java.util.List;

/** 해당 인터페이스가 이 프로젝트에서
 * 템플릿 메소드의 역할을 수행한다. **/

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
