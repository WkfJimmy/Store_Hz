package cn.wkf.store.mapper;

import cn.wkf.store.entity.User;

public interface UserMapper {
	/**
	 * 向数据库中插入注册信息
	 * @param user 用户输入的信息
	 * @return 受影响的行数
	 */
	Integer insert(User user);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户注册的用户名
	 * @return 与用户名匹配数据，如果没有匹配到数据则返回null
	 */
	User getUserByUsername(String username);

}
