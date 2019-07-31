/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Internet;

/**
 *
 * @author pc
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtIme;
    @FXML
    private TextField txtPrezime;
    @FXML
    private TextField txtAdressa;
    @FXML
    private ComboBox<String> txtBrzina;
    @FXML
    private ComboBox<String> txtProtok;
    @FXML
    private RadioButton txtradion;
    @FXML
    private RadioButton txtradion2;
    @FXML
    TableView tblData;
    PreparedStatement preparedStatement;
    Connection connection;
    String SQL = "SELECT * from internet";
    private ObservableList<ObservableList> data;
    @FXML
    ToggleGroup group;

    private String saveData() {
        //-ugovor trim
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        if (toogleGroupValue.equals("2 GOD.")) {
            toogleGroupValue = "2";
        } else {
            toogleGroupValue = "1";
        }
        //---INDENTIFIKACIONI BROJ RANDOM
        Random rand = new Random();
        int n = rand.nextInt(10000);
        n += 253;
        String ime=txtIme.getText();

        try {
            String st = "insert into internet(ime,prezime,adresa,brzina,protok,ugovor,identifikacioni_broj) values (?,?,?,?,?,?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtIme.getText());
            preparedStatement.setString(2, txtPrezime.getText());
            preparedStatement.setString(3, txtAdressa.getText());
            preparedStatement.setString(4, txtBrzina.getValue());
            preparedStatement.setString(5, txtProtok.getValue());
            preparedStatement.setInt(6, Integer.parseInt(toogleGroupValue));
            preparedStatement.setInt(7, n);
            preparedStatement.executeUpdate();
            fetRowList();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "Exception";
        }
        return null;
    }

    @FXML
    private void HandleEvents(MouseEvent event) {
        
        if (txtBrzina.getValue() == "Brzina" || txtProtok.getValue()=="Protok") {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Brzina nije uneta ili protok");
            alert.setContentText("Morate da unesete brzinu ili protok!!!");

            alert.showAndWait();
        }else if(txtIme.getText().isEmpty() || txtPrezime.getText().isEmpty() || txtAdressa.getText().isEmpty()){
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("GRESKA!");
            alert.setContentText("Sva polja moraju biti populjena");
             alert.showAndWait();
         }else{
        saveData();
        }
    }
     @FXML
    private void HandleEvents_for_ponisti(MouseEvent event) {
        txtIme.setText("");
        txtPrezime.setText("");
        txtAdressa.setText("");
       this.txtBrzina.getSelectionModel().select("Brzina");
       this.txtProtok.getSelectionModel().select("Protok");
       
        
    }
    @FXML
    private void HandleEvents_for_brisanje(MouseEvent event) {
        if(txtid.getText().isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
            alert.setHeaderText("Brisanje korisnika nije moguce");
            alert.setContentText("Morate uneti ID!!!");
            alert.showAndWait();
        }else{
        try{
       String st = "delete from internet where id=?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtid.getText());
             preparedStatement.executeUpdate();
            fetRowList();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txtBrzina.getItems().addAll(new String[]{"2 MB", "5 MB", "10 MB", "20 MB", "50 MB", "100 MB"});
        this.txtBrzina.getSelectionModel().select("Brzina");
        this.txtProtok.getItems().addAll(new String[]{"1 GB", "5 GB", "10 GB", "100 GB", "Flat"});
        this.txtProtok.getSelectionModel().select("Protok");
        group = new ToggleGroup();
        txtradion.setToggleGroup(group);
        txtradion2.setToggleGroup(group);
        txtradion.setSelected(true);
        //--------------------------------
        fetColumnList();
        fetRowList();
       
    }

    public ObservableList<Internet> getinternet() {
        ObservableList<Internet> itnernet_informacije = FXCollections.observableArrayList();
        itnernet_informacije.add(new Internet("proba"));
        return itnernet_informacije;
    }

    private void fetColumnList() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?zeroDateTimeBehavior=convertToNull", "root", "vojke123321");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(this.SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); ++i) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }

                });
                this.tblData.getColumns().removeAll(new Object[]{col});
                this.tblData.getColumns().addAll(new Object[]{col});
                System.out.println("Column [" + i + "] ");
            }
        } catch (Exception var5) {
            System.out.println("Error " + var5.getMessage());
        }
    }

    private void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
