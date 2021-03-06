/**
 * 
 */
package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhailiang
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	/**
	 * 用户注册及图片上传
	 * @param user
	 * @param request
	 */
	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}
	
	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal User user) {
		return user;
	}

	/**
	 * 创建用户
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "创建用户")
	public User create(@Valid @RequestBody User user,BindingResult bindingResult) {

		if (bindingResult.hasErrors())
		{
			bindingResult.getAllErrors().forEach(error-> System.out.println(error.getDefaultMessage()));
		}
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	/**
	 * 修改用户信息
	 * @param user
	 * @param errors
	 * @return
	 */
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(
					e->{
						FieldError fieldError=(FieldError) e;
						String menssage=fieldError.getField()+" "+e.getDefaultMessage();
						System.out.println(menssage);
					}
			);
		}

		System.out.println("wzq："+user.getId());
		System.out.println("wzq："+user.getUsername());
		System.out.println("wzq："+user.getPassword());
		System.out.println("wzq："+user.getBirthday());

		user.setId("1");
		return user;
	}

	/**
	 * 删除用户
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}

	/**
	 * 查询用户服务
	 * @param condition
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
	public List<User> query(UserQueryCondition condition,
			@PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());

		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	/**
	 * 查询用户详情
	 * @param id
	 * @return
	 */
	@GetMapping("/{id:\\d+}") //正则表达式 表示只接收数字
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {
//		throw new RuntimeException("user not exist");
		System.out.println("进入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}

}
