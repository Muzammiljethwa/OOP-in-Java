package quizproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class questions extends JFrame implements ActionListener{
    
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
    
    
    
    
    JButton next, submit, lifeline;
    public static int count = 0;
    public static int timer = 15;
    public static int ans_given = 0;
    public static int score = 0;
    JLabel qno, question;
 
    updatedata update = new updatedata();
    
    
    
    
    
//    questions() throws Exception{this.reader = new BufferedReader(new FileReader("./Data/file.txt"));
//}
//    GetSize ga = new GetSize();
//    int a = ga.ReadQuizSize();
    
     public int ReadQuizSize()throws Exception{
        size = 10;
        try{
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./Data/TeachersData.json"));

        JSONArray teacher = (JSONArray) obj;
        
        Object[] teacherArray = teacher.toArray();
        for (int i=0; i<teacherArray.length; i++){
            JSONObject TeacherObject = (JSONObject) teacherArray[i];
            
            if(TeacherObject.get("StartQuiz").toString().equals("true")){
                size = Integer.parseInt( TeacherObject.get("QuizSize").toString() );
       
                }
            }
    
        }
        catch(Exception e){e.printStackTrace();}
        return size;
    }
    

    int size;
    String q[][];
    String pa[][];
    String qa[][];
    
//    String q[][] = new String[size][5];
//    String pa[][] = new String[size][1];
//    String qa[][] = new String[size][2];
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup options;
    
    String username,seat_no;
    
    questions(String seat_no){
        try { 
            
            size = ReadQuizSize();
            System.out.println(size);

            q = new String[size][5];
            pa = new String[size][1];
            qa = new String[size][2];

            int[] randomindex = randomArray(size, filesize());
            q = returnQestionsArray(size, randomindex);
    //            qa = returnCorrectOptionsArray(10, randomindex);
            qa = returnCorrectOptionsArray(size, randomindex);

            update.updateRandomIndex(seat_no, randomindex);

            this.username = username;
            this.seat_no = seat_no;

            setBounds(0, 0, 1360, 700);
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("quizproject/quiz.jpg"));
            JLabel l1 = new JLabel(i1);
            l1.setBounds(0, 0, 1360, 300);
            add(l1);

            qno = new JLabel("");
            qno.setFont(new Font("Tahoma", Font.PLAIN, 18));
            qno.setBounds(40,330,40,25);
            add(qno);

            question = new JLabel("");
            question.setFont(new Font("Tahoma", Font.PLAIN, 18));
            question.setBounds(80, 317,800, 50);
            add(question);

            opt1 = new JRadioButton("");
            opt1.setBounds(80, 400,600,25);
            opt1.setFont(new Font("Dialog", Font.PLAIN, 18));
            opt1.setBackground(Color.WHITE);
            add(opt1);

            opt2 = new JRadioButton("");
            opt2.setBounds(80, 440, 600, 25);
            opt2.setFont(new Font("Dialog", Font.PLAIN, 18));
            opt2.setBackground(Color.WHITE);
            add(opt2);

            opt3 = new JRadioButton("");
            opt3.setBounds(80,480,600,25);
            opt3.setFont(new Font("Dialog", Font.PLAIN, 18));
            opt3.setBackground(Color.WHITE);
            add(opt3);

            opt4 = new JRadioButton("");
            opt4.setBounds(80,520,600,25);
            opt4.setFont(new Font("Dialog", Font.PLAIN, 18));
            opt4.setBackground(Color.WHITE);
            add(opt4);

            options = new ButtonGroup();
            options.add(opt1);
            options.add(opt2);
            options.add(opt3);
            options.add(opt4);

            next = new JButton("Next");
            next.setFont(new Font("Tahoma", Font.PLAIN, 18));
            next.setBackground(Color.BLUE);
            next.setForeground(Color.WHITE);
            next.addActionListener(this);
            next.setBounds(1050, 400, 150, 30);
            add(next);

            submit = new JButton("Submit");
            submit.setFont(new Font("Tahoma", Font.PLAIN, 18));
            submit.setEnabled(false);
            submit.setBackground(Color.BLUE);
            submit.setForeground(Color.WHITE);
            submit.addActionListener(this);
            submit.setBounds(1050, 500, 150, 30);
            add(submit);

            start(0);
        
        }catch (Exception e) {
        }
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            repaint();
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            ans_given = 1;
            if(options.getSelection() == null){
                pa[count][0] = "";
            }else {
                pa[count][0] = options.getSelection().getActionCommand();
            }
            
            if(count == (size-2)){
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            
            count++;
            start(count);
        }else if(ae.getSource() == submit){
            ans_given = 1;
            if(options.getSelection() == null){
                pa[count][0] = "";
            }else {
                pa[count][0] = options.getSelection().getActionCommand();
            }

            for(int i =0 ; i < pa.length ; i++){
                if(pa[i][0].equals(qa[i][1])){
                    score+=1;
                }else{
                    score+=0;
                }
            }
            try {
                update.updatepa(seat_no, pa, score, size);
            } catch (Exception e) {e.printStackTrace();}
            this.setVisible(false);
            new scoreMain(seat_no);
//        }else if(ae.getSource() == lifeline){
//            if(count == 2 || count == 4 || count == 6 || count == 8 || count == 9){
//                opt2.setEnabled(false);
//                opt3.setEnabled(false);
//            }else {
//                opt1.setEnabled(false);
//                opt4.setEnabled(false);
//            }
//            lifeline.setEnabled(false);
//        }
    }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        String time = "Time Left - " + timer + " seconds"; // 15
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        if(timer > 0) {
            g.drawString(time, 1050, 380);
        }else {
            g.drawString("Times Up!!", 1050, 390);
        }
        
        timer--; // 14
        
        try{
            Thread.sleep(1000);
            repaint();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(ans_given == 1){
            ans_given = 0;
            timer = 15;
        }else if(timer < 0){
            timer = 15;

            if(count == (size-2)){
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            if(count == (size-1)){
                if(options.getSelection() == null){
                    pa[count][0] = "";
                }else {
                    pa[count][0] = options.getSelection().getActionCommand();
                }
                
                for(int i =0 ; i < pa.length ; i++){
                    if(pa[i][0].equals(qa[i][1])){
                        score+=10;
                    }else{
                        score+=0;
                    }
                }
                
                try {
                update.updatepa(seat_no, pa, score, size);
            } catch (Exception e) {e.printStackTrace();}
            this.setVisible(false);
            new scoreMain(seat_no);
            
            }else{
                if(options.getSelection() == null){
                    pa[count][0] = "";
                }else {
                    pa[count][0] = options.getSelection().getActionCommand();
                }
                count++;
                start(count);
            }
        }
    }
    
    public void start(int count){
        qno.setText("" + (count + 1) + ". ");
        question.setText(q[count][0]);
        
        opt1.setLabel(q[count][1]);
        opt1.setActionCommand(q[count][1]);
        opt2.setLabel(q[count][2]);
        opt2.setActionCommand(q[count][2]);
        opt3.setLabel(q[count][3]);
        opt3.setActionCommand(q[count][3]);
        opt4.setLabel(q[count][4]);
        opt4.setActionCommand(q[count][4]);
        options.clearSelection();
    }
    
    
    public static void main(String[] args)throws Exception{
        new questions("").setVisible(true);
    }
    

}
