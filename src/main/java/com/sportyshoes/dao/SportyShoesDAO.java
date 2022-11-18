package com.sportyshoes.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.bean.Administrator;
import com.sportyshoes.bean.ProductCategory;
import com.sportyshoes.bean.Products;
import com.sportyshoes.bean.PurchaseReport;
import com.sportyshoes.bean.UserList;

@Repository
@Transactional
public class SportyShoesDAO {
	@Autowired
	EntityManager entityManager;

	public JSONObject adminValidation(String adminUserId, String password) {
		String result = null;
		JSONObject FinalObject = new JSONObject();
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Administrator> cq = cb.createQuery(Administrator.class);
		Root<Administrator> root = cq.from(Administrator.class);
		Predicate adminIDPredicate = cb.equal(root.get("adminUserId"), adminUserId);
		Predicate adminPassword = cb.equal(root.get("adminPassword"), password);
		Predicate adminValidationCheck = cb.and(adminIDPredicate, adminPassword);
		cq.where(adminValidationCheck);
		List<Administrator> resultlist = entityManager.createQuery(cq).getResultList();
		if (resultlist.isEmpty()) {
			result = "Invaild Username or Password";
		} else {
			result = "success";
			int adminId = resultlist.get(0).getAdminId();
			List<UserList> userList = getAllUsers();
			List<PurchaseReport> PurchaseReportList = getAllPurchaseReport();
			List<Products> productsList = getAllProductDetails();
			JSONArray userListJsonArray = new JSONArray();
			JSONArray purchasedReportJsonArray = new JSONArray();
			JSONArray productDetailsJsonArray = new JSONArray();
			for (UserList userLists : userList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("UserName", userLists.getUserName());
				jsonObject.put("UserEmail", userLists.getUserEmail());
				userListJsonArray.put(jsonObject);
			}
			for (PurchaseReport PurchaseReportLists : PurchaseReportList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("PurchasedDate", simpleDateFormat.format(PurchaseReportLists.getPurchaseDate()));
				jsonObject.put("PurchasedUser", PurchaseReportLists.getUser().getUserName());
				jsonObject.put("PurchasedUserEmailId", PurchaseReportLists.getUser().getUserEmail());
				jsonObject.put("PurchasedProductName", PurchaseReportLists.getProducts().getProductName());
				jsonObject.put("PurchasedProductType",
						PurchaseReportLists.getProducts().getProduct().getProductCategoryName());
				jsonObject.put("PurchasedPrice", PurchaseReportLists.getProducts().getProductPrice());
				purchasedReportJsonArray.put(jsonObject);
			}
			for (Products Products : productsList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("ProductID", Products.getProductId());
				jsonObject.put("ProductName", Products.getProductName());
				jsonObject.put("ProductPrice", Products.getProductPrice());
				jsonObject.put("ProdcutCategory", Products.getProduct().getProductCategoryName());
				jsonObject.put("ProdcutCategoryID", Products.getProduct().getProductCategoryID());
				productDetailsJsonArray.put(jsonObject);
			}
			FinalObject.put("Result", result);
			FinalObject.put("adminId", adminId);
			FinalObject.put("ProductDetails", productDetailsJsonArray);
			FinalObject.put("UserList", userListJsonArray);
			FinalObject.put("PurchaseReport", purchasedReportJsonArray);
		}
		return FinalObject;
	}

	public List<Products> getAllProductDetails() {
		return entityManager.createNamedQuery("Product.getProducts", Products.class).getResultList();
	}

	public List<UserList> getAllUsers() {
		return entityManager.createNamedQuery("userList.findAll", UserList.class).getResultList();
	}

	public List<PurchaseReport> getAllPurchaseReport() {
		return entityManager.createNamedQuery("PurchaseList.findAll", PurchaseReport.class).getResultList();
	}

	public String updateProduct(int productId, String newProductValue, long newProductPrice) {
		String updateStatus;
		try {
			Products updateProduct = entityManager.find(Products.class, productId);
			updateProduct.setProductName(newProductValue);
			updateProduct.setProductPrice(newProductPrice);
			updateStatus = "Succesful";
		} catch (Exception e) {
			e.printStackTrace();
			updateStatus = "Unsuccesful";
		}
		return updateStatus;
	}

