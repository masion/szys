package com.example.sudoku;
import java.math.*;
public class Core 
{
//	char[]h={'+','-','*','/'};
	int resultfront,resultback,result;
    public int Cal(int i1,int i2,int a,int b,int c)//加法
    {
    	if(i2==2||i2==3){
      		switch (i2){
    		case 2:resultback=b*c;break;
    		case 3:resultback=b/c;break;}
      		switch (i1){
    		case 0:result=a+resultback;break;
    		case 1:result=a-resultback;break;
    		case 2:result=a*resultback;break;
    		case 3:result=a/resultback;break;}
            return result;
          }
    	else
    	{
      		switch (i1){
    		case 0:resultfront=a+b;break;
    		case 1:resultfront=a-b;break;
    		case 2:resultfront=a*b;break;
    		case 3:resultfront=a/b;break;}
        	 
      		switch (i2){
    		case 0:result=resultfront+c;break;
    		case 1:result=resultfront-c;break;
    		case 2:result=resultfront*c;break;
    		case 3:result=resultfront/c;break;}
            return result;
            }
        	
    }
    public int Add(int i1,int i2,int a,int b,int c)//加法
    {
        
        return a+b;
    }
    public int Subtraction(int a,int b)//减法
    {
        
        return a-b;
    }
    public int multiplication(int a,int b)//减法
    {
        return a*b;
    }
    public int Division(int a,int b)//除法
    {
       
        return a/b;
    }
}
