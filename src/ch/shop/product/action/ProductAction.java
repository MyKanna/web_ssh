package ch.shop.product.action;

import com.opensymphony.xwork2.ActionSupport;

import ch.shop.product.service.ProductService;

public class ProductAction extends ActionSupport {
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
}
