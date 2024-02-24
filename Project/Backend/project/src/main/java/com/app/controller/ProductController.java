package com.app.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.ProductException;
import com.app.model.Product;
import com.app.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/products")
//	public ResponseEntity<Page<Product>> findProductByCatgeoryHandler(@RequestParam Integer minPrice,
//			@RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String stock,
//			@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
		public ResponseEntity<Page<Product>> findProductByCatgeoryHandler(@RequestParam Integer quantity){
		
		Page<Product> res = (Page<Product>) productService.getAllProduct();
		
		System.out.println("complete products");
		
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/products/search")
//	public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q){
//		List<Product> products = productService.searchProduct(q);
//		
//		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
//	}
	@CrossOrigin("*")
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable("productId") int productId) throws ProductException{
		System.out.println("in controller");
		Product product = productService.findProductById(productId);
		System.out.println(product.getId());
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}
	@CrossOrigin("*")
	@GetMapping("/get/")
	public List<Product> findAllProduct(){
		System.out.println("in find controller");
     List<Product> productPage = productService.getAllProduct();
     System.out.println(productPage);
     return productPage;
	}
	
}
