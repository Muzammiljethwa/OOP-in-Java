/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author mzjet
 */
public class updateMessage {
    
    public void deleteMessage()throws Exception{
        
        
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();
        JSONArray message = new JSONArray();
        
        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];
            StudentObject.put("Messages", message);
                  
        }


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(student);
        // System.out.println(array);

        FileWriter file = new FileWriter("./Data/QuizScore.json");
        file.write(json);
        file.close();
        
        
    }
    
    public void writeMessage(String message, String seatNumber, String person)throws Exception{
        
        
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();
        JSONArray msg = new JSONArray();
        int index=-1;
        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];
            if(StudentObject.get("SeatNumber").equals(seatNumber)){
                index =i;
                msg = (JSONArray) StudentObject.get("Messages");
                break;
            }    
                  
        }
        
        JSONObject StudentObject = (JSONObject) array[index];
        
        
        msg.add(person + ": " + message);
        StudentObject.put("Messages", msg);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(student);
        // System.out.println(array);

        FileWriter file = new FileWriter("./Data/QuizScore.json");
        file.write(json);
        file.close();
        
        
    }
    
    
    public String checkMessage(String seatNumber)throws Exception{
       
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();
        JSONArray msg = new JSONArray();
        
        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];
            if(StudentObject.get("SeatNumber").equals(seatNumber)){
                 msg = (JSONArray) StudentObject.get("Messages");
            }    
        }
        
        Object[] msgLi = msg.toArray();
        
//        for(Object i: msgLi){
//            System.out.println(i);}
        
        
        if(msgLi.length == 0){ return "false"; }
        else{ return "true"; }
    }
    
    public Object[] readMessage(String seatNumber)throws Exception{
       
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();
        JSONArray msg = new JSONArray();
        
        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];
            if(StudentObject.get("SeatNumber").equals(seatNumber)){
                 msg = (JSONArray) StudentObject.get("Messages");
            }    
        }
        
        Object[] msgLi = msg.toArray();
        return msgLi;
    }
    
    
    
    
    public String checkname(String seatNumber)throws Exception{
       
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();
        String name="";
        
        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];
            if(StudentObject.get("SeatNumber").equals(seatNumber)){
                 name =  StudentObject.get("Name").toString();
            }    
        }
        return name;
    }
    
    
    public static void main(String[] args)throws Exception{
        updateMessage up = new updateMessage();
        up.deleteMessage();
//        System.out.println( up.checkMessage("B19102001") );
//        Object[] li = up.readMessage("B19102111");
        
//        System.out.println( up.checkname("B19102111") );
        
//        for(Object i: li){System.out.println(i);}
    } 
    
}
