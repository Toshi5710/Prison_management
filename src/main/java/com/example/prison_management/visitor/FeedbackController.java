package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.Feedback;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FeedbackController {

    @javafx.fxml.FXML
    private TextArea feedbackTextArea;

    @javafx.fxml.FXML
    private ComboBox<String> ratingComboBox;


    @javafx.fxml.FXML
    public void initialize() {

        ratingComboBox.getItems().addAll("1", "2", "3", "4", "5");
    }


    @javafx.fxml.FXML
    public void clearButtonOA(ActionEvent actionEvent) {

        feedbackTextArea.clear();
        ratingComboBox.getSelectionModel().clearSelection();
    }


    @javafx.fxml.FXML
    public void submitButtonOA(ActionEvent actionEvent) {

        String feedback = feedbackTextArea.getText().trim();
        String rating = ratingComboBox.getValue();

        if (feedback.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter your feedback."
            );

            return;
        }

        if (rating == null) {

            Helper.showAlert(
                    "Error",
                    "Please select a rating."
            );

            return;
        }


        Feedback f = new Feedback(
                feedback,
                rating
        );


        File file = new File("Feedback.bin");

        try {

            FileOutputStream fos;
            ObjectOutputStream oos;

            if (file.exists() && file.length() > 0) {

                fos = new FileOutputStream(file, true);

                oos = new ObjectOutputStream(fos) {

                    @Override
                    protected void writeStreamHeader() {
                    }
                };

            } else {

                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(f);
            oos.close();


            Helper.showAlert(
                    "Success",
                    "Feedback submitted successfully."
            );

            clearButtonOA(actionEvent);


        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save feedback."
            );
        }
    }
}