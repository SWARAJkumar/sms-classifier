/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsClassifier;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
 /**
 *
 * @author SWARAJ
 */
public class Dictionary{
    Stemmer s =new Stemmer();
    public static ArrayList<String> current_dictionary = new ArrayList<String>();
    public int[][] trainerMatrix= new int[5573][6460];
    int current_count_of_dictionary=0;
    public synchronized void write(){
        boolean dictionary_making_procedure= false;
        int count_of_words_written=0;
       // try{
          //  BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));
            //Iterator itr = s.dictionary_from_traing.iterator();
           // while(itr.hasNext()){
                count_of_words_written = count_of_words_written+1;
             //   String str = (String)itr.next();
              //  System.out.println(count_of_words_written+"--"+str);
              //  writer.write(str+"\n");
            }
           // writer.close();
       // }catch(IOException e){
        //    e.printStackTrace();
      //  }
   // }
    
    public synchronized void read(){
         NaiveBayes nb= new NaiveBayes();
         nb.extract();
         int index=-1,count=0;
        System.out.println("trying to read ");
        try{
            BufferedReader reader = new BufferedReader(new FileReader("FinalStemmedSMS.txt"));
            String word = null;
            while((word= reader.readLine())!=null&&count<5573){
                System.out.println(word);
                String[] message = word.split("\\s");
                
                for(String w:message){  
                    index=-1;  
                    index= nb.dictionary.indexOf(w);
                 if(index!=-1 && index<6460)
                     trainerMatrix[count][index]=trainerMatrix[count][index]+1;
                }
                count=count+1;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        for(int i=0;i<5573;i++){
            int num=0;
            for(int j=0;j<6460;j++){
                if(trainerMatrix[i][j]==1)
                    num=num+1; 
            }
            System.out.println("row no:  "+num);
        }
    }
    
    public void displayArray(){
        int count=0;
        Iterator itr = current_dictionary.iterator();
        while(itr.hasNext()){
            count=count+1;
            System.out.println(count+"---"+(String)itr.next());
        }
    }
    
    public int sizeofArrayList(){
        int count=0;
        Iterator itr = current_dictionary.iterator();
        while(itr.hasNext()){
            count=count+1;
        }
        return count;
    }
}
