package bean;

import bean.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);

        // bean
        UserService userService = applicationContext.getBean("userService", UserService.class);

        // 获取userService类中的方法
        for (Method method : userService.getClass().getMethods()) {
            // 遍历查看是否有@PostConstruct注解
            if (method.isAnnotationPresent(PostConstruct.class)) {
                // 执行
                method.invoke(userService, xxxxx);
            }
            
        }
        userService.test();

    }
}
