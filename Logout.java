/* 
    Author: Denis Bajgora
    Date: 1/9/2024

    loginout class is used to loginout the player and deleting their current session data.
*/

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Logout extends Page {
    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button logoutButton;

    @FXML
    private Button previousPageButton;

    private String fxmlFilePath = "/assets/fxml/Logout.fxml";
    private String cssFilePath = "/assets/css/Logout.css";
    private String css = this.getClass().getResource(getCSSFilePath()).toExternalForm();

    public Logout() {
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);
        setCSS(css);
    }
 
    public void goBack(ActionEvent e) throws IOException {
        Main.getPageManager().navigateBack();
    }   

    public void logout(ActionEvent e) throws IOException {

        DBUtils.savePlayerData();

        // clean old data
        Player.setInventory(new ArrayList<String>());
        Player.setInventoryItemsId(new ArrayList<Long>());
        Player.setPurchasedItems(new ArrayList<String>());

        Player.saveData = false;

        Main.getPageManager().clearPageStack();
        Main.getPageManager().navigateTo(new Welcome());
    }

    // Getters & Setters 
    @Override
    public String getFXMLFilePath() {
        return fxmlFilePath;
    }

    @Override
    public String getCSSFilePath() {
        return cssFilePath;
    }

    @Override
    public String getCSS() {
        return css;
    }

    public Button getPreviousPageButton() {
        return previousPageButton;
    }

    public void setPreviousPageButton(Button previousPageButton) {
        this.previousPageButton = previousPageButton;
    }

    public Button getlogoutButton() {
        return logoutButton;
    }
}
