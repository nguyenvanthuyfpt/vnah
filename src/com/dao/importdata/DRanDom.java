package com.dao.importdata;

import java.util.Random;

public class DRanDom  extends DSqlImportData
{
    public int[] getRandomLong(int aStart, int aEnd, int rows){
    int[] result=new int[rows];
      Random random = new Random();
      for (int idx = 1; idx <= rows; ++idx){
        result[idx-1]=showRandomInteger(aStart, aEnd, random);
      }
        return result;
    }
    
    public int showRandomInteger(int aStart, int aEnd, Random aRandom){
        int result=0;
      if ( aStart > aEnd ) {
        throw new IllegalArgumentException("Start cannot exceed End.");
      }
      //get the range, casting to long to avoid overflow problems
      long range = (long)aEnd - (long)aStart + 1;
      // compute a fraction of the range, 0 <= frac < range
      long fraction = (long)(range * aRandom.nextDouble());
      int randomNumber =  (int)(fraction + aStart);    
      result=randomNumber;
     return result; 
    }
    
    public String[] getRandomString(int aStart,int aEnd,int rows){
         String[] result=new String[rows];
        String str=new  String("QAa0 bcLd UK2eHf JgTP8X hiFj61DOk lNm9 nBoI5p GqYV rs3Ct SuMZvwW x4yE7zR");
        StringBuffer sb=new StringBuffer();
        Random r = new Random();
        int te=0;
         Random random = new Random();
         for (int idx = 1; idx <= rows; ++idx){
           long lengthStr=showRandomInteger(aStart, aEnd, random);
             for(int i=1;i<=lengthStr;i++){
                     te=r.nextInt((int)lengthStr);
                     sb.append(str.charAt(te));
             }
//             //.println("------"+idx+"-----"+sb.toString());
             result[idx-1]=sb.toString();
             sb=new StringBuffer();
         }
        return result;
     }
}
