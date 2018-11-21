import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.wkf.store.entity.User;
import cn.wkf.store.service.IUserService;

public class TestService {

	private AbstractApplicationContext ac;
	private IUserService UserService;
//	测试方法执行之前先执行
	@Before
	public void doBefore() {
		ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		UserService = ac.getBean("userService", IUserService.class);
	}
//	测试方法执行之后执行
	@After
	public void After() {
		ac.close();
	}
//	插入数据测试
	@Test
	public void reg() {
		User user = new User();
		user.setUsername("rroot");
		user.setPassword("123");
		User result = UserService.reg(user);
		System.out.println("User:"+result);
	}
}
