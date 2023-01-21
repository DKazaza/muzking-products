package com.example.muzkingproducts.DAO;

import com.example.muzkingproducts.DAO.ProductDAO;
import com.example.muzkingproducts.Exceptions.ProductDAOException;
import com.example.muzkingproducts.Product;

import java.util.ArrayList;
import java.util.Iterator;


public class ProductSimpleDAO implements ProductDAO {

    private final ArrayList<Product> products = new ArrayList<Product>();
    @Override
    public String addProduct(Product product) {
        products.add(product);
        return product.getId();
    }

    @Override
    public void updateProduct(Product product) {
        Product oldProduct = getProduct(product.getId());
        if(oldProduct!=null){
            oldProduct.setName(product.getName());
            oldProduct.setCount(product.getCount());
            oldProduct.setBuy_price(product.getBuy_price());
            oldProduct.setRrc_price(product.getRrc_price());
        }
    }

    @Override
    public void deleteProduct(String productId) {
        for(Iterator<Product> it = products.iterator(); it.hasNext();) {
            Product prd = it.next();
            if(prd.getId().equals(productId)) {
                it.remove();
            }
        }
    }

    @Override
    public Product getProduct(String productId) {
        for(Product product : products){
            if(product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Product> findProducts() {
        return products;
    }

    @Override
    public String lastIndex() throws ProductDAOException {
        return null;
    }

}
