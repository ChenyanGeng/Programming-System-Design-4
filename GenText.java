// Name:Chenyan Geng
// USC loginid:cgeng	
// CS 455 PA4
// Spring 2013
import java.util.*;
import java.io.*;

class GenException extends Exception{ //my own exception 
 public GenException(){}
 public GenException(String msg){
  super(msg);
  }
 }
 
public class GenText{
 public static void main(String[] args){
  String prefixLength = "";
  String numWords = "";
  String sourceFile = "";
  String outFile = "";
  String debug = "";
  int length=0;
  int num=0;
  ArrayList<String> source = new ArrayList<String>();
  
  try{
   if(args.length==4){
    prefixLength = args[0];
    numWords = args[1];
    sourceFile = args[2];
    outFile = args[3];
   }
   else if(args.length==5){
    debug = args[0];
    prefixLength = args[1];
    numWords = args[2];
    sourceFile = args[3];
    outFile = args[4];
   }
   else if(args.length<4){
    missArgs();
   }    
   if(!(prefixLength.matches("[0-9]+")||numWords.matches("[0-9]+"))){ 
    wrongType(); 
   }
   else{
   length = Integer.parseInt(prefixLength);
   num = Integer.parseInt(numWords);
   if ((length < 1)||(num < 0)){
    errRange();
   }
   }
  
   File input = new File(sourceFile);
   Scanner in = new Scanner(input);
   while(in.hasNext()){
    source.add(in.next());
    }
   if(length >= source.size()){
    tooLong();
   }
   }
  catch(FileNotFoundException e){
   System.out.println("ERROR: File '"+ sourceFile +"' does not exist.");
   System.exit(0);
   }
  catch (GenException e){
   System.out.println(e.getMessage());
   correctForm();
   System.exit(0);
  }	
  try{
   File output = new File(outFile);
   PrintWriter out = new PrintWriter(output);
   RandomTextGenerator r = new RandomTextGenerator(source, length, num, out, debug);
   r.generate();
   out.close();
   }
   catch(FileNotFoundException e){
   System.out.println("ERROR: Can't write to File'"+ outFile +"'.");
   System.exit(0);
   }
 }
 
private static void correctForm(){
System.out.println("Requirements:");
System.out.println("The right type of command line should be as follow:");
System.out.println("java GenText prefixLength numWords sourceFile outFile ");
System.out.println("or ");
System.out.println("java GenText [-d] prefixLength numWords sourceFile outFile ");
System.out.println("where [-d] means in debug modle.");
}

private static void wrongType() throws GenException{
 throw new GenException("ERROR: Wrong input type. \nReminder: Both prefixLength and numWords arguments should be integers");
} 
private static void errRange() throws GenException{
 throw new GenException("ERROR: Out of range. \nReminder: prefixLength should >= 1 and numWords should >= 0");
} 
private static void tooLong() throws GenException{
 throw new GenException("ERROR: prefixLength too long. \nReminder: prefixLength should < number of words in sourceFile");
} 
private static void missArgs() throws GenException{
 throw new GenException("ERROR: missing command-line arguments. \nReminder: there should be 4 or 5 arguments");
} 
}
