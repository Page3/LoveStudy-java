package com.changxue.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.changxue.dao.UserMapper;
import com.changxue.model.User;
import com.changxue.util.DBConnector;
import com.changxue.util.MD5;

import net.sf.json.JSONObject;


/**
 * Servlet implementation class CallBackServlet
 */
@WebServlet("/callBack")
public class CallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallBackServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取code
		String code=request.getParameter("code");
		
		//通过code获取openID和access_token
		String url="https://api.weixin.qq.com/sns/oauth2/access_token"
				+ "?appid="+AuthUtil.APPID
				+ "&secret="+AuthUtil.APPSECRET
				+ "&code="+code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject=AuthUtil.doGetJson(url);
		String openid=jsonObject.getString("openid");
		String access_token=jsonObject.getString("access_token");
		
		//通过openID和access_token获取用户信息
		String getUserInfoUrl="https://api.weixin.qq.com/sns/userinfo"
				+ "?access_token="+access_token
				+ "&openid="+openid
				+ "&lang=zh_CN";
		JSONObject userInfo=AuthUtil.doGetJson(getUserInfoUrl);
		
		System.out.println("++++++++++"+userInfo);
		
		String stringJson=new String(userInfo.toString().getBytes(), "UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(stringJson); 
		System.out.println("__________"+stringJson);
		
		//将获取到的用户信息封装到usermodel中
		UserModel userModel=new UserModel(); 
		userModel.setOpenid(openid);
		userModel.setNickname(userInfo.getString("nickname"));
		if(userInfo.getString("sex").equals("2")) userModel.setSex("女");
		else if(userInfo.getString("sex").equals("1")) userModel.setSex("男");
		else userModel.setSex("未知");
		userModel.setLanguage(userInfo.getString("language"));
		userModel.setCity(userInfo.getString("city"));
		userModel.setProvince(userInfo.getString("province"));
		userModel.setCountry(userInfo.getString("country"));
		userModel.setHeadimgurl(userInfo.getString("headimgurl"));
		
		//将用户信息存放到数据库
		SqlSession sqlSession = DBConnector.connectMybatis();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setAvatar(userModel.getHeadimgurl());
		user.setName(userModel.getNickname());
		user.setUid(userModel.getOpenid());
		user.setPoint(0);
		user.setLevel(0);
		
		if(userMapper.selectByPrimaryKey(userModel.getOpenid())==null){
			try{
				userMapper.insertSelective(user);
				sqlSession.commit();
				System.out.println("commit");
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println("close");
				sqlSession.close();
			}
		}
		
		String s1 = "http://";
		String s2 = "myths.mythsman.com/login/"+userModel.getOpenid();
		Long ltime = new Date().getTime();
		String time = Long.toString(ltime);
		String code1 = Long.toString(ltime*2+1);
		String code2 = s2+code1;
		String signature = MD5.md5(code2);
		
		String clientUrl = s1+s2+"/"+time+"/"+signature;
		response.sendRedirect(clientUrl);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
