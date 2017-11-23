package ch.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ch.shop.category.dao.CategoryDao;
import ch.shop.category.vo.Category;

@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// TODO 查找全部的一级分类
		return categoryDao.findAll();
	}
	
}
