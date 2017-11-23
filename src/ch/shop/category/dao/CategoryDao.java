package ch.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import ch.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{
	//查询出所有一级分类方法
	public List<Category> findAll() {
		String hql ="from Category ";
		List<Category> cList = (List<Category>) this.getHibernateTemplate().find(hql);
		if(cList != null && cList.size()>0){
			return cList;
		}
		return null;
	}

}
