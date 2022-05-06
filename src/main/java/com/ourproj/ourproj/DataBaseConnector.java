package com.ourproj.ourproj;
//Імпорт необхідних класів

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;



public class DataBaseConnector extends authentication_Controller{//Головний клас підключення бази даних

    public static boolean True_connection;

    public static void Open_DB() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//підключення драйверу
            try (Connection conn = getConnection()){
                System.out.println("Connection to Store DB successfully!");
                True_connection = true;
            }
        }
        catch(Exception ex){
            True_connection = false;
            Stage Test_connection = new Stage();
            Test_connection.setTitle("Помилка");
            Test_connection.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Error_connection.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 250, 100);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Test_connection.setScene(scene);
            Test_connection.showAndWait();


            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException, IOException {

        Ini properties = new Ini(new File("src/main/resources/com/ourproj/ourproj/Config.ini"));

        String open_Login = properties.get("login", "login");
        String open_Password = properties.get("password", "password");
        String open_Host = properties.get("host", "host");

        String url = "jdbc:mysql://" + open_Host;
        return DriverManager.getConnection(url, open_Login, open_Password);
    }

    public ResultSet Connection_Dekanat() {//Створення методу для отримання даних з бази даних
        ResultSet resultSet = null;
        String query = "SELECT Login, password, ID_Faculty FROM Users_dekanat";//SQL запит на отримання інформації
        try {
            PreparedStatement prSt = getConnection().prepareStatement(query);
            resultSet = prSt.executeQuery();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }




}
