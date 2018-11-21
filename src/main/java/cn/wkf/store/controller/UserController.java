package cn.wkf.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wkf.store.entity.ResponseResult;
import cn.wkf.store.entity.User;
import cn.wkf.store.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/handle_reg.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult reg(
			@RequestParam("username") String username,@RequestParam("password") String password,
			String phone,String email
//			,@RequestParam(value="gender",required=false,defaultValue="1") Integer gender
			) {
//		将5个参数封装到User对象中
		User user = new User(username,password,phone,email, null);
		System.out.println("username:"+username+"password:"+password);
//		调用业务层的注册方法
		userService.reg(user);
//		返回ResponseResult对象
		return new ResponseResult();
	}
}
