import java.io.IOException;
import org.jsoup.select.Elements;
//import java.util.Scanner;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;


 

public class scrapData {

    static class scrapPage {


        public String ScrapPage(Object link) throws IOException{
            Document doc = Jsoup.connect(link.toString()).timeout(6000).get();

            Elements entryContent = doc.select("div.entry-content").select("p");
            Elements ans = doc.select("div.entry-content").select(".collapseomatic_content");
            String[] ansArray = new String[ans.toArray().length];
            int i = 0;

            for(Element e: ans){
                ansArray[i] = e.text().substring( 0, e.text().indexOf(" E") );
                i++;
                // System.out.println( e.text().substring( e.text().indexOf(" E") ).replace(" E","E") );
            }

            // int entryContentLength = entryContent.toArray().length;

            // System.out.println(ansArray.length + " " + entryContentLength); 
            // String[][] mainData = new String[entryContentLength][5];
            // int index = 0;

            

            for(Element e: entryContent){

                if (e.text().indexOf(("d)")) != -1){ 
                    // index++;
                    try{
                    int a = e.text().indexOf(("a)"));
                    int b = e.text().indexOf(("b)"));
                    int c = e.text().indexOf(("c)"));
                    int d = e.text().indexOf(("d)"));
                    int f = e.text().indexOf(("V"));
                    int dot = e.text().indexOf((". "));

                    System.out.print("{");
                    System.out.println();
                    System.out.print("'Question': '");
                    System.out.print( e.text().substring(0,dot) + ") " );
                    

                    String[] data = new String[5];
                    data[0] = e.text().substring(dot, a).replace(". ", "") + "',";
                    data[1] = e.text().substring(a, b);
                    data[2] = e.text().substring(b, c);
                    data[3] = e.text().substring(c, d);
                    data[4] = e.text().substring(d, f);

                    System.out.print(data[0]);
                    System.out.println();

                    System.out.print("'Options': ");
                    System.out.print("[");
                    System.out.print("'");
                    System.out.print(data[1]);
                    System.out.print("'");
                    System.out.print(",");
                    System.out.print("'");
                    System.out.print(data[2]);
                    System.out.print("'");
                    System.out.print(",");
                    System.out.print("'");
                    System.out.print(data[3]);
                    System.out.print("'");
                    System.out.print(",");
                    System.out.print("'");
                    System.out.print(data[4]);
                    System.out.print("'");
                    System.out.print("]");

                    System.out.println();
                    System.out.print("},");
                    System.out.println();

                    
                  

                    // qj.put("Options",list);

                    // System.out.println(list);
                    // data[5] = ansArray[index];

                    // for(String iterator: data){ System.out.println(iterator);}


                    // mainData[index] = data;
                    // mainData[index][0] = e.text().substring(dot, a).replace(". ", "");
                    // mainData[index][1] = e.text().substring(a, b);
                    // mainData[index][2] = e.text().substring(b, c);
                    // mainData[index][3] = e.text().substring(c, d);
                    // mainData[index][4] = e.text().substring(d, f);
                    // index++;

                    // System.out.println( e.text().substring(dot, a).replace(". ", "") );
                    // System.out.println( e.text().substring(a, b) );
                    // System.out.println( e.text().substring(b, c) );
                    // System.out.println( e.text().substring(c, d) );
                    // System.out.println( e.text().substring(d, f) );
                    }
                    catch(Exception P){System.out.println();}
                     
                } 
                // else if (e.text().indexOf(("b)")) != -1){
                //     index++;
                //     try{
                //         int a = e.text().indexOf(("a)"));
                //         int b = e.text().indexOf(("b)"));
                //         int f = e.text().indexOf(("V"));
                //         int dot = e.text().indexOf((". "));
    
    
    
                //         System.out.print( e.text().substring(0,dot) + ") " );
    
                //         String[] data = new String[4];
                //         data[0] = e.text().substring(dot, a).replace(". ", "");
                //         data[1] = e.text().substring(a, b);
                //         data[2] = e.text().substring(b, f);
                //         data[3] = ansArray[index];
    
                //         for(String iterator: data){ System.out.println(iterator);}
    
                //         }
                //         catch(Exception P){System.out.println();}
                // }
            }

            // System.out.println(mainData.length);
            // for(int i=0; i< mainData.length; i++){
            //     for(int j=0; j<mainData[i].length; j++ ){
            //         System.out.println(mainData[i][j]);
            //     }
            // }
            // System.out.println(index);
            return "a";
        }
    
    }
    
    public static void main(String[] args)throws IOException{
        Document doc = Jsoup.connect("https://www.sanfoundry.com/1000-object-oriented-programming-oops-questions-answers/#oops-concept-features").timeout(6000).get();
        Elements page_links = doc.select("table[border]").select("a");
        // System.out.println(page_links);

        // Object[] obj = page_links.toArray();
        // System.out.println(obj.length);
//        Scanner input = new Scanner(System.in);
        scrapPage page = new scrapPage();
        // page.ScrapPage(page_links.attr("href"));
        for(Element e: page_links){
            page.ScrapPage(e.attr("href"));
//            int inputoption = input.nextInt();
//            System.out.println(inputoption);
        }
//        input.close();
        


    }
}
