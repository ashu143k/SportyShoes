package com.sportyshoes.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.dao.SportyShoesDAO;

@Service
public class SportyShoesService {
	@Autowired
	SportyShoesDAO sportyShoesDao;

	public JSONObject adminValidation(String adminUserId, String password) {
		return sportyShoesDao.adminValidation(adminUserId, password);
	}
	public String updateProduct(int productId, String newProductValue, long newProductPrice) {
		return sportyShoesDao.updateProduct(productId, newProductValue,newProductPrice);
	}
	public JSONObject getProductCategories() {
		return sportyShoesDao.getProductCategories();
	}
	public String addNewProduct(String productName, long newProductPrice, int productCategoryId) {
		return sportyShoesDao.addNewProduct(productName, newProductPrice,productCategoryId);
	}
	public JSONObject getAllDetails() {
		return sportyShoesDao.getAllDetails();
	}
	public String changeAdminPassword(int adminId, String oldPassword, String newPassword) {
		return sportyShoesDao.changeAdminPassword(adminId,oldPassword, newPassword);
	}
}
