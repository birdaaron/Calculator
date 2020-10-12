package com.cal.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIView extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
        Parent root = loader.load();
        UIController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("四则运算生成器");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }
}