	public JSONObject getProductCategories() {
		List<ProductCategory> productCategories = entityManager
				.createNamedQuery("ProductCategory.findAll", ProductCategory.class).getResultList();
		JSONArray productCategoryJsonArray = new JSONArray();
		JSONObject FinalObject = new JSONObject();
		for (ProductCategory productCategory : productCategories) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ProductCategoryID", productCategory.getProductCategoryID());
			jsonObject.put("ProductCategoryName", productCategory.getProductCategoryName());
			productCategoryJsonArray.put(jsonObject);
		}
		FinalObject.put("ProductCategories", productCategoryJsonArray);
		return FinalObject;
	}

	public String addNewProduct(String productName, long newProductPrice, int productCategoryId) {
		String addProductStatus;
		try {
			ProductCategory productCategory = entityManager.find(ProductCategory.class, productCategoryId);
			Products newProduct = new Products(productName, newProductPrice, productCategory);
			entityManager.persist(newProduct);
			addProductStatus = "Succesful";
		} catch (Exception e) {
			e.printStackTrace();
			addProductStatus = "Unsuccesful";
		}
		return addProductStatus;
	}

	public JSONObject getAllDetails() {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		JSONObject FinalObject = new JSONObject();
		List<UserList> userList = getAllUsers();
		List<PurchaseReport> PurchaseReportList = getAllPurchaseReport();
		List<Products> productsList = getAllProductDetails();
		JSONArray userListJsonArray = new JSONArray();
		JSONArray purchasedReportJsonArray = new JSONArray();
		JSONArray productDetailsJsonArray = new JSONArray();
		for (UserList userLists : userList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("UserName", userLists.getUserName());
			jsonObject.put("UserEmail", userLists.getUserEmail());
			userListJsonArray.put(jsonObject);
		}
		for (PurchaseReport PurchaseReportLists : PurchaseReportList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("PurchasedDate", simpleDateFormat.format(PurchaseReportLists.getPurchaseDate()));
			jsonObject.put("PurchasedUser", PurchaseReportLists.getUser().getUserName());
			jsonObject.put("PurchasedUserEmailId", PurchaseReportLists.getUser().getUserEmail());
			jsonObject.put("PurchasedProductName", PurchaseReportLists.getProducts().getProductName());
			jsonObject.put("PurchasedProductType",
					PurchaseReportLists.getProducts().getProduct().getProductCategoryName());
			jsonObject.put("PurchasedPrice", PurchaseReportLists.getProducts().getProductPrice());
			purchasedReportJsonArray.put(jsonObject);
		}
		for (Products Products : productsList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ProductID", Products.getProductId());
			jsonObject.put("ProductName", Products.getProductName());
			jsonObject.put("ProductPrice", Products.getProductPrice());
			jsonObject.put("ProdcutCategory", Products.getProduct().getProductCategoryName());
			jsonObject.put("ProdcutCategoryID", Products.getProduct().getProductCategoryID());
			productDetailsJsonArray.put(jsonObject);
		}
		FinalObject.put("ProductDetails", productDetailsJsonArray);
		FinalObject.put("UserList", userListJsonArray);
		FinalObject.put("PurchaseReport", purchasedReportJsonArray);
		return FinalObject;
	}

	public String changeAdminPassword(int adminId, String oldPassword, String newPassword) {
		String passwordChangeStatus;
		try {
			Administrator changeAdminPassword = entityManager.find(Administrator.class, adminId);
			String oldPass = changeAdminPassword.getAdminPassword();
			if (oldPass.equalsIgnoreCase(oldPassword)) {
				changeAdminPassword.setAdminPassword(newPassword);
				passwordChangeStatus = "Password Changed Successfully";
			} else {
				passwordChangeStatus = "Old Password Does not Matches";
			}

		} catch (Exception e) {
			passwordChangeStatus = "Unscuccessful";
			e.printStackTrace();
		}
		return passwordChangeStatus;
	}
}
