package ch.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import ch.shop.product.vo.Product;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> findHot() {
		//离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//is_hot = 1 表示热门
		criteria.add(Restrictions.eq("is_hot", 1));
		//排序日期
		criteria.addOrder(Order.desc("padate"));
		//search
		List<Product> hotList =  (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 4);
		return hotList;
	}

}
