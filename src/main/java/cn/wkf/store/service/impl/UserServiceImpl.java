package cn.wkf.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.wkf.store.entity.User;
import cn.wkf.store.mapper.UserMapper;
import cn.wkf.store.service.IUserService;
import cn.wkf.store.service.ex.InsertDataException;
import cn.wkf.store.service.ex.UsernameConflictException;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Autowired 
	private UserMapper userMapper;
//	实现执行注册业务的抽象方法
	public User reg(User user) throws UsernameConflictException, InsertDataException {
		// 根据用户名查询用户信息
		User result = userMapper.getUserByUsername(user.getUsername());
		// 判断是否查询到数据
		if(result == null) {
			// 否：用户名可用，执行注册
			insert(user);
			return user;
		}
		// 是：用户名已经被占用，抛出UsernameConflictException
		throw new UsernameConflictException("用户名"+user.getUsername()+"已经被占用");
	}
	
//	拓展注册信息保存方法insert
	public void insert(User user) throws InsertDataException{
		// 加密密码
		String salt = getRandomSalt();
		String password = user.getPassword();
		String md5Password = getEncrpytedPassword(password, salt);
		user.setPassword(md5Password);
		// 设置用户数据的日志
		Date now = new Date();
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(now);
		user.setCreatedUser(user.getUsername());
		user.setCreatedTime(now);
		// 执行注册
		Integer rows = userMapper.insert(user);
		if(rows != 1) {
			throw new InsertDataException("用户注册的时候遇到未知错误，详情联系管理员");
		}
	}
//  获取随机的盐值
	private String getRandomSalt() {
//		UUID是根据时间、硬件信息等运算得到的36位长度的随机字符串；
		return UUID.randomUUID().toString().toUpperCase();
	}
//	获取加密后的密码
	private String getEncrpytedPassword(String src,String salt) {
		// 将原密码加密
		src = md5(src);
		// 将盐加密
		salt = md5(salt);
		// 将2次加密结果拼接，再加密
		String result = md5(src+salt);
		// 将以上结果再加密5次
		for(int i = 0 ; i < 5 ; i++) {
			result = md5(result);
		}
		// 返回
		return result;
	}
//	使用MD5算法对数据进行加密
	private String md5(String src) {
//		Spring框架提供了`DigestUtils`工具类，
//		其中的静态方法`String md5DigestAsHex(byte[])`可以将原密码的字节数据加密得到32位长度的十六进制字符串
		return DigestUtils.md5DigestAsHex(src.getBytes()).toUpperCase();
	}
}
