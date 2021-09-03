import java.io.FileReader;
import java.util.HashMap;  
import java.util.Map;  
  
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StudentData {

    @SuppressWarnings("unchecked")
    public void addStudent(String name, String seatnumber)throws Exception{

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Map<String,String> newdata = new HashMap<String,String>();
        newdata.put("Name", name);
        newdata.put("SeatNumber", seatnumber);
        newdata.put("Score", "");

        student.add(newdata);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(student);
        // System.out.println(json);
        
        FileWriter file = new FileWriter("./Data/QuizScore.json");
        file.write(json);
        file.close();

    }

    @SuppressWarnings("unchecked")
    public void changeScore(String seatnumber,int score) throws Exception{
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();
        int index=0;
        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];
            if(StudentObject.get("SeatNumber").equals(seatnumber) == true){
                index = i;
            }       
        }

        JSONObject StudentObject = (JSONObject) array[index];
        StudentObject.put("Score", score);

        array[index] = StudentObject;


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(student);
        // System.out.println(array);

        FileWriter file = new FileWriter("./Data/QuizScore.json");
        file.write(json);
        file.close();

    }

    public static void main(String args[])throws Exception{

        JSONParser parser = new JSONParser();

        StudentData studentdata = new StudentData();
        // studentdata.addStudent("try name", "123456");

        studentdata.changeScore("B19102002", 15); 
        
        {
            try{

                Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

                JSONArray student = (JSONArray) obj;

                Object[] array = student.toArray();


                for(int i=0; i<array.length; i++){

                    JSONObject StudentObject = (JSONObject) array[i];
                    if(StudentObject.get("SeatNumber").equals("B19102002")) {
                        System.out.println(array[i]);
                        if(StudentObject.get("Score").equals("")) {
                            System.out.println("You are eligible to take test.");
                            break;
                        }
                        else{
                            System.out.println("Your test has been taken and your Score is: "+ StudentObject.get("Score") );
                        }
                    }       
                }


            }
            catch(Exception e){ e.printStackTrace();}
        }



}
}
