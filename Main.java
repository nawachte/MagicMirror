//jsoup
import javafx.application.Platform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//javafx
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Random;
class Num{
    static String num(){
        Random rand = new Random();
        rand.nextInt(100);
        String string = Integer.toString(rand.nextInt(100));
        //string = string+rand;
        System.out.println(string);
        return string;
    }
}

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage){

        Text timeText = new Text(Attributes.time());
        timeText.setFill(Color.WHITE);
        timeText.setFont(Font.font("Helvetica", 100));
        timeText.setRotate(270);
        timeText.relocate(-100, 400);

//        Text numText = new Text(Num.num());
//        numText.setFill(Color.WHITE);
//        numText.setFont(Font.font("Helvetica", 30));
//        numText.setRotate(270);
//        numText.relocate(200, 400);

        Text dateText = new Text(Attributes.date());
        dateText.setFill(Color.WHITE);
        dateText.setFont(Font.font("Helvetica", 30));
        dateText.setRotate(270);
        dateText.relocate(150, 700);

        Text tempText = new Text(Attributes.curTemp());
        //System.out.println("temp text "+tempText);
        tempText.setFont(Font.font("Helvetica", 50));
        tempText.setFill(Color.WHITE);
        tempText.setRotate(270);
        tempText.relocate(1200, 650);
        tempText.relocate(700, 650);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        timeText.setText(Attributes.time());
                        //tempText.setText(Attributes.curTemp());

                    }
                };
                while(true){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException ex){
                    }
                    Platform.runLater(updater);
                }
            }
        });

        thread.setDaemon(true);
        thread.start();

        Pane pane = new Pane(timeText, dateText, tempText);
        Scene scene = new Scene(pane, Color.BLACK);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();

    }
}

class Attributes{
    static String time(){
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime nowTime = LocalDateTime.now();
        String timeString = dtfTime.format(nowTime);
        //check if first character is 0
        return timeString;
    }
    static String date(){
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MM-dd yyyy");
        LocalDateTime nowDate = LocalDateTime.now();
        //check if first character is 0
        return dtfDate.format(nowDate);
    }
    static String curTemp(){
        final String tempUrl = "https://weather.com/weather/today/l/5c51b83496c9dacc59b73678fcb814dcf384e3499ea75ae248dc64265d377482";
        String tempString = "";
        try{
            final Document tempDoc = Jsoup.connect(tempUrl).get();
            //tempString = tempDoc.select("div[id=home]").text();
            tempString = tempDoc.getElementsByClass("today_nowcard-temp").text();
        }
        catch (Exception ex2){
            ex2.printStackTrace();
        }
        tempString = "Current temp: "+tempString+"F";
        //String tempString = "Current temp: 44°F";
        System.out.println(tempString);
        return tempString;
    }
}

//class Time{
//    static String time(){
//        final String timeUrl = "https://time.is";
//        String timeString = "";
//        try {
//            final Document timeDoc = Jsoup.connect(timeUrl).get();
//            timeString = timeDoc.select("div[id=twd]").text();
//        }
//        catch (Exception ex1) {
//            ex1.printStackTrace();
//        }
////        String firstChar = timeString.charAt(0)+"";
////        if (firstChar == "0"){
////            return timeString.substring(1);
////        }
//        return timeString;
//    }
//}

//class Temp{
//    static String temp(){
//        final String tempUrl = "https://www.google.com/search?ei=YZBMXYL5JKL89AOd0LSIBQ&q=temperature+in+chandler+arizona&oq=temperature+in+chandler+a&gs_l=psy-ab.1.0.0i70i256j0i22i30l9.20927.29804..31007...5.0..1.277.3374.24j9j1......0....1..gws-wiz.....10..0i71j35i39i285j0i20i263j0i131j0j0i67j35i39i285i70i256j35i39j35i305i39j0i67i70i256j0i20i263i70i256.RrQDBBiWiKc";
//        String tempString = "";
//        try{
//            final Document tempDoc = Jsoup.connect(tempUrl).get();
//            tempString = tempDoc.select("span[id=wob_tm]").text();
//        }
//        catch (Exception ex2){
//            ex2.printStackTrace();
//        }
//        tempString = "Current temp: "+tempString+"°F";
//        System.out.println(tempString);
//        return tempString;
//    }
//}
