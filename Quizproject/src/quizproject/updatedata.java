package quizproject;
import java.io.FileReader;
  
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class updatedata {

    @SuppressWarnings("unchecked")
    
    //pa.length
    public void updatepa(String seatNumber,String[][] pa2,int score, int size)throws Exception{
        
        String finalScore = score + "/" + size;
        
        String[] pa = new String[pa2.length];
            for(int k=0; k<pa2.length; k++){
                if(pa2[k][0].equals("")){ pa[k] = "Not Answered"; }
                else{ 
                     pa[k] = pa2[k][0] ; 
                }
            }
            
        JSONParser parser = new JSONParser();
    
            Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));
    
            JSONArray student = (JSONArray) obj;
    
            Object[] array = student.toArray();
            int index=0;
            for(int i=0; i<array.length; i++){
    
                JSONObject StudentObject = (JSONObject) array[i];
                if(StudentObject.get("SeatNumber").equals(seatNumber) == true){
                    index = i;
                }       
            }
    
            JSONObject StudentObject = (JSONObject) array[index];
            StudentObject.put("Score", finalScore);
            StudentObject.put("GivenAnswers", pa);
    
            array[index] = StudentObject;
    
    
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(student);
            // System.out.println(array);
    
            FileWriter file = new FileWriter("./Data/QuizScore.json");
            file.write(json);
            file.close();
    }

    public void updateRandomIndex(String seatNumber, int[] randomIndex)throws Exception{
        // try{

            JSONParser parser = new JSONParser();
    
            Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));
    
            JSONArray student = (JSONArray) obj;
    
            Object[] array = student.toArray();
            int index=0;
            for(int i=0; i<array.length; i++){
    
                JSONObject StudentObject = (JSONObject) array[i];
                if(StudentObject.get("SeatNumber").equals(seatNumber) == true){
                    index = i;
                }       
            }
    
            JSONObject StudentObject = (JSONObject) array[index];
            StudentObject.put("QuestionIndex", randomIndex);
    
            array[index] = StudentObject;
    
    
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(student);
            // System.out.println(array);
    
            FileWriter file = new FileWriter("./Data/QuizScore.json");
            file.write(json);
            file.close();
    //     }
    //     catch(Exception e){ e.printStackTrace();}
    // }
    
    }
    public static void main(String[] args) throws Exception{
        updatedata add = new updatedata();
        int[] randomIndex = {1,2,3,4,5};
        String[][] pa = {{"pa","pa","pa"}};
        add.updateRandomIndex("B19102111", randomIndex);
    }
} 