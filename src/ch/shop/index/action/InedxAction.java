package ch.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ch.shop.category.service.CategoryService;
import ch.shop.category.vo.Category;
import ch.shop.product.service.ProductService;
import ch.shop.product.vo.Product;


/**
 * web-inf/index
 * @author xunwu
 *
 */
public class InedxAction extends ActionSupport  {
	private ProductService	productService;
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public String execute() throws Exception {
		//二级菜单
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		//热门商品
		List<Product> hotList =  productService.findHot();
		ActionContext.getContext().getValueStack().set("hotList", hotList);
		return "index";
	}
}
