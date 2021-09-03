
import java.io.FileReader;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class checkjasonreadfile{
    
    public int[] randomArray(int size, int ObjectLength){

        int[] indexArray = new int[size];
        Random rand = new Random();
        int randomCount = 0;

        while(randomCount<size){
           
            int int_random = rand.nextInt(ObjectLength);
         
            boolean flag = false;
            for(int k=0; k<randomCount; k++){
                if (int_random==indexArray[k] ){
                    flag = true;
                }
            }
                
            if(flag==false){ 
                indexArray[randomCount] = int_random;
                randomCount++;
            } 
             
    }
        return indexArray;
    }
    
    public int filesize()throws Exception{
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./Data/Final_Quiz.json"));

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray quiz1 = (JSONArray) jsonObject.get("Quiz");

        Object[] array = quiz1.toArray();
        
        int length = array.length;
        
        return length;
    }
    
    public String[][] returnCorrectOptionsArray(int size, int[] randomArray)throws Exception{
        String ansArray[][] = new String[size][2];
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./Data/Final_Quiz.json"));

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray quiz1 = (JSONArray) jsonObject.get("Quiz");

        Object[] array = quiz1.toArray();
        
        for(int i = 0 ; i < randomArray.length ; i++){

            JSONObject quizobject = (JSONObject) array[randomArray[i]];
            
            long correctoption = (long) quizobject.get("TrueIndex");
            int correctindex = (int) correctoption;
            
            JSONArray options = (JSONArray) quizobject.get("Options");
            Object[] optionsarray = options.toArray();
            
            ansArray[i][1] = (String) optionsarray[correctindex];


        }
        
        return ansArray;
    }
    
    
    public String[][] returnQestionsArray(int size, int[] randomArray)throws Exception{
        
        String questionsarray[][] = new String[size][5];
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./Data/Final_Quiz.json"));

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray quiz1 = (JSONArray) jsonObject.get("Quiz");

        Object[] array = quiz1.toArray();
        
        
        for(int i = 0 ; i < randomArray.length ; i++){

            JSONObject quizobject = (JSONObject) array[randomArray[i]];

            String question = (String) quizobject.get("Question");
            questionsarray[i][0] = question;

            JSONArray options = (JSONArray) quizobject.get("Options");
            Object[] optionsarray = options.toArray();
            
            questionsarray[i][1] = (String) optionsarray[0];
            questionsarray[i][2] = (String) optionsarray[1];
            questionsarray[i][3] = (String) optionsarray[2];
            questionsarray[i][4] = (String) optionsarray[3];
            
            }
        return questionsarray;
    }
        
    
       
    public static void main(String[] args){
        checkjasonreadfile obj = new checkjasonreadfile();
        String q[][] = new String[10][5];
        try{
            int[] r = obj.randomArray(20, obj.filesize());
            for(int j: r){System.out.println(j);}
            
            q = obj.returnQestionsArray(20, r);
//            String[][] arr = obj.returnCorrectOptionsArray(10, r);
                    
            for(int i=0 ; i<q.length; i++){
                String[] qr = q[i];
                for(int j=0; j<qr.length ; j++) {
                    System.out.println(qr[j]);
                }
            }
        }
         catch(Exception e){ e.printStackTrace(); } 
        
        
        
       

    }
}