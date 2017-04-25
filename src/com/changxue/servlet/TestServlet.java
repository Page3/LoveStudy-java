package com.changxue.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.changxue.dao.UserMapper;
import com.changxue.model.User;
import com.changxue.util.DBConnector;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SqlSession sqlSession = DBConnector.connectMybatis();
		
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = new User();
			user.setAvatar("测试3");
			user.setName("测试3");
			user.setUid("测试3");
			user.setPoint(0);
			user.setLevel(0);
			
			userMapper.insertSelective(user);
			sqlSession.commit();
			sqlSession.close();
			
			System.out.println(user.getName());
		}finally{
			sqlSession.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
