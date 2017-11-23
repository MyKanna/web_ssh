package ch.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import ch.shop.user.vo.User;

public class UserDao extends HibernateDaoSupport {
	
	//按username查询
	public User findByUsername(String username){
		String hql = "from User where username =?";
		List<User> list =(List<User>) this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size()>0){
			return list.get(0);
			
		}
		return null;
	}

	public void regist(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	public User findByCode(String code) {
		String hql = "from User where code =?";
		List<User> list =(List<User>) this.getHibernateTemplate().find(hql, code);
		if(list != null && list.size()>0){
			return list.get(0);
			
		}
		return null;
	}
	//修改
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
		
	}

	public User login(User user) {
		String sql = "from User where username = ? and password = ? and state =?";
		List<User> list =(List<User>) this.getHibernateTemplate().find(sql, user.getUsername(),user.getPassword(),1);
		if(list != null && list.size()>0){
			return list.get(0);
			
		}
		return null;
	}
}
