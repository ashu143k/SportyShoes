package com.sportyshoes.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.service.SportyShoesService;

@RestController
public class ProductController {
	@Autowired
	SportyShoesService sportyShoesService;

	@RequestMapping(value = "/productUpdate", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateProduct(@RequestParam("productId") int productId,
			@RequestParam("newProductValue") String newProductValue,
			@RequestParam("newProductPrice") long newProductPrice) {
		String productUpdateStatus = sportyShoesService.updateProduct(productId, newProductValue, newProductPrice);
		return new ResponseEntity<Object>(productUpdateStatus, HttpStatus.OK);
	}

	@RequestMapping(value = "/getProductCategory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getProductCategory() {
		JSONObject productCategoriesJson = sportyShoesService.getProductCategories();
		return new ResponseEntity<Object>(productCategoriesJson.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllDetails", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getAllDetails() {
		JSONObject allDetailsJson = sportyShoesService.getAllDetails();
		return new ResponseEntity<Object>(allDetailsJson.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/addNewProduct", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addNewProduct(@RequestParam("newProductName") String productName,
			@RequestParam("newProductPrice") long newProductPrice,
			@RequestParam("productCategoryId") int productCategoryId) {
		String addNewProduct = sportyShoesService.addNewProduct(productName, newProductPrice, productCategoryId);
		return new ResponseEntity<Object>(addNewProduct, HttpStatus.OK);
	}
}
