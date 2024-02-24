package com.app.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.Exception.ProductException;
import com.app.model.Product;
import com.app.repositry.ProductRepository;
import com.app.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService{

	private ProductRepository productRepository;
	private UserService userService;
	
	public ProductServiceImplementation(ProductRepository productRepository,UserService userService ) {
		this.productRepository = productRepository;
		this.userService = userService;
	}
	
	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Product product = new Product();
		product.setName(req.getName());
		product.setBrand(req.getBrand());
		product.setDescription(req.getDescription());
		product.setQuantity(req.getQuantity());
		product.setPrice(req.getPrice());
		product.setImageUrl(req.getImageUrl());
		product.setDiscountPercent(req.getDiscountPercent());
		product.setDiscountPrice(req.getDiscountPrice());
		
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	@Override
	public String deleteProduct(int productId) throws ProductException {
		Product product = findProductById(productId);
	    productRepository.delete(product);
	    return "Product Deleted Successfully";

	}

	@Override
	public Product updateProduct(int productId, Product req) throws ProductException {
		Product product = findProductById(productId);
	
		if(req.getName()!=null) {
			product.setName(req.getName());
		}
		if(req.getDescription()!=null) {
			product.setDescription(req.getDescription());
		}
		if(req.getPrice()!=0) {
			product.setPrice(req.getPrice());
		}
		if(req.getQuantity()!=0) {
			product.setQuantity(req.getQuantity());
		}
		if(req.getBrand()!=null) {
			product.setBrand(req.getBrand());
		}
		if(req.getDiscountPrice()!=0) {
			product.setDiscountPrice(req.getDiscountPrice());
		}
		if(req.getDiscountPercent()!= 0) {
			product.setDiscountPercent(req.getDiscountPercent());
		}
		if(req.getRatings()!=null) {
			product.setRatings(req.getRatings());
		}
		if(req.getReviews()!=null) {
			product.setReviews(req.getReviews());
		}
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductException {
		Optional<Product> opt = productRepository.findById(productId);
		System.out.println(opt.get());
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("Product not found with id: "+ productId);
	}

	@Override
	public List<Product> findProductByCatgeory(String Catgeory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	  public List<Product> getAllProduct() {
		System.out.println("in  product service");
        List<Product> products = productRepository.findAll();
        System.out.println(products);
        return products;
    }

}
