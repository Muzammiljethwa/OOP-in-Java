
//import java.util.ArrayList;
import java.util.Random;

public class checkRandom{
    
    public int[] randomArray(int size, int ObjectLength){

        int[] indexArray = new int[size];
        Random rand = new Random();
        int randomCount = 0;

        while(randomCount<size){
            

            int int_random = rand.nextInt(ObjectLength);

//            ArrayList<Integer> checkarray = new ArrayList<Integer>();
//            checkarray.add(int_random);
            

//            for(int j: checkarray)
         
            boolean flag = false;
//      
            for(int k=0; k<randomCount; k++){
                if (int_random==indexArray[k] ){
                    flag = true;
                }
            }
                
  
            if(flag==false){ 
                indexArray[randomCount] = int_random;
                randomCount++;
            } 
            
//            if(randomCount == size){
//                break;
//            }
            
            
        
    }
        return indexArray;
    }
    
    public static void main(String[] args){
        checkRandom obj = new checkRandom();
        int[] a = obj.randomArray(10,10);
        for(int e : a ){
            System.out.println(e);
        }
        
}
}