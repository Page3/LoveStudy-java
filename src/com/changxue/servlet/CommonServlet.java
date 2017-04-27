package com.changxue.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.changxue.dao.CourseMapper;
import com.changxue.model.Course;
import com.changxue.model.CourseExample;
import com.changxue.model.CourseExample.Criteria;
import com.changxue.util.DBConnector;
import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet implementation class CommonServlet
 */
@WebServlet("/common")
public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommonServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String oprateType=request.getParameter("oprateType");
		
		if(oprateType.equals("getList")){
			SqlSession sqlSession = DBConnector.connectMybatis();
			CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
			
			//查询到course表的所有数据，放在list中，之后对其进行处理
			CourseExample example1 = new CourseExample();
			Criteria criteria1 = example1.createCriteria();
			criteria1.andSchoolIsNotNull();
			List<Course> list = courseMapper.selectByExample(example1);
			
			//存在学校相关的信息
			List<Map<String, Object>> result1 = new ArrayList<>();
			List<String> schools = new ArrayList<String>();
			for(Course temp:list)
			{
				String school = temp.getSchool();
				schools.add(school);
			}
			HashSet h = new HashSet(schools);
			schools.clear();
			schools.addAll(h);
			
			//分别考察每个学校(一个map)，构造结果(map数组)
			for(String school:schools)
			{
				CourseExample example2 = new CourseExample();
				Criteria criteria2 = example2.createCriteria();
				criteria2.andSchoolEqualTo(school);
				List<Course> list2 = courseMapper.selectByExample(example2);
				
				//存放学院相关的信息
				List<Map<String, Object>> result2 = new ArrayList<>();
				List<String> colleges = new ArrayList<>();
				
				for(Course temp2:list2)
				{
					String college = temp2.getCollege();
					colleges.add(college);
				}
				HashSet h2 = new HashSet(colleges);
				colleges.clear();
				colleges.addAll(h2);
				
				//分别考察当前学校下的每一个学院（一个map），构造结果（map数组）
				for(String college:colleges)
				{
					CourseExample example3 = new CourseExample();
					Criteria criteria3 = example3.createCriteria();
					criteria3.andSchoolEqualTo(school);
					criteria3.andCollegeEqualTo(college);
					List<Course> list3 = courseMapper.selectByExample(example3);
					
					//存放课程相关的信息
					List<Map<String, Object>> result3 = new ArrayList();
					List<String> courses = new ArrayList();
					
					for(Course temp3:list3)
					{
						String course = temp3.getCourse();
						courses.add(course);
					}
					HashSet h3 = new HashSet(courses);
					courses.clear();
					courses.addAll(h3);
					
					//分别考察当前学院下的每一个课程（一个map），构造结果（map数组）
					for(String course:courses)
					{
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("name", course);
						result3.add(map);
					}
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", college);
					map.put("courses", result3);
					
					result2.add(map);
				}
				
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", school);
				map.put("colleges", result2);
				
				result1.add(map);
			}
			
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(result1));
			
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
