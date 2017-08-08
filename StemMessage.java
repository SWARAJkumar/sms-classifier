/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsClassifier;
import java.util.*;
/**
 *
 * @author SWARAJ
 */
public class StemMessage {
    public int[][] SMSMatrix=new int[2][6460];
    public void stemSMS(){
        NaiveBayes nb=new NaiveBayes();
        Stemmer s = new Stemmer();
        String[] str={"22 days to kick off! For Euro2004 U will be kept up to date with the latest news and results daily. To be removed send GET TXT STOP to 83222","Todays Vodafone numbers ending with 4882 are selected to a receive a £350 award. If your number matches call 09064019014 to receive your £350 award.</"};
        for(int w=0;w<str.length;w++){
            int matches=0;
            String[] word= str[w].split("\\s");
            for(int i=0;i<word.length;i++){
                char[] ch= word[i].toCharArray();
                for(int j=0;j<ch.length;j++){
                    ch[j] = Character.toLowerCase((char) ch[j]);
                    if (Character.isLetter((char) ch[j]))
                        s.add(ch[j]);
                }
                s.stem();
                String converted_text= s.toString();
                System.out.print(converted_text+"\t");
                int index=nb.dictionary.indexOf(converted_text);
                if(index!=-1){
                    SMSMatrix[w][index]=SMSMatrix[w][index]+1;
                    matches=matches+1;
                }
            }
            System.out.println("\nno of matches are : "+matches);
        }
         Iterator itr=nb.dictionary.iterator();
         while(itr.hasNext()){
             System.out.println(itr.next());
         }
    }   
    
}
