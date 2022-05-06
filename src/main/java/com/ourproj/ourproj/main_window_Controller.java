package com.ourproj.ourproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.ourproj.ourproj.authentication_Controller.Name_dekanat;

public class main_window_Controller {

    @FXML
    private Button Registration_of_students;

    @FXML
    private Button Support_of_the_educational_process;

    @FXML
    private Button Making_changes_and_transferring_to_the_course;

    @FXML
    private Button View_and_print_information;

    @FXML
    private Button Visiting;

    @FXML
    private Button Modular_control;

    @FXML
    private Button Directory;

    @FXML
    private Label Dekanat_name;

    @FXML
    private Button Info;
    @FXML
    void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }



    @FXML
    void initialize() {

        Dekanat_name.setText(Name_dekanat);

        Info.setOnAction(ActionEvent -> {
            Stage Info = new Stage();
            Info.setTitle("Про розробників");
            Info.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("About_developers.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 400, 200);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Info.setScene(scene);
            Info.showAndWait();
        });

        Directory.setOnAction(ActionEvent -> {
            Stage Directory = new Stage();
            Directory.setTitle("Довідник");
            Directory.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Directory.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1024, 768);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Directory.setScene(scene);
            Directory.showAndWait();
        });

        Making_changes_and_transferring_to_the_course.setOnAction(ActionEvent -> {
            Stage Directory = new Stage();
            Directory.setTitle("Внесення змін та переведення на курс");
            Directory.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Making_changes_and_transferring_to_the_course.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Directory.setScene(scene);
            Directory.showAndWait();
        });

        Registration_of_students.setOnAction(actionEvent -> {
            Stage Directory = new Stage();
            Directory.setTitle("Реєстрація зарахованих студентів");
            Directory.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Registration_of_enrolled_students.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 400, 200);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Directory.setScene(scene);
            Directory.showAndWait();
        });

        Support_of_the_educational_process.setOnAction(ActionEvent -> {
            Stage Directory = new Stage();
            Directory.setTitle("Cупровід навчального процессу");
            Directory.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EduProcessCuration_Controller.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1024, 768);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Directory.setScene(scene);
            Directory.showAndWait();
        });

        Modular_control.setOnAction(ActionEvent -> {
            Stage Directory = new Stage();
            Directory.setTitle("Перегляд результатів модульного контролю");
            Directory.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Modular_results_preview.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Directory.setScene(scene);
            Directory.showAndWait();
        });

        View_and_print_information.setOnAction(ActionEvent -> {
            Stage Directory = new Stage();
            Directory.setTitle("Перегляд результатів модульного контролю");
            Directory.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view_and_print.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1240, 768);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Directory.setScene(scene);
            Directory.showAndWait();
        });
    }
}
