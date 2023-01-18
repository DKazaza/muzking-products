module com.example.muzkingproducts {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.muzkingproducts to javafx.fxml;
    exports com.example.muzkingproducts;
    exports com.example.muzkingproducts.DAO;
    opens com.example.muzkingproducts.DAO to javafx.fxml;
}