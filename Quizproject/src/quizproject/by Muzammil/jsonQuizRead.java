import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class jsonQuizRead {

    public int[] randomArray(int size, int ObjectLength){

        int[] indexArray = new int[size];
        Random rand = new Random();
        int randomCount =0;

        for(int i=0; i < indexArray.length ; i++){

            int int_random = rand.nextInt(ObjectLength);

            ArrayList<Integer> checkarray = new ArrayList<Integer>();
            checkarray.add(int_random);
            randomCount++;

            for(int j: checkarray){
                boolean flag = false;
//                for(int k=0; k<indexArray.length; k++){
                for(int k=0; k<randomCount; k++){
                    if (j==indexArray[k] ){
                        flag = true;
                    }
                
            }
            if(flag==false){ indexArray[i] = int_random; } 
            
        }
    }
        return indexArray;
    }
    
    public static void main(String args[])throws NullPointerException{

        JSONParser parser = new JSONParser();
        Scanner input = new Scanner(System.in);

        jsonQuizRead Array = new jsonQuizRead();
        
        {
            try{
                int score = 0;
                Object obj = parser.parse(new FileReader("./Data/Final_Quiz.json"));

                JSONObject jsonObject = (JSONObject) obj;

                JSONArray quiz1 = (JSONArray) jsonObject.get("Quiz");

                Object[] array = quiz1.toArray();
                System.out.print("Enter number of Questions asked Randomly: ");
                int inint = input.nextInt();
                int[] randomArray = Array.randomArray(inint, array.length);
                // for(int j=0 ; j< randomArray.length; j++){
                //     System.out.println(randomArray[j]);
                // }

                for(int i = 0 ; i < randomArray.length ; i++){

                    JSONObject quizobject = (JSONObject) array[randomArray[i]];

                    String question = (String) quizobject.get("Question");
                    String number = String.valueOf(i+1);
                    System.out.println( number + ") " + question);

                    System.out.println("");

                    JSONArray options = (JSONArray) quizobject.get("Options");
                    Object[] optionsarray = options.toArray();
                    for(int j = 0 ; j < optionsarray.length; j++){
                        // String optionNumber = String.valueOf(j+1);
                        // System.out.println( optionNumber + ") " + optionsarray[j]);
                        System.out.println(optionsarray[j]);
                    }

                    
                    int option ;
                    while(true){
                        System.out.print("\nEnter Option#: ");
                        char inputoption = input.next().charAt(0);
                        
                        if (inputoption == 'a'){
                            option = 0;
                            break;
                        }
                        else if (inputoption == 'b'){
                            option = 1;
                            break;
                        }
                        else if (inputoption == 'c'){
                            option = 2;
                            break;
                        }
                        else if (inputoption == 'd'){
                            option = 3;
                            break;
                        }
                        else{
                            System.out.println("Please enter correct option.");
                        }
                    }
                    

                    long correctoption = (long) quizobject.get("TrueIndex");
                    int correctindex = (int) correctoption;

                    if ( option == correctindex){
                        score++;
                    }

                    System.out.println("Correct Option: " + optionsarray[correctindex] );

                    System.out.println("");

                }
                    input.close();
                    String strScore = String.valueOf(score);
                    System.out.println("Your Score is: " + strScore);

                    System.out.println("");

                    double percentage = (score * 100 )/randomArray.length;
                    String strpercentage = String.valueOf(percentage);
                    System.out.println("Your Percentage is: " + strpercentage + "%"); 

            }
            catch(Exception e){ e.printStackTrace();}
        }

        
    
}

}
