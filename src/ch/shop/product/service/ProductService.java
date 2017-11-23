package ch.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ch.shop.product.dao.ProductDao;
import ch.shop.product.vo.Product;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		return productDao.findHot();
	}
	
}
