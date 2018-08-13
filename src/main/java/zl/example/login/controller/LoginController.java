package zl.example.login.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zl.example.login.pojo.User;
import zl.example.login.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/login",method={RequestMethod.POST})
	public String login(String username,String password,String tokenValidate,HttpSession session,HttpServletResponse response,HttpServletRequest request) throws InterruptedException{
		
		//先验证token,比较form隐藏域的token和session域的token值是否一致，不一致表示重复提交表单了
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
		}
		
		User isLogin = userService.loginByNameAndPass(username, password);
				if (isLogin!=null) {
					String uidStr=UUID.randomUUID().toString();
					String token=uidStr+"_"+String.valueOf(isLogin.getId());
					session.setAttribute("user", isLogin);
					session.setAttribute("token", token);
					Cookie cookie=new Cookie("tokenVal", token);
					//cookie.setDomain(".jszx.com");//设置在哪个域名下可访问，必须以.开头
					//cookie.setPath("/");//在同一台应用服务器下的所有应用都可以用改cookie
					cookie.setPath(request.getContextPath());//只在该应用下可以使用cookie
					cookie.setMaxAge(1000*60*30);//30分钟
					response.addCookie(cookie);
					Thread.sleep(3000);//模拟网络延迟，测试表单重复提交
					return "redirect:index.jsp";
				}
				return "redirect:login.jsp";
	}
	
	@RequestMapping(value="/login",method={RequestMethod.GET})
	public String login(HttpSession session,HttpServletRequest request){
		if(isSameCookieSession(session,request)){
			return "redirect:index.jsp";
		}
		return "redirect:login.jsp";
	}
	
	@RequestMapping(value="/",method={RequestMethod.GET})
	public String loginPage(HttpSession session,HttpServletRequest request){
		/*if (session.getAttribute("user")!=null) {
			return "redirect:index.jsp";
		}*/
		/*String token=(String) session.getAttribute("token");
		if (!StringUtils.isEmpty(token)) {
			return "redirect:index.jsp";
		}
		return "redirect:login.jsp";*/
		if(isSameCookieSession(session,request)){
			return "redirect:index.jsp";
		}
		return "redirect:login.jsp";
	}
	
	//cookie中的token和session中的是否一致
	public boolean isSameCookieSession(HttpSession session,HttpServletRequest request){
		String token=(String) session.getAttribute("token");
		
		String cookieToken="";
		
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("tokenVal")) {
				cookieToken=cookie.getValue();
				break;
			}
		}
		
		if (!StringUtils.isEmpty(token)&&token.equals(cookieToken)) {
			return true;
		}
		return false;
	}
}
