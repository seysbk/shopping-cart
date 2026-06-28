package com.hellocode.shoppingcart.step2.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HomeView {

    private Parent view;

    public HomeView() throws IOException {
        java.net.URL url = getClass().getResource("home.fxml");
        if (url == null) {
            throw new IOException("Unable to load FXML resource: home.fxml");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
}
