package com.ourproj.ourproj;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.ourproj.ourproj.DataBaseConnector.True_connection;

public class authentication_Controller {

    @FXML
    private Button authentication_GO;

    @FXML
    private TextField authentication_Login;

    @FXML
    private Button Settings;

    @FXML
    private AnchorPane main;

    @FXML
    private PasswordField authentication_password;

    public static String Login = "";
    public static String Password = "";
    public static String Name_dekanat = "";
    public static String Id_User = "";

    public authentication_Controller() {
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode() == KeyCode.ENTER) {
        Authentication_Button();
    }
}

    @FXML
    void initialize() throws IOException {

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Ini Login_Dekanat_set = new Ini(new File("src/main/resources/com/ourproj/ourproj/Config.ini"));
        String Login_Dekanat = Login_Dekanat_set.get("login", "login_Dekanat");
        authentication_Login.setText(Login_Dekanat);

        authentication_GO.setOnAction(actionEvent -> {
            try {
                Authentication_Button();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Settings.setOnAction(ActionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Ini Files", "*.ini"));
            File file = fileChooser.showOpenDialog(new Stage());
            Ini ini = null;
            try {
                ini = new Ini(new File(String.valueOf(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert ini != null;
            String Login =  ini.get("login", "login");
            String Password = ini.get("password", "password");
            String Host = ini.get("host", "host");
            String Login_Dekanat_getter = ini.get("login", "login_Dekanat");
            try {
                Ini update_properties = new Ini(new File("src/main/resources/ourproj/ourproj/Config.ini"));
                update_properties.put("login", "login", Login);
                update_properties.put("password", "password", Password);
                update_properties.put("host", "host", Host);
                update_properties.put("login", "login_Dekanat", Login_Dekanat_getter);
                update_properties.store();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void Close_window(){
        Stage close_window = (Stage) main.getScene().getWindow();
        close_window.close();
    }

    public void Authentication_Button() throws IOException {
        Login = String.valueOf(authentication_Login.getText());
        Password = String.valueOf(authentication_password.getText());
        Ini Login_Dekanat_set = new Ini(new File("src/main/resources/com/ourproj/ourproj/Config.ini"));
        authentication_Login.setText(Login_Dekanat_set.get("login", "login_Dekanat"));
        Login_Dekanat_set.put("login", "login_Dekanat", Login);
        Login_Dekanat_set.store();


        System.out.println(Login);
        System.out.println(Password);
        try {
            DataBaseConnector.Open_DB();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (True_connection){
            //////////////////////////////////////////////////////////////////////////
            boolean Connection_dekanat = false;
            DataBaseConnector dataBaseHandler = new DataBaseConnector();//Створюємо нову змінну на основі створеного нами класу
            ResultSet Log_pass = dataBaseHandler.Connection_Dekanat();//Викликаємо функцію з іншого класу
            List<String> var_1_List = new LinkedList<>();
            List<String> var_2_List = new LinkedList<>();//Створюємо список
            List<String> var_3_List = new LinkedList<>();//Створюємо список

            while (true) {//Запускаємо цикл на обробку даних отриманих з бази даних
                try {
                    if (!Log_pass.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String Login_string = null;
                String Password_string = null;
                String Login_ID = null;
                try {
                    Login_string = Log_pass.getString("Login");
                    Password_string = Log_pass.getString("password");
                    Login_ID = Log_pass.getString("ID_Faculty");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                var_1_List.add(Login_string);//додаємо отримані результати у список
                var_2_List.add(Password_string);
                var_3_List.add(Login_ID);
            }
            for (int i = 0; i < var_1_List.size(); i++) {
                if(Objects.equals(Login, var_1_List.get(i))){
                    Connection_dekanat = Objects.equals(Password, var_2_List.get(i));
                    Name_dekanat = var_1_List.get(i);
                    Id_User = var_3_List.get(i);
                    System.out.println(Id_User);
                }
            }
            //////////////////////////////////////////////////////////////////////////

            Stage Test_connection = new Stage();
            if (Connection_dekanat) {
                Test_connection.setTitle("Головне меню");
                Test_connection.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main_window.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1024, 768);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Close_window();
                Test_connection.setScene(scene);
                Test_connection.show();
            }
            else {
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
            }
        }
    }
}