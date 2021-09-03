package quizproject;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.*;

import java.io.FileReader;
  
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class score extends JFrame implements ActionListener{


    @SuppressWarnings("unchecked")

    score(String seat_no,int score,int size){

        setBounds(350, 150, 610, 420);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        String finalScore = score + "/" + size;

////// ================== Starting of Updating data to file ==========================

    try{
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();
        int index=0;
        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];
            if(StudentObject.get("SeatNumber").equals(seat_no) == true){
                index = i;
            }       
        }

        JSONObject StudentObject = (JSONObject) array[index];
        StudentObject.put("Score", finalScore);

        array[index] = StudentObject;


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(student);
        // System.out.println(array);

        FileWriter file = new FileWriter("./Data/QuizScore.json");
        file.write(json);
        file.close();
    }
    catch(Exception e){ e.printStackTrace();}


////// ================== Closing of Updating data to file ==========================
//        
//        File f  = new File("data.txt");
//        try {
//            f.createNewFile();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//         try {
// //            FileWriter f1 = new FileWriter("data.txt");
// //            f1.write("Name,"+"seat_no,"+"score");
//             FileWriter filewrite = new FileWriter("data.txt",true);
// //            filewrite.write("Name,"+"seat_no,"+"score\n");
//             filewrite.write(username+","+seat_no+","+score+"\n");
//             filewrite.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
        
        JLabel l2 = new JLabel("Quiz is successfully submitted!!");
        l2.setBounds(45, 30, 700, 30);
        l2.setFont(new Font("", Font.PLAIN, 24));
        add(l2);
        
        JLabel l3 = new JLabel("Your Score is ");
        l3.setBounds(160,140,200,30);
        l3.setFont(new Font("", Font.BOLD, 24));
        
//        l3.setForeground(new Color(199, 21, 133));
        l3.setForeground(Color.black);
        add(l3);
        
        JPanel p = new JPanel();
        p.setBounds(330,140,65,30);
        p.setBackground(Color.black);
        p.setLayout(null);
        add(p);
        
        JLabel l4 = new JLabel(finalScore);
        l4.setBounds(15,0,100,30);
        l4.setFont(new Font("", Font.BOLD, 24));
        l4.setForeground(Color.white);
        p.add(l4);

        JButton b1 = new JButton("Exit");
        b1.setBackground(Color.BLUE);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        b1.setBounds(240, 250, 120, 30);
        add(b1);
    }
    public void actionPerformed(ActionEvent ae){
        System.exit(0);

    }
    
    public static void main(String[] args)throws FileNotFoundException{

        new score("",0,0).setVisible(true);

        // File f = new File("data.txt");
        // Scanner sc = new Scanner(f);
        // while(sc.hasNextLine()){
        //     String data = sc.nextLine();
        //     System.out.println(data);
            
        // }
        // sc.close();

    }
}
