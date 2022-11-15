package bean.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserService implements InitializingBean {

    @Autowired
    private OrderServie orderServie;

    private User defaultUser;

    @Override
    public void afterPropertiesSet() throws Exception {
            // mysql--------->user-------->defaultUser

    }

    //    @PostConstruct
//    public void a() {
//        // mysql--------->user-------->defaultUser
//
//    }

    public void test() {
        System.out.println("测试");
    }
}
