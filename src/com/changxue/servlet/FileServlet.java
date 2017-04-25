package com.changxue.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.changxue.dao.FileMapper;
import com.changxue.model.File;
import com.changxue.model.FileExample;
import com.changxue.model.FileExample.Criteria;
import com.changxue.util.DBConnector;
import com.google.gson.Gson;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/file")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String oprateType=request.getParameter("oprateType");
		
		if("getArticle".equals(oprateType)){
			String fid = request.getParameter("fid");
			
			SqlSession sqlSession = DBConnector.connectMybatis();
			FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
			
			File file = fileMapper.selectByPrimaryKey(fid);
			
			Gson gson = new Gson();
			
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(file));
			
			return ;
			
		}else if("getArticles".equals(oprateType)){
			String school = request.getParameter("school");
			String course = request.getParameter("course");
			String college = request.getParameter("college");
			
			SqlSession sqlSession = DBConnector.connectMybatis();
			FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
			
			FileExample example = new FileExample();
			Criteria criteria = example.createCriteria();
			criteria.andSchoolEqualTo(school);
			criteria.andCollegeEqualTo(college);
			criteria.andCourseEqualTo(course);
			
			List<File> list = fileMapper.selectByExample(example);
			
			Gson gson = new Gson();
			
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(list));
			
			return ;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
