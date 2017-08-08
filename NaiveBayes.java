/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsClassifier;
import java.io.*;
import java.util.*;
import java.lang.*;
/**
 *
 * @author SWARAJ
 */
public class NaiveBayes {
    public static ArrayList<String> dictionary = new ArrayList<String>();
    double prior_spam=0d,prior_ham=0d;
    double[] likelihood_spam= new double[6460];
    double[] likelihood_ham= new double[6460];
    
    public void extract(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Dictionary.txt"));
            String word=null;
            while((word=reader.readLine())!=null){
                dictionary.add(word);
            }
        }catch(IOException e){
            e.printStackTrace();
        }  
    }
    
    public void trainingMatrix(int size_of_training_data){
       ReadClass r= new ReadClass();
       Dictionary dict= new Dictionary();
       r.readCSV();// Y made
       dict.read();// dictonary and trainer matrix made
      // 1 is spam and 0 is ham
      int N_spam=0,N_ham=0;
      int[] Nspam_words= new int[6460];
      int[] Nham_words= new int[6460];
     
      
      for (int i=0;i<size_of_training_data;i++){
           if(r.Y[i]==1){
               N_spam=N_spam+1;
               for(int j=0;j<6460;j++){
                   if(dict.trainerMatrix[i][j]==1){
                       Nspam_words[j]=Nspam_words[j]+1;
                   }
               }
           }
           else{
                N_ham=N_ham+1;
                for(int j=0;j<6460;j++){
                   if(dict.trainerMatrix[i][j]==1){
                       Nham_words[j]=Nham_words[j]+1;
                   }
               }
            }
      }
      prior_spam=(double)N_spam/size_of_training_data;
      prior_ham=(double)N_ham/size_of_training_data;
      
      int[] prediction= new int[r.Y.length];
      double Lspam=0d,Lham=0d;
      for(int i=0;i<6460;i++){
            likelihood_spam[i]=(double)(Nspam_words[i]+1)/(N_spam+2);
            likelihood_ham[i]=(double)(Nham_words[i]+1)/(N_ham+2);
      }
      System.out.println((prior_ham)+"   "+(prior_spam));
      Scanner sc=new Scanner(System.in);
      String str= sc.next();
              
      System.out.println("pritning the results obtained\n");
      for(int i=0;i<size_of_training_data;i++){
          Lspam=Math.log(prior_spam);
          Lham=Math.log(prior_ham);
          for(int j=0;j<6460;j++){
              if(dict.trainerMatrix[i][j]==1&& likelihood_spam[j]!=0&&likelihood_ham[j]!=0){
                  Lspam=Lspam+Math.log(likelihood_spam[j]);
                  Lham=Lham+Math.log(likelihood_ham[j]);                  
              }
              else{
                if(likelihood_spam[j]!=1&&likelihood_ham[j]!=1){  
                  Lham=Lham+Math.log(1-likelihood_ham[j]); 
                  Lspam=Lspam+Math.log(1-likelihood_spam[j]); 
                }  
              }
          }
          System.out.println(Lham+"  Spam prob:  "+Lspam);
          if(Lham>=Lspam){
              prediction[i]=0;//0 is for not a spam
          }
          else
              prediction[i]=1;
           System.out.println(prediction[i]);
      }
      int accuracy=0,flag=0;
      for(int i=0;i<r.Y.length;i++){
          if(r.Y[i]==prediction[i])
             accuracy=accuracy+1;
          if(prediction[i]==1)
              flag=flag+1;
      }
      System.out.println("Accuracy : "+(double)accuracy/size_of_training_data*100+
              "\nThe no of spam detected : "+flag);
      String d=sc.next();
    }
    
    
    public void classifySMS(int[][] SMSMatrix){
        double Lspam=0d,Lham=0d;
        System.out.println("classify message lspam"+prior_spam +"  "+prior_ham);
        int[] prediction=new int[SMSMatrix.length];
         for(int i=0;i<SMSMatrix.length;i++){
          Lspam=Math.log(prior_spam);
          Lham=Math.log(prior_ham);
          for(int j=0;j<6460;j++){
              if(SMSMatrix[i][j]==1&& likelihood_spam[j]!=0&&likelihood_ham[j]!=0){
                  Lspam=Lspam+Math.log(likelihood_spam[j]);
                  Lham=Lham+Math.log(likelihood_ham[j]);                  
              }
              else{
                if(likelihood_spam[j]!=1&&likelihood_ham[j]!=1){  
                  Lham=Lham+Math.log(1-likelihood_ham[j]); 
                  Lspam=Lspam+Math.log(1-likelihood_spam[j]); 
                }  
              }
          }
          System.out.println(Lham+"  Spam prob:  "+Lspam);
          if(Lham>=Lspam){
              prediction[i]=0;//0 is for not a spam
          }
          else
              prediction[i]=1;
          System.out.println(prediction[i]);
      }
      
    }
}

