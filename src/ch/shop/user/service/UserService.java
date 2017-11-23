package ch.shop.user.service;

import java.util.UUID;




import org.springframework.transaction.annotation.Transactional;

import ch.shop.user.dao.UserDao;
import ch.shop.user.utils.SendMailThread;
import ch.shop.user.vo.User;

@Transactional
public class UserService {
	private UserDao userDao;
	
	


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//根据username查询
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

		//注册
	public void regist(User user) {
		//存入到数据库
		user.setState(0);//未激活1是已经激活
		//去掉-  ；32位
		user.setCode(UUID.randomUUID().toString().replace("-", ""));
		userDao.regist(user);
		//发送激活邮件
		SendMailThread smt = new SendMailThread(user);
		smt.start();
	}

	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	public void update(User existUser) {
		userDao.update(existUser);
		
		
	}
	//登入
	public User login(User user) {;
		return  userDao.login(user);
	}
}
