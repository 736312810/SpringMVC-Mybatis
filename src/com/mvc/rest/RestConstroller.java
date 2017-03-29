package com.mvc.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hmybatis.dao.UserMapper;
import com.hmybatis.model.User;

@Controller
public class RestConstroller {
	// mybatis初始化
	private UserMapper userMapper;
	private SqlSession sqlSession;

	// 使用spring配置注解
	@Autowired
	UserMapper userMapper2;

	public RestConstroller() {
	}

	@RequestMapping(value = "login")
	public ModelAndView myMethod(HttpServletRequest request, HttpServletResponse response, String user,
			ModelMap modelMap) throws Exception {

		user = new String(user.getBytes("iso8859-1"),"utf-8");
		// mybatis实例

		init();
//		if (userMapper.selectByPrimaryKey(1).getName().equals(user)) {
//			modelMap.put("loginUser", user);
//			return new ModelAndView("/login/hello", modelMap);
//		} else
//			return new ModelAndView("/welcome", modelMap);
		
		//添加数据
		User user1 = new User();
		user1.setName(user);
		userMapper.insertSelective(user1);
		sqlSession.commit();
		sqlSession.close();
		modelMap.put("loginUser", user);
		return new ModelAndView("/login/hello", modelMap);

		// 整合spring实例
		
//		  if (userMapper2.selectByPrimaryKey(1).getName().equals(user)) {
//		  modelMap.put("loginUser", user); return new
//		  ModelAndView("/login/hello", modelMap); } else return new
//		  ModelAndView("/welcome", modelMap);
		
		//Map传递参数实例
//		Map<String, Object> map = new HashMap<>();
//		map.put("id",2);
//		map.put("name", "lc");
//		userMapper2.insertMap(map);
		
		//获取自增长主键
//		User user1 = new User();
//		userMapper2.insertkey1(user1);
//		System.out.println(user1.getId());
//		User user2 = new User();
//		userMapper2.insertkey2(user2);
//		System.out.println(user2.getId());
		
		//使用$符号实例
//		String s = "查找结果为："+userMapper2.selectLike(user);
//		modelMap.put("loginUser", s);
//		return new ModelAndView("/login/hello", modelMap);
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String registPost() {
		return "/welcome";
	}

	// 获取对象信息
	public void init() throws IOException {
		String resource = "/conf/SqlMapConfig.xml";
		InputStream stream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
		sqlSession = sqlSessionFactory.openSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
	}
}