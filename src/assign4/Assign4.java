/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assign4;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author lynne
 */
public class Assign4 extends Application{

    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage stage){
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList("hotel 1","hotel 2","hotel 3");
        list.setItems(items);
        
        Button starBtn = new Button("Star Rating");
        starBtn.setStyle("-fx-background-color: #add8e6;");
         //when mouse hovering over btn
        starBtn.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                starBtn.setStyle("-fx-background-color: #c9eaf5");
            }
        });
        //when mouse no longer hovering over btn
        starBtn.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                starBtn.setStyle("-fx-background-color: #add8e6");
            }
        });
        
        Button lowestBtn = new Button("Lowest Price");
        lowestBtn.setStyle("-fx-background-color: #add8e6;");
        //when mouse is hovering over btn
        lowestBtn.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                lowestBtn.setStyle("-fx-background-color: #c9eaf5");
            }
        });
        //when mouse is no longer hovering over 
        lowestBtn.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                lowestBtn.setStyle("-fx-background-color: #add8e6");
            }
        });
        
        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: #add8e6;");
        
        //when mouse is hovering over btn
        saveBtn.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                saveBtn.setStyle("-fx-background-color: #c9eaf5");
            }
        });
        //when mouse is no longer hovering over
        saveBtn.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                saveBtn.setStyle("-fx-background-color: #add8e6");
            }
        });
        
        HBox hbox = new HBox(10, starBtn, lowestBtn, saveBtn);
        
        VBox vbox = new VBox(15, list, hbox);
        vbox.setPadding(new Insets(15, 12, 13, 14));
        Scene scene = new Scene(vbox, 400, 320);

        stage.setScene(scene);
        stage.setTitle("Hotel List");
        stage.show();
    }
    
    static class Hotel implements Comparable<Hotel>{
        String name;
        int stars;
        double price;
        
        //constructerrr
        public Hotel(String name, int stars, double price){
            this.name = name;
            this.stars = stars;
            this.price = price;
        }
        
        //getters
        public String getName(){
            return name;
        }
        
        public int getStars(){
            return stars;
        }
        
        public double getPrice(){
            return price;
        }
        
        @Override
        public int compareTo(Hotel other){
            return 1;
        }
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        //created a file object to make the file to read from
        launch(args);
        File file1 = new File("src/assign4/Hotels.txt"); 
        List<Hotel> hotels = new ArrayList<>();
        //reading the file
        try(BufferedReader buff = new BufferedReader(new FileReader(file1)))
        {
            String line;
            //read the file till the ends
            while ((line = buff.readLine()) != null)
            {
                line = line.trim();
                
                if(line.isEmpty()){
                    continue; //this is to skip emtpy lines
                }
                
                String[] parts = line.split("\\s+"); //split from whitespaces
                
                //this is to get the last index which would be the price
                double price = Double.parseDouble(parts[parts.length -1]);
                
                //this is to get the 2nd last index which will be the start rating
                int stars = Integer.parseInt(parts[parts.length - 2]);
                
                //since most hotel names have spaces we get everything before the 2nd last index as the hotel name
                String name = "";
                for(int i =0;i<parts.length - 2; i++){
                    if(i>0){
                        name += " ";
                    }
                    name += parts[i];
                }
                
                //creating Hotel objects
                Hotel h = new Hotel(name, stars, price);
                hotels.add(h);
                
                //printing hotel info
                System.out.println("Hotel Name: " + name);
                System.out.println("Stars: " + stars);
                System.out.println("Price: " + price);
                System.out.println("=====================");
               
            }   
        }
        catch (FileNotFoundException e)
        {
            System.out.println("file not found: " + e);
        }
    }
    
}
