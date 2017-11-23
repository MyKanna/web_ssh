package ch.shop.user.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import ch.shop.user.utils.*;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ch.shop.user.service.UserService;
import ch.shop.user.vo.User;
/**
 * 用户模块的方法
 * @author xunwu
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动
	private User user = new User();
	//注入service对象
	private UserService userService ;
	//验证码
	private VerifyCode vc ;
	//接收验证码
	private String verifyCode;
	
	/////******************************************************************************///
	
	
	public void setVc(VerifyCode vc) {
		this.vc = vc;
	}


	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}
	
	
	//验证码
	public String VerifyCode() throws IOException{
		BufferedImage img = vc.getImage();
		ServletActionContext.getRequest().getSession().setAttribute("session_vcode", vc.getText());
		VerifyCode.output(img, ServletActionContext.getResponse().getOutputStream());
		return NONE;
	}
	//退出登入
	public String loginOut(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginOut";
	}
	//登入方法
	public String login(){
		User existUser =userService.login(user);
		String session_vcode=(String) ServletActionContext.getRequest().getSession()
				.getAttribute("session_vcode");
		if(!verifyCode.equalsIgnoreCase(session_vcode)){
			//错误添加错误信息
			this.addActionError("验证码错误");
			return "login";
		}else{
			if(existUser==null){
			//登入失败
			this.addActionError("登入失败 ，请检查用户名、密码和是否激活");
			return "login";
			}else{
			//登入成功
			//将用户信息存到session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//跳转
			return "loginSuccess";
				}	
			}
	}
	
	//跳转login页面方法
	public String loginPage(){
		return	"loginPage";
	}
	//跳转到页面注册的方法
	public String registPage(){
		return "registPage";
		
	}
	/**
	 * ajax 校验用户名是否存在
	 * @return
	 */
	public String findByUsername() throws IOException{
		//调用service
		 User existUser = userService.findByUsername(user.getUsername());
		 //获得response对象向页面输出
		HttpServletResponse res =  ServletActionContext.getResponse();
		res.setContentType("text/html;charset=UTF-8");
		 if(existUser !=null){
			 //有这个用户	
			res.getWriter().println("<font color='red'>用户名已经存在</font>"); 
		 }else{
			res.getWriter().println("<font color='green'>用户名可以注册</font>");  
		 }
		return NONE;
		
	}
	
	/**
	 *用户注册的方法
	 */
	public String regist(){
		
		userService.regist(user);
		return "login";
		
	}
	
	/**
	 * 激活
	 */
	public String active(){
		User existUser = userService.findByCode(user.getCode());
		if(existUser == null){
			//激活码错误
			this.addActionMessage("激活码失败");
		}else{
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功");
		}
		return "msg";
		
	}
}
