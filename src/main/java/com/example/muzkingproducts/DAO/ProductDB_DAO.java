package com.example.muzkingproducts.DAO;

import com.example.muzkingproducts.Exceptions.ProductDAOException;
import com.example.muzkingproducts.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductDB_DAO implements ProductDAO {

    private static final String SELECT
            = "SELECT id, name, count, price, rrc_price FROM product ORDER BY name";
    private static final String SELECT_ONE
            = "SELECT id, name, count, price, rrc_price FROM product WHERE id=?";
    private static final String INSERT
            = "INSERT INTO product (id, name, count, price, rrc_price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE
            = "UPDATE product SET name=?, count=?, price=?, rrc_price=? WHERE id=?";
    private static final String DELETE
            = "DELETE FROM product WHERE id=?";
    private static final String LAST
            = "SELECT MAX(id) FROM product";

    private ConnectionBuilder builder = new SimpleConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public String addProduct(Product product) throws ProductDAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT)) {
            pst.setString(1, product.getId());
            pst.setString(2, product.getName());
            pst.setString(3, product.getCount());
            pst.setString(4, product.getBuy_price());
            pst.setString(5, product.getRrc_price());
            pst.executeUpdate();

            return product.getId();
        }
        catch (Exception e){
            throw new ProductDAOException(e);
        }
    }

    @Override
    public void updateProduct(Product product) throws ProductDAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, product.getName());
            pst.setString(2, product.getCount());
            pst.setString(3, product.getBuy_price());
            pst.setString(4, product.getRrc_price());
            pst.setString(5, product.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new ProductDAOException(e);
        }

    }

    @Override
    public void deleteProduct(String productId) throws ProductDAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setString(1, productId);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new ProductDAOException(e);
        }
    }

    @Override
    public Product getProduct(String productId) throws ProductDAOException {
        Product product = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE);
            pst.setString(1, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product = fillProduct(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new ProductDAOException(e);
        }
        return product;
    }

    @Override
    public ArrayList<Product> findProducts() throws ProductDAOException {
        ArrayList<Product> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillProduct(rs));
            }
            rs.close();
        } catch (Exception e) {
            throw new ProductDAOException(e);
        }
        return list;
    }

    private Product fillProduct(ResultSet rs) throws SQLException {
        Product product = new Product(rs.getString("id"),
                rs.getString("name"),
                rs.getString("count"),
                rs.getString("price"),
                rs.getString("rrc_price"));

        return product;
    }
    public String lastIndex() throws ProductDAOException {
        String name = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(LAST);
             ResultSet rs = pst.executeQuery()) {
            while(rs.next()){
                name = rs.getString(1);
            }
        } catch (Exception e) {
            throw new ProductDAOException(e);
        }
        return name;
    }
}
