package cn.wkf.store.service;

import cn.wkf.store.entity.User;
import cn.wkf.store.service.ex.InsertDataException;
import cn.wkf.store.service.ex.UsernameConflictException;

public interface IUserService {

	/**
	 * 处理用户注册
	 * @param user 用户数据
	 * @return 成功注册的数据
	 * @throws UsernameConflictException 当出现用户名被占用
	 * @throws InsertDataException 插入数据遇到错误
	 */
	User reg(User user) throws UsernameConflictException,InsertDataException;
}
