package com.changxue.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.changxue.dao.FileMapper;
import com.changxue.dao.MyCollectionMapper;
import com.changxue.dao.MyUploadedMapper;
import com.changxue.dao.UserMapper;
import com.changxue.model.MyCollectionExample.Criteria;
import com.changxue.model.MyUploaded;
import com.changxue.model.MyUploadedExample;
import com.changxue.model.User;
import com.changxue.model.UserExample;
import com.changxue.model.File;
import com.changxue.model.MyCollection;
import com.changxue.model.MyCollectionExample;
import com.changxue.util.DBConnector;
import com.google.gson.Gson;

/**
 * Servlet implementation class User
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String oprateType=request.getParameter("oprateType");
		
		if("getFavourite".equals(oprateType)){
			
			String uid = request.getParameter("uid");
			
			SqlSession sqlSession = DBConnector.connectMybatis();
			MyCollectionMapper myCollectionMapper = sqlSession.getMapper(MyCollectionMapper.class);
			
			MyCollectionExample example = new MyCollectionExample();
			MyCollectionExample.Criteria criteria = example.createCriteria();
			criteria.andUidEqualTo(uid);
			List<MyCollection> myCollections = myCollectionMapper.selectByExample(example);
			
			List<File> files = new ArrayList<>();
			
			FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
			
			for(MyCollection myCollection:myCollections){
				String fid = myCollection.getFid();
				files.add(fileMapper.selectByPrimaryKey(fid));
			}
			
			Gson gson = new Gson();
			
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(files));
			
			return ;
			
		}else if("getUploaded".equals(oprateType)){
			
			String uid = request.getParameter("uid");
			
			SqlSession sqlSession = DBConnector.connectMybatis();
			MyUploadedMapper myUploadedMapper = sqlSession.getMapper(MyUploadedMapper.class);
			
			MyUploadedExample example = new MyUploadedExample();
			MyUploadedExample.Criteria criteria = example.createCriteria();
			criteria.andUidEqualTo(uid);
			List<MyUploaded> myUploadeds = myUploadedMapper.selectByExample(example);
			
			List<File> files = new ArrayList<>();
			
			FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
			
			for(MyUploaded myUploaded:myUploadeds){
				String fid = myUploaded.getFid();
				files.add(fileMapper.selectByPrimaryKey(fid));
			}
			
			Gson gson = new Gson();
			
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(files));
			
			return ;
			
		}else if("toggleFavourite".equals(oprateType)){
			
			String uid = request.getParameter("uid");
			String fid = request.getParameter("fid");
			
			SqlSession sqlSession = DBConnector.connectMybatis();
			MyCollectionMapper myCollectionMapper = sqlSession.getMapper(MyCollectionMapper.class);
			
			MyCollectionExample example = new MyCollectionExample();
			MyCollectionExample.Criteria criteria = example.createCriteria();
			criteria.andUidEqualTo(uid);
			criteria.andFidEqualTo(fid);
			List<MyCollection> myCollections = myCollectionMapper.selectByExample(example);
			
			if(myCollections.size()==0){
				MyCollection temp = new MyCollection();
				temp.setFid(fid);
				temp.setUid(uid);
				
				myCollectionMapper.insert(temp);
				sqlSession.commit();
				
				Gson gson = new Gson();
				
				PrintWriter pw = response.getWriter();
				pw.write(gson.toJson("收藏成功"));
				
				return ;
			
			}else{
				
				myCollectionMapper.deleteByExample(example);
				sqlSession.commit();
				
				Gson gson = new Gson();
				
				PrintWriter pw = response.getWriter();
				pw.write(gson.toJson("取消收藏成功"));
				
				return ;
			}
			
		}else if("getUser".equals(oprateType)){
			
			String uid = request.getParameter("uid");
			
			SqlSession sqlSession = DBConnector.connectMybatis();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			User user = userMapper.selectByPrimaryKey(uid);
			
			Gson gson = new Gson();
			
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(user));
			
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
