package dataBase;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static dataBase.ContactDAO.*;
import static java.lang.Integer.parseInt;


public class DataBase extends Application {

        static int position = 0;

        List<ContactPerson> tableAll = ContactDAO.getContacts();


    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField nick_nameField = new TextField();
    TextField emailField = new TextField();
    TextField phoneField = new TextField();

    void displayingData(int pos){
            idField.setText(String.valueOf(tableAll.get(pos).getId()));
            nameField.setText(tableAll.get(pos).getName());
            nick_nameField.setText(tableAll.get(pos).getNick_name());
            emailField.setText(tableAll.get(pos).getEmail());
            phoneField.setText(tableAll.get(pos).getCell_phone());
            position = pos;
    }

    int nextPrev(String sign)
    {
        if (Objects.equals(sign, "+")){
        if (position == tableAll.size() - 1)
        { return position = 0; }
        return ++position; }
        else{
            if(position > 0)
            { return --position; }
            return position = tableAll.size() - 1; }
    }

    @Override
    public void start(Stage stage) {

        TextArea main = new TextArea("DataBase Data");
        main.setEditable(false);

        idField.setPromptText("ID");
        Text idText = new Text("ID");

        nameField.setPromptText("Name");
        Text nameText = new Text("Name");

        nick_nameField.setPromptText("Nick name");
        Text nickText = new Text("Nick name");

        emailField.setPromptText("Email");
        Text emailText = new Text("Email");

        phoneField.setPromptText("Phone");
        Text phoneText = new Text("Phone");

        Button firstButton = new Button("First");
        Button prevButton = new Button("Previous");
        Button nextButton = new Button("Next");
        Button lastButton = new Button("Last");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");
        Button newButton = new Button("New");

        BorderPane body = new BorderPane();

        FlowPane mainLayOut = new FlowPane();

        GridPane gridContent = new GridPane();
        gridContent.setPadding(new Insets(10, 10, 10, 10));
        gridContent.setVgap(5);
        gridContent.setHgap(20);
        gridContent.setAlignment(Pos.CENTER_LEFT);

        gridContent.add(idText, 0, 0);
        gridContent.add(idField, 1, 0);
        gridContent.add(nameText, 0, 1);
        gridContent.add(nameField, 1, 1);
        gridContent.add(nickText, 0, 2);
        gridContent.add(nick_nameField, 1, 2);
        gridContent.add(emailText, 0, 3);
        gridContent.add(emailField, 1, 3);
        gridContent.add(phoneText, 0, 4);
        gridContent.add(phoneField, 1, 4);

        //Display first row as a start
        displayingData(position);

        //First button
        firstButton.addEventHandler(ActionEvent.ACTION, event -> displayingData(0)); // try and catch
        //Last button
        lastButton.addEventHandler(ActionEvent.ACTION, event -> displayingData((tableAll.size() - 1)));
        //Next button
        nextButton.addEventHandler(ActionEvent.ACTION, event -> displayingData(nextPrev("+")));
        //Previous button
        prevButton.addEventHandler(ActionEvent.ACTION, event -> displayingData(nextPrev("-")));

        //Delete button
        deleteButton.addEventHandler(ActionEvent.ACTION, event -> {
            try {
                deleteData(parseInt(idField.getText()));
                for (int i = 0; i < tableAll.size(); i++){
                if (tableAll.get(i).getId() == parseInt(idField.getText()))
                    {
                        tableAll.remove(i);
                        displayingData(0); //It should just clear the text fields and fill it with first row
                        System.out.println("Deleted Successfully");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        //Update button
        updateButton.addEventHandler(ActionEvent.ACTION, event -> {
            ContactPerson updateRow = new ContactPerson(
                    parseInt(idField.getText()),
                    nameField.getText(),
                    nick_nameField.getText(),
                    emailField.getText(),
                    phoneField.getText());
            try {
                updateData(updateRow);
                System.out.println("Updated Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Can't Update");
            }
        });

        //New button
        newButton.addEventHandler(ActionEvent.ACTION, event -> {
            ContactPerson insertRow = new ContactPerson(
                    parseInt(idField.getText()),
                    nameField.getText(),
                    nick_nameField.getText(),
                    emailField.getText(),
                    phoneField.getText());
            try {
                InsertData(insertRow);
                tableAll.add(insertRow);
                System.out.println(parseInt(idField.getText()));
                System.out.println("Inserted Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("id exist try another one");
            }
        });

        StackPane mainStack = new StackPane();

        VBox backGround = new VBox();
        backGround.setStyle("-fx-background-color: gray");
        mainStack.getChildren().add(backGround);

        mainLayOut.setHgap(2);
        FlowPane.setMargin(newButton, new Insets(20, 0, 20, 30));

        ObservableList<Node> list = mainLayOut.getChildren();
        list.addAll(newButton ,updateButton,deleteButton,firstButton, prevButton, nextButton, lastButton);

        body.setCenter(gridContent);
        body.setBottom(mainLayOut);
        mainStack.getChildren().add(body);

        Scene scene = new Scene(mainStack, 400, 400);

        stage.setMinWidth(400);
        stage.setMaxWidth(400);
        stage.setTitle("DataBase Interface");

        scene.getStylesheets().clear();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {
        ContactDAO.connection();
        Application.launch(args);
        ContactDAO.ends();
    }
}
