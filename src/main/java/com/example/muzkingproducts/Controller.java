package com.example.muzkingproducts;

import com.example.muzkingproducts.DAO.ProductSimpleDAO;
import com.example.muzkingproducts.Exceptions.ProductManagerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

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


    @FXML
    private void initialize() throws ProductManagerException {
        tableView.setEditable(true);


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
        tableView.setItems(products);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(!addName.getText().equals("")&&
                        !addCount.getText().equals("")&&
                        !addBuy.getText().equals("")&&
                        !addPrice.getText().equals("")
                ) {
                products.add(new Product(
                        Long.toString(parser()),
                        addName.getText(),
                        addCount.getText(),
                        addBuy.getText(),
                        addPrice.getText()
                ));
                    try {
                        productManager.addProduct(new Product(
                                Long.toString(parser()),
                                        addName.getText(),
                                        addCount.getText(),
                                        addBuy.getText(),
                                        addPrice.getText()
                        ));
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
                if(!tableView.getSelectionModel().isEmpty()) {
                    try {
                        System.out.println(tableView.getSelectionModel().getSelectedItem().getId()+" " + tableView.getSelectionModel().getSelectedItem().getName());
                        productManager.deleteProduct(tableView.getSelectionModel().getSelectedItem().getId());
                    } catch (ProductManagerException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());

            }
        });
        initData();
    }


    private void initData() throws ProductManagerException {
        /*products.add(new Product("M60sql", "10", "3000.0","4600.0"));
        products.add(new Product("кольца 16", "100", "150", "300")); */

        ArrayList<Product> prd1 = productManager.findProducts();
        for (Product prd: prd1
             ) {
            products.add(prd);

        }
        //products = FXCollections.observableArrayList(prd1);
    }
        private Long parser() {
        int sz = products.size();
        Long last;
        if(sz>0) {
            last = (long) Integer.parseInt(products.get(sz - 1).getId()) + 1;
        }
        else last = (long) 0;
        return last;
        }

}
