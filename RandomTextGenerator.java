// Name:Chenyan Geng
// USC loginid:cgeng	
// CS 455 PA4
// Spring 2013
import java.util.*;
import java.io.*;

public class RandomTextGenerator{
static final int MAX = 80;
public RandomTextGenerator(ArrayList<String> s, int len, int num, PrintWriter out, String de){
 source = new ArrayList<String>(s);
 length = len;
 numWords = num;
 outFile = out;
 debug = de;
 ran = new Random();
 connect = new HashMap<Prefix,ArrayList<String>>();
} 
 
public void generate(){
 createConnect(connect, length, source);
 Prefix currprefix = getRandomPrefix();
 int currlength = 0;
 for(int i = 0; i <= numWords; i++){
  String newword = nextWord(currprefix, connect);
  boolean flag = true;
  while (flag){
   if (newword != " "){
    if(debug != ""){
     System.out.println("DEBUG: word generated: "+newword);
    }
    flag = false;
    currlength += (newword.length()+1);
    if(writeFile(newword, currlength)){
     currlength = newword.length()+1;
    }
    currprefix = currprefix.shift(newword);
    }
	else{
	 currprefix = getRandomPrefix();
	 newword = nextWord(currprefix, connect);
	}
   }
  }
}

public Prefix getRandomPrefix(){
 Prefix pre = new Prefix();
 boolean check = true;
 while(check){
  int begin = ran.nextInt(source.size());         //initiralize currprefix
  if((length+begin)<=source.size()){
   for(int i = begin; i < (length+begin); i++){
    pre = pre.add(source.get(i));
   }
   check = false;
  }
 }
 if(debug != ""){
  System.out.println("DEBUG: chose a new initial prefix: "+pre.getPre());
  }
 return pre;
}

public void createConnect(Map<Prefix,ArrayList<String>> premap, int len, ArrayList<String> file){
 Prefix curr = new Prefix();
 for(int i = 0; i < len; i++){
  curr = curr.add(file.get(i));
 }
 for(int i = len; i < file.size(); i++){
 String newWord = file.get(i);
 ArrayList<String> successor = new ArrayList<String>();
 if(premap.containsKey(curr)){
  successor = premap.get(curr);
  }
  successor.add(newWord);
  premap.put(curr,successor);
  curr = curr.shift(newWord);
  }
}

public String nextWord(Prefix pre, Map<Prefix,ArrayList<String>> premap){
 ArrayList<String> successor = premap.get(pre);
 if(successor != null){
  if(debug != ""){
  System.out.println("DEBUG: prefix: "+pre.getPre());
  System.out.println("DEBUG: successors: "+successor);
  }
  return successor.get(ran.nextInt(successor.size()));
 }
 else{
  if(debug != ""){
  System.out.println("DEBUG: prefix: "+pre.getPre());
  System.out.println("DEBUG: successors: <END OF FILE>");
  }
  return " ";
  }
}

public boolean writeFile(String newWord, int len){
 if((len-1) <= MAX){
  outFile.print(newWord+" ");
  return false;
 }
 else{
  outFile.println("\n"+newWord+" ");
  return true;
 }
}

// **************************************************************
//  PRIVATE INSTANCE VARIABLE(S)
 //private int length; //store the prefixLength
 private ArrayList<String> source;
 private int length;
 private int numWords;
 private PrintWriter outFile;
 private String debug;
 private Random ran;
 private Map<Prefix,ArrayList<String>> connect;
}
