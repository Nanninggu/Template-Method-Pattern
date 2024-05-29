### **\# Template-Method-Pattern (템플릿 메소드 패턴) 정리**

### **1\. 흐름 정리**

이 프로젝트의 핵심 로직은 템플릿 메소드 패턴을 사용하여 공통적인 작업 흐름을 정의하고, 그 중 일부 단계를 서브클래스에서 구현하도록 하는 것이다.

아래의 업로드된 코드에서 보면 아래와 같다.  
AbstractBaseMapper.java에서 템플릿 메소드인 selectOne(String id)를 정의하고 있다.

이 메소드는 애플리케이션의 핵심 작업 흐름을 정의한다.  
이 메소드는 executeSelectOne(String id)와 postSelect(List<T> result) 메소드를 순서대로 호출한다.  
executeSelectOne(String id) 메소드는 AbstractBaseMapper에서는 빈 메소드로 정의되어 있습니다.  
그리고 이 메소드는 서브클래스에서 반드시 구현해야 하는 메소드이다.  
postSelect(List<T> result) 메소드는 AbstractBaseMapper에서 기본 구현을 제공하지만, 서브클래스에서 필요에 따라 재정의할 수 있는 메소드이다.  
UserMapper.java는 AbstractBaseMapper를 확장하여 executeSelectOne(String id)와 postSelect(List<User> user) 메소드를 구현하고 있다.  
이 메소드들은 데이터베이스에서 사용자를 조회하고 조회 후에 필요한 추가 작업을 수행하는 로직을 담고 있다.  
**따라서, 이 프로젝트의 핵심 로직은 템플릿 메소드 패턴을 사용하여 공통 작업 흐름을 정의하고, 서브클래스에서 필요한 메소드를 구현하는 것, 그리고 MyBatis를 사용하여 데이터베이스에서 사용자를 조회하는 것이다.**

### **2\. Work-Flow 정리**

1\. 사용자는 UserService를 통해 특정 사용자의 정보를 조회하려고 한다. 이를 위해 getUser(String id) 메소드를 호출한다.  
getUser(String id) 메소드는 UserMapper의 selectOne(String id) 메소드를 호출한다. 이 메소드는 템플릿 메소드 패턴을 사용하여 공통 작업 흐름을 정의한다.  
2\. selectOne(String id) 메소드는 먼저 executeSelectOne(String id) 메소드를 호출한다. 이 메소드는 UserMapper 인터페이스에서 구현되며, 데이터베이스에서 사용자를 조회하는 로직을 담고 있다.  
executeSelectOne(String id) 메소드가 호출된 후, postSelect(List<User> user) 메소드가 호출된다. 이 메소드는 UserMapper 인터페이스에서 기본 구현을 제공하지만, 필요에 따라 재정의할 수 있다. 이 메소드는 조회 작업 후에 필요한 추가 작업을 수행하는 로직을 담고 있다.  
3\. postSelect(List<User> user) 메소드에서는 조회된 사용자의 수를 로그로 출력한다.  
4\. 마지막으로, getUser(String id) 메소드는 조회된 사용자 정보를 반환한다.  
해당 워크플로우는 템플릿 메소드 패턴을 사용하여 공통 작업 흐름을 정의하고, 서브클래스에서 필요한 메소드를 구현하는 방식으로 동작하며, 이를 통해 코드의 재사용성을 높이고, 유지 보수를 용이하게 할 수 있다.

이 프로젝트에서는 템플릿 메소드 패턴을 사용하여 공통 작업 흐름을 정의하고 있습니다. 템플릿 메소드 패턴은 상위 클래스에서 공통 작업 흐름을 정의하고, 하위 클래스에서 이를 구체화하는 디자인 패턴입니다.

여기서 `AbstractBaseMapper` 인터페이스가 템플릿 메소드 패턴의 역할을 합니다. `AbstractBaseMapper` 인터페이스에서는 `selectOne(String id)` 메소드를 통해 공통 작업 흐름을 정의하고 있습니다. 이 메소드는 먼저 `executeSelectOne(String id)`를 호출하여 데이터를 조회하고, 그 후 `postSelect(List<T> result)`를 호출하여 조회 후의 작업을 수행합니다.

```java
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
```

`UserMapper` 인터페이스는 `AbstractBaseMapper`를 상속받아 `executeSelectOne(String id)`와 `postSelect(List<User> user)` 메소드를 구체화하고 있습니다. `executeSelectOne(String id)` 메소드에서는 사용자 정보를 데이터베이스에서 조회하는 로직을 구현하고, `postSelect(List<User> user)` 메소드에서는 조회된 사용자의 수를 로그로 출력하는 로직을 구현하고 있습니다.

```java
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
```

이렇게 템플릿 메소드 패턴을 사용하면 공통 작업 흐름을 한 곳에서 관리할 수 있어 코드의 재사용성을 높이고 유지 보수를 용이하게 할 수 있습니다.

끝
