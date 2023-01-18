package com.example.muzkingproducts.DAO;

public class ProductDAOFactory {
    public static ProductDAO getProductDAO() {
        try {
            Class dao = ProductDB_DAO.class;
            return (ProductDAO) dao.newInstance();
        } catch (InstantiationException | IllegalAccessException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
