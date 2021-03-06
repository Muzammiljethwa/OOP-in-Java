package quizproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.FileReader;
// import java.util.HashMap;  
// import java.util.Map;  
  
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
// import java.io.FileWriter;


public class myQuizproject extends JFrame implements ActionListener{
    
    JButton b1, b2;
    JTextField t1,t2;
    myQuizproject(){
        setBounds(370, 100, 800, 550); // dleft, //dup // length, height ///////
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);    
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("quizproject/socrative.png"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(190,0,350,100);
        add(l1);
        
        JPanel l2 = new JPanel();
        l2.setBounds(130,150,480,290);
        l2.setForeground(Color.BLUE);
        l2.setLayout(null);
        add(l2);
        
        JLabel l3 = new JLabel("Students Login");
        l3.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
        l3.setForeground(Color.BLACK);
        l3.setBounds(165,15,150,25);
//        l3.setVerticalAlignment(JLabel.CENTER);
//        l3.setBounds(50, 100, 30, 20);
        l2.add(l3);  
        
        JLabel l4 = new JLabel("Enter your name");
        l4.setFont(new Font("", Font.PLAIN, 16));
        l4.setForeground(Color.BLACK);
        l4.setBounds(50,70,180,20);
        l2.add(l4);  

        t1 = new JTextField();
        t1.setBounds(50, 100, 200, 25);
        t1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l2.add(t1);
        
        JLabel l5 = new JLabel("Enter your Seat number");
        l5.setFont(new Font("", Font.PLAIN, 16));
        l5.setForeground(Color.BLACK);
        l5.setBounds(50,150,180,20);
        l2.add(l5);  
        
        t2 = new JTextField();
        t2.setBounds(50, 180, 200, 25);
        t2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l2.add(t2);
        
        b1 = new JButton("Join");
        b1.setBounds(140, 240, 200, 35);
//        b1.setBackground(new Color(30, 144, 254));
        b1.setBackground(Color.yellow);
//        b1.setFont(new Font("Mongolian Baiti", Font.BOLD, 20);
        b1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        l2.add(b1);
       
        
//        b2 = new JButton("Exit");
//        b2.setBounds(915, 270, 120, 25);
//        b2.setBackground(new Color(30, 144, 254));
//        b2.setForeground(Color.WHITE);
//        b2.addActionListener(this);
//        add(b2);
        
        setVisible(true);
    }

    public String[] checkStudent(String seatnumber, String name)throws Exception{

        String Existseatnumber;
        String Existscore;
        Object Score;

        String[] returnArray = new String[3];
        // returnArray[0] = Existseatnumber;
        // returnArray[1] = Existscore;
        // returnArray[2] = Score[0];

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./Data/QuizScore.json"));

        JSONArray student = (JSONArray) obj;

        Object[] array = student.toArray();

        for(int i=0; i<array.length; i++){

            JSONObject StudentObject = (JSONObject) array[i];

            

            if(StudentObject.get("SeatNumber").equals(seatnumber)) {

                Existseatnumber = "true";
                returnArray[0] = Existseatnumber;

                if(StudentObject.get("Score").equals("")) {

                    Existscore = "true";
                    returnArray[1] = Existscore;
                    // new questions(name,seatnumber).setVisible(true);
                    break;
                    }
                    else{
                        Score = StudentObject.get("Score");
                        returnArray[2] = Score.toString();

                        Existscore = "false";
                        returnArray[1] = Existscore;
                        // JOptionPane.showMessageDialog(this,"Your test has been taken and your Score is: "+ StudentObject.get("Score"));
                        break;
                        }

                
                }
                else{ 

                    Existseatnumber = "false";
                    returnArray[0] = Existseatnumber;
                    // JOptionPane.showMessageDialog(this,"Please Enter correct seatnumber");

                }
            }

              

            return returnArray;
        }       
        

    

    public void actionPerformed(ActionEvent ae){


        if(ae.getSource() == b1){

            if (t1.getText().isEmpty() | t2.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Filled the required information!!"); 
        }
//            String name = t1.getText();
//            String seat_no = t2.getText();
            else { 
            this.setVisible(false);
            String name = t1.getText();
            String seat_no = t2.getText();

            // started by me
            try {

                String[] returnArray = checkStudent(seat_no, name);

                String Existseatnumber = returnArray[0];
                String Existscore = returnArray[1];
                String Score = returnArray[2];

                for(String m: returnArray){
                    System.out.println(m);
                }

                if ( Existseatnumber.equals("true") ){

                    if( Existscore.equals("true") ){
                        this.setVisible(false);
                        new questions(seat_no).setVisible(true);
                       
                    }
                    else if( Existscore.equals("false") ){
                        JOptionPane.showMessageDialog(this,"Your test has been taken and your Score is: "+ Score );
                    }
                }
                else if( Existseatnumber.equals("false") ){
                    JOptionPane.showMessageDialog(this,"Please Enter correct seatnumber");
                }

            } catch (Exception e) {  
                e.printStackTrace();
            }


            // ended by me

            // new questions(name,seat_no).setVisible(true);}
           }
        }
//        else{
////            System.exit(0);
//        }
        }

    

    public static void main(String[] args) {
        new myQuizproject();
    }
    
}
