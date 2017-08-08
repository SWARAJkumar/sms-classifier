package smsClassifier;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SmsClassifier{
    public static void main(String[] args) {
        
        Dictionary dict= new Dictionary();
        Stemmer s = new Stemmer();
        NaiveBayes nb =new NaiveBayes();
        ReadClass r = new ReadClass();
        StemMessage sm=new StemMessage();
        nb.extract();
        nb.trainingMatrix(5573);
        sm.stemSMS();
        nb.classifySMS(sm.SMSMatrix);
    }
  
}