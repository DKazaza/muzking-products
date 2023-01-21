package com.example.muzkingproducts;

import com.example.muzkingproducts.DAO.ProductSimpleDAO;
import com.example.muzkingproducts.Exceptions.ProductDAOException;
import com.example.muzkingproducts.Exceptions.ProductManagerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.util.ArrayList;
import java.util.Collection;


public class Controller {
    ObservableList<Product> products = FXCollections.observableArrayList();
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> countColumn;
    @FXML
    private TableColumn<Product, String> buyColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;

    @FXML
    private TextField addName;
    @FXML
    private TextField addCount;
    @FXML
    private TextField addBuy;
    @FXML
    private TextField addPrice;
    @FXML
    private Button addButton;
    @FXML
    private TextArea message;
    @FXML
    private Button delButton;
    private final ProductManager productManager = new ProductManager();
    private Product buffer = new Product();


    @FXML
    private void initialize() throws ProductManagerException {
        tableView.setEditable(true);
        tableView.setItems(products);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Product, String> evnt) {
                        ((Product) evnt.getTableView().getItems().get(
                                evnt.getTablePosition().getRow())
                        ).setName(evnt.getNewValue());
                        try {
                            productManager.updateProduct(tableView.getSelectionModel().getSelectedItem());
                        } catch (ProductManagerException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        countColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        countColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Product, String> evnt) {
                        ((Product) evnt.getTableView().getItems().get(
                                evnt.getTablePosition().getRow())
                        ).setCount(evnt.getNewValue());
                        try {
                            productManager.updateProduct(tableView.getSelectionModel().getSelectedItem());
                        } catch (ProductManagerException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        buyColumn.setCellValueFactory(new PropertyValueFactory<>("buy_price"));
        buyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        buyColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Product, String> evnt) {
                        ((Product) evnt.getTableView().getItems().get(
                                evnt.getTablePosition().getRow())
                        ).setBuy_price(evnt.getNewValue());
                        try {
                            productManager.updateProduct(tableView.getSelectionModel().getSelectedItem());
                        } catch (ProductManagerException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("rrc_price"));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Product, String> evnt) {
                        ((Product) evnt.getTableView().getItems().get(
                                evnt.getTablePosition().getRow())
                        ).setRrc_price(evnt.getNewValue());
                        try {
                            productManager.updateProduct(tableView.getSelectionModel().getSelectedItem());
                        } catch (ProductManagerException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        initData();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(!addName.getText().equals("")&&
                        !addCount.getText().equals("")&&
                        !addBuy.getText().equals("")&&
                        !addPrice.getText().equals("")
                ) {
                    try {
                        buffer.setId(Long.toString(parser()));
                    } catch (ProductDAOException ex) {
                        throw new RuntimeException(ex);
                    }
                    buffer.setName(addName.getText());
                    buffer.setCount(addCount.getText());
                    buffer.setBuy_price(addBuy.getText());
                    buffer.setRrc_price(addPrice.getText());
                    try {
                        productManager.addProduct(buffer);
                        products.add(buffer);
                    } catch (ProductManagerException ex) {
                        throw new RuntimeException(ex);
                    }
                    addName.clear();
                addCount.clear();
                addBuy.clear();
                addPrice.clear();
                message.setText("Успешно добавлено!");
                }
                else {
                    message.setText("Все поля должны быть заполнены!");
                }
            }
        });
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int select = tableView.getSelectionModel().getSelectedIndex();
                if(select >=0) {
                    try {
                        productManager.deleteProduct(tableView.getSelectionModel().getSelectedItem().getId());
                        tableView.getItems().remove(select);

                    } catch (ProductManagerException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });

    }

    private void initData() throws ProductManagerException {
        ArrayList<Product> prd1 = productManager.findProducts();
        for (Product prd: prd1
        ) {
            products.add(prd);

        }
    }
        private Long parser() throws ProductDAOException {
        int sz = Integer.parseInt(productManager.lastIndex());
        Long last;
        if(sz!=0) {
            last = (long) sz + 1;
        }
        else last = (long) 1;
        return last;
        }
}
