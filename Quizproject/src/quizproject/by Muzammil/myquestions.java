//package quizproject;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.FileReader;
//import java.util.Random;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//public class myquestions extends JFrame implements ActionListener{
//    
//    JButton next, submit, lifeline;
//    public static int count = 0;
//    public static int timer = 15;
//    public static int ans_given = 0;
//    public static int score = 0;
//    JLabel qno, question;
//    int size = 10;
////    String q[][] = new String[size][5];
////    String pa[][] = new String[size][1];
//    String qa[][] = new String[size][2];
//    JRadioButton opt1, opt2, opt3, opt4;
//    ButtonGroup options;
//    
//    String username,seat_no;
//    
//    public int[] randomArray(int size, int ObjectLength){
//
//        int[] indexArray = new int[size];
//        Random rand = new Random();
//        int randomCount = 0;
//
//        while(randomCount<size){
//           
//            int int_random = rand.nextInt(ObjectLength);
//         
//            boolean flag = false;
//            for(int k=0; k<randomCount; k++){
//                if (int_random==indexArray[k] ){
//                    flag = true;
//                }
//            }
//                
//            if(flag==false){ 
//                indexArray[randomCount] = int_random;
//                randomCount++;
//            } 
//             
//    }
//        return indexArray;
//    }
//    
//    public int filesize()throws Exception{
//        
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(new FileReader("./Data/Final_Quiz.json"));
//
//        JSONObject jsonObject = (JSONObject) obj;
//
//        JSONArray quiz1 = (JSONArray) jsonObject.get("Quiz");
//
//        Object[] array = quiz1.toArray();
//        
//        int length = array.length;
//        
//        return length;
//    }
//    
//    int[] randomindex = randomArray(size, filesize() );
//    
//    public String[][] returnCorrectOptionsArray(int size, int[] randomArray)throws Exception{
//        String ansArray[][] = new String[size][1];
//        
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(new FileReader("./Data/Final_Quiz.json"));
//
//        JSONObject jsonObject = (JSONObject) obj;
//
//        JSONArray quiz1 = (JSONArray) jsonObject.get("Quiz");
//
//        Object[] array = quiz1.toArray();
//        
//        for(int i = 0 ; i < randomArray.length ; i++){
//
//            JSONObject quizobject = (JSONObject) array[randomArray[i]];
//            
//            long correctoption = (long) quizobject.get("TrueIndex");
//            int correctindex = (int) correctoption;
//            
//            JSONArray options = (JSONArray) quizobject.get("Options");
//            Object[] optionsarray = options.toArray();
//            
//            ansArray[i][1] = (String) optionsarray[correctindex];
//        }
//        
//        return ansArray;
//    }
//    
//    String pa[][] = ansArray[size][1];
//    
//    public String[][] returnQestionsArray(int size, int[] randomArray)throws Exception{
//        
//        String questionsarray[][] = new String[size][5];
//        
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(new FileReader("./Data/Final_Quiz.json"));
//
//        JSONObject jsonObject = (JSONObject) obj;
//
//        JSONArray quiz1 = (JSONArray) jsonObject.get("Quiz");
//
//        Object[] array = quiz1.toArray();
//        
//        
//        for(int i = 0 ; i < randomArray.length ; i++){
//
//            JSONObject quizobject = (JSONObject) array[randomArray[i]];
//
//            String question = (String) quizobject.get("Question");
//            questionsarray[i][0] = question;
//
//            JSONArray options = (JSONArray) quizobject.get("Options");
//            Object[] optionsarray = options.toArray();
//            
//            questionsarray[i][1] = (String) optionsarray[0];
//            questionsarray[i][2] = (String) optionsarray[1];
//            questionsarray[i][3] = (String) optionsarray[2];
//            questionsarray[i][4] = (String) optionsarray[3];
//            
//            }
//        return questionsarray;
//    }
//        
//    
//        
//     String q[][] = returnQestionsArray(size, randomindex);
//     
//    
//    myquestions(String username,String seat_no){
//        this.username = username;
//        this.seat_no = seat_no;
//        setBounds(0, 0, 1360, 700);
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//        
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("quizproject/quiz.jpg"));
//        JLabel l1 = new JLabel(i1);
//        l1.setBounds(0, 0, 1360, 300);
//        add(l1);
//        
//        qno = new JLabel("");
//        qno.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        qno.setBounds(40,330,40,25);
//        add(qno);
//        
//        question = new JLabel("");
//        question.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        question.setBounds(80, 330, 700, 30);
//        add(question);
//        
////        q[0][0] = "Which is used to find and fix bugs in the Java programs.?";
////        q[0][1] = "JVM";
////        q[0][2] = "JDB";
////        q[0][3] = "JDK";
////        q[0][4] = "JRE";
////
////        q[1][0] = "What is the return type of the hashCode() method in the Object class?";
////        q[1][1] = "int";
////        q[1][2] = "Object";
////        q[1][3] = "long";
////        q[1][4] = "void";
////
////        q[2][0] = "Which package contains the Random class?";
////        q[2][1] = "java.util package";
////        q[2][2] = "java.lang package";
////        q[2][3] = "java.awt package";
////        q[2][4] = "java.io package";
////
////        q[3][0] = "An interface with no fields or methods is known as?";
////        q[3][1] = "Runnable Interface";
////        q[3][2] = "Abstract Interface";
////        q[3][3] = "Marker Interface";
////        q[3][4] = "CharSequence Interface";
////
////        q[4][0] = "In which memory a String is stored, when we create a string using new operator?";
////        q[4][1] = "Stack";
////        q[4][2] = "String memory";
////        q[4][3] = "Random storage space";
////        q[4][4] = "Heap memory";
////
////        q[5][0] = "Which of the following is a marker interface?";
////        q[5][1] = "Runnable interface";
////        q[5][2] = "Remote interface";
////        q[5][3] = "Readable interface";
////        q[5][4] = "Result interface";
////
////        q[6][0] = "Which keyword is used for accessing the features of a package?";
////        q[6][1] = "import";
////        q[6][2] = "package";
////        q[6][3] = "extends";
////        q[6][4] = "export";
////
////        q[7][0] = "In java, jar stands for?";
////        q[7][1] = "Java Archive Runner";
////        q[7][2] = "Java Archive";
////        q[7][3] = "Java Application Resource";
////        q[7][4] = "Java Application Runner";
////
////        q[8][0] = "Which of the following is a mutable class in java?";
////        q[8][1] = "java.lang.StringBuilder";
////        q[8][2] = "java.lang.Short";
////        q[8][3] = "java.lang.Byte";
////        q[8][4] = "java.lang.String";
////
////        q[9][0] = "Which of the following option leads to the portability and security of Java?";
////        q[9][1] = "Bytecode is executed by JVM";
////        q[9][2] = "The applet makes the Java code secure and portable";
////        q[9][3] = "Use of exception handling";
////        q[9][4] = "Dynamic binding between objects";
//        
//        qa[0][1] = "JDB";
//        qa[1][1] = "int";
//        qa[2][1] = "java.util package";
//        qa[3][1] = "Marker Interface";
//        qa[4][1] = "Heap memory";
//        qa[5][1] = "Remote interface";
//        qa[6][1] = "import";
//        qa[7][1] = "Java Archive";
//        qa[8][1] = "java.lang.StringBuilder";
//        qa[9][1] = "Bytecode is executed by JVM";
//        
//        
//        opt1 = new JRadioButton("");
//        opt1.setBounds(80, 400,600,25);
//        opt1.setFont(new Font("Dialog", Font.PLAIN, 18));
//        opt1.setBackground(Color.WHITE);
//        add(opt1);
//        
//        opt2 = new JRadioButton("");
//        opt2.setBounds(80, 440, 600, 25);
//        opt2.setFont(new Font("Dialog", Font.PLAIN, 18));
//        opt2.setBackground(Color.WHITE);
//        add(opt2);
//        
//        opt3 = new JRadioButton("");
//        opt3.setBounds(80,480,600,25);
//        opt3.setFont(new Font("Dialog", Font.PLAIN, 18));
//        opt3.setBackground(Color.WHITE);
//        add(opt3);
//        
//        opt4 = new JRadioButton("");
//        opt4.setBounds(80,520,600,25);
//        opt4.setFont(new Font("Dialog", Font.PLAIN, 18));
//        opt4.setBackground(Color.WHITE);
//        add(opt4);
//        
//        options = new ButtonGroup();
//        options.add(opt1);
//        options.add(opt2);
//        options.add(opt3);
//        options.add(opt4);
//        
//        next = new JButton("Next");
//        next.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        next.setBackground(Color.BLUE);
//        next.setForeground(Color.WHITE);
//        next.addActionListener(this);
//        next.setBounds(1050, 400, 150, 30);
//        add(next);
//        
////        lifeline = new JButton("50-50 Lifeline");
////        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 18));
////        lifeline.setBackground(Color.BLUE);
////        lifeline.setForeground(Color.WHITE);
////        lifeline.setBounds(1050, 450, 150, 30);
////        lifeline.addActionListener(this);
////        add(lifeline);
//        
//        submit = new JButton("Submit");
//        submit.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        submit.setEnabled(false);
//        submit.setBackground(Color.BLUE);
//        submit.setForeground(Color.WHITE);
//        submit.addActionListener(this);
//        submit.setBounds(1050, 500, 150, 30);
//        add(submit);
//        
//        start(0);
//        
//    }
//    
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == next){
//            repaint();
//            opt1.setEnabled(true);
//            opt2.setEnabled(true);
//            opt3.setEnabled(true);
//            opt4.setEnabled(true);
//            
//            ans_given = 1;
//            if(options.getSelection() == null){
//                pa[count][0] = "";
//            }else {
//                pa[count][0] = options.getSelection().getActionCommand();
//            }
//            
//            if(count == 8){
//                next.setEnabled(false);
//                submit.setEnabled(true);
//            }
//            
//            count++;
//            start(count);
//        }else if(ae.getSource() == submit){
//            ans_given = 1;
//            if(options.getSelection() == null){
//                pa[count][0] = "";
//            }else {
//                pa[count][0] = options.getSelection().getActionCommand();
//            }
//
//            for(int i =0 ; i < pa.length ; i++){
//                if(pa[i][0].equals(qa[i][1])){
//                    score+=1;
//                }else{
//                    score+=0;
//                }
//            }
//            this.setVisible(false);
//            new score(username,seat_no,score).setVisible(true);
////        }else if(ae.getSource() == lifeline){
////            if(count == 2 || count == 4 || count == 6 || count == 8 || count == 9){
////                opt2.setEnabled(false);
////                opt3.setEnabled(false);
////            }else {
////                opt1.setEnabled(false);
////                opt4.setEnabled(false);
////            }
////            lifeline.setEnabled(false);
////        }
//    }
//    }
//    
//    public void paint(Graphics g){
//        super.paint(g);
//        String time = "Time Left - " + timer + " seconds"; // 15
//        g.setColor(Color.RED);
//        g.setFont(new Font("Tahoma", Font.BOLD, 18));
//        
//        if(timer > 0) {
//            g.drawString(time, 1050, 380);
//        }else {
//            g.drawString("Times Up!!", 1050, 390);
//        }
//        
//        timer--; // 14
//        
//        try{
//            Thread.sleep(1000);
//            repaint();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        if(ans_given == 1){
//            ans_given = 0;
//            timer = 15;
//        }else if(timer < 0){
//            timer = 15;
////            opt1.setEnabled(true);
////            opt2.setEnabled(true);
////            opt3.setEnabled(true);
////            opt4.setEnabled(true);
//            
//            if(count == 8){
//                next.setEnabled(false);
//                submit.setEnabled(true);
//            }
//            if(count == 9){
//                if(options.getSelection() == null){
//                    pa[count][0] = "";
//                }else {
//                    pa[count][0] = options.getSelection().getActionCommand();
//                }
//                
//                for(int i =0 ; i < pa.length ; i++){
//                    if(pa[i][0].equals(qa[i][1])){
//                        score+=10;
//                    }else{
//                        score+=0;
//                    }
//                }
//                this.setVisible(false);
//                new score(username,seat_no,score).setVisible(true);
//            }else{
//                if(options.getSelection() == null){
//                    pa[count][0] = "";
//                }else {
//                    pa[count][0] = options.getSelection().getActionCommand();
//                }
//                count++;
//                start(count);
//            }
//        }
//    }
//    
//    public void start(int count){
//        qno.setText("" + (count + 1) + ". ");
//        question.setText(q[count][0]);
//        opt1.setLabel(q[count][1]);
//        opt1.setActionCommand(q[count][1]);
//        opt2.setLabel(q[count][2]);
//        opt2.setActionCommand(q[count][2]);
//        opt3.setLabel(q[count][3]);
//        opt3.setActionCommand(q[count][3]);
//        opt4.setLabel(q[count][4]);
//        opt4.setActionCommand(q[count][4]);
//        options.clearSelection();
//    }
//    
//    public static void main(String[] args){
//        new myquestions("","").setVisible(true);
//    }
//}
