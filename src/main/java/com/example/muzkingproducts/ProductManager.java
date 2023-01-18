package com.example.muzkingproducts;

import com.example.muzkingproducts.DAO.ProductDAO;
import com.example.muzkingproducts.DAO.ProductDAOFactory;
import com.example.muzkingproducts.Exceptions.ProductDAOException;
import com.example.muzkingproducts.Exceptions.ProductManagerException;

import java.util.ArrayList;
import java.util.List;

public class ProductManager{
    private final ProductDAO dao;

    public ProductManager() {
        dao = ProductDAOFactory.getProductDAO();
    }
    public String addProduct(Product product) throws ProductManagerException {
        try{
        return dao.addProduct(product);
        } catch (ProductDAOException ex) {
            throw new ProductManagerException(ex);
        }
    }
    public void updateProduct(Product product) throws ProductManagerException {
        try {
            dao.updateProduct(product);
        }
        catch (ProductDAOException ex) {
            throw new ProductManagerException(ex);
        }
    }
    public void deleteProduct(String productId) throws ProductManagerException {
        try {
            dao.deleteProduct(productId);
        }
        catch (ProductDAOException ex) {
            throw new ProductManagerException(ex);
        }
    }
    public Product getProduct(String productId) throws ProductManagerException {
        try {
            return dao.getProduct(productId);
        }
        catch (ProductDAOException ex) {
            throw new ProductManagerException(ex);
        }
    }
    public ArrayList<Product> findProducts() throws ProductManagerException {
        try {
            return dao.findProducts();
        }
        catch (ProductDAOException ex) {
            throw new ProductManagerException(ex);
        }
    }
}
