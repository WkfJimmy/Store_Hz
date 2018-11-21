package cn.wkf.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wkf.store.entity.ResponseResult;
import cn.wkf.store.service.ex.InsertDataException;
import cn.wkf.store.service.ex.ServiceException;
import cn.wkf.store.service.ex.UsernameConflictException;

public abstract class BaseController {

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult handleException(Exception e) {
//		判断捕获到的异常是什么类型
		if(e instanceof UsernameConflictException) {
//			用户名冲突，例如用户名被占用，注册失败
			return new ResponseResult(401,e);
		}else if(e instanceof InsertDataException){
//			注册时遇到未知错误
			return new ResponseResult(402,e);
		}else{
//			其他没有捕获到的异常
			return new ResponseResult(600,e);
		}
	}
}
