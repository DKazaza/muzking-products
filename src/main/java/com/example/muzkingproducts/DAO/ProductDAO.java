package com.example.muzkingproducts.DAO;

import com.example.muzkingproducts.Exceptions.ProductDAOException;
import com.example.muzkingproducts.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductDAO {
    public String addProduct(Product product) throws ProductDAOException;
    public void updateProduct(Product product) throws ProductDAOException;
    public void deleteProduct(String productId) throws ProductDAOException;
    public Product getProduct(String productId) throws ProductDAOException;
    public ArrayList<Product> findProducts() throws ProductDAOException;
}
