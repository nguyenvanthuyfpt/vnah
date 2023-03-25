package com.action.doc.assign;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyFile {

    public static void copyFile(String fromFile,String toFile) throws IOException{
            try  {
                FileInputStream fileIn=new FileInputStream(fromFile);//tao inputStream de doc file
                FileOutputStream fileOut=new FileOutputStream(toFile);//tao outputStream de ghi file
                byte[] data=new byte[20024];
                int r=fileIn.read(data);//doc mang data tu fileIn (file duoc doc)
                while(r!=-1){
                        fileOut.write(data,0,r);//ghi du lieu vao file moi
                        r=fileIn.read(data);
                }
                fileIn.close();
                fileOut.close();    
            } catch (Exception ex)  {
                ex.printStackTrace();
            } finally  {
            }
            
            
    }
   
} 