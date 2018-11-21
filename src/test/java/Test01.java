import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.wkf.store.entity.User;
import cn.wkf.store.mapper.UserMapper;

public class Test01 {

	private AbstractApplicationContext ac;
	private UserMapper userMapper;
	
//	测试方法执行之前先执行
	@Before
	public void doBefore() {
		ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		userMapper = ac.getBean("userMapper", UserMapper.class);
	}
//	测试方法执行之后执行
	@After
	public void After() {
		ac.close();
	}
//	插入数据测试
	@Test
	public void insert() {
		User user = new User();
		user.setUsername("root");
		user.setPassword("rootroot");
		Integer rows = userMapper.insert(user);
		System.out.println("Insert的rows："+rows);
	}
//	根据用户名查询用户数据
	@Test
	public void getUserByUsername() {
		String username = "root";
		User user = userMapper.getUserByUsername(username);
		System.out.println("根据用户名查询用户数据："+user.getPassword());
	}
}
