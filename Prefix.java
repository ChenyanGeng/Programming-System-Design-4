// Name:Chenyan Geng
// USC loginid:cgeng	
// CS 455 PA4
// Spring 2013
import java.util.*;


public class Prefix{

 public Prefix(){
  prefix = new LinkedList<String>();
 }
 
 public LinkedList<String> getPre(){
  return this.prefix;
 }
 
 public Prefix shift(String s){
 Prefix re = new Prefix();
 ListIterator<String> iter = prefix.listIterator();
 while(iter.hasNext()){
  re.prefix.add(iter.next());
  }
 re.prefix.removeFirst();
 re.prefix.add(s);
 return re;
 }
 
 public Prefix add(String s){
 Prefix re = new Prefix();
 ListIterator<String> iter = prefix.listIterator();
 while(iter.hasNext()){
  re.prefix.add(iter.next());
  }
 re.prefix.add(s);
 return re;
 }
 
 public int hashCode(){
 int re = 0;
 ListIterator<String> iter = prefix.listIterator(); 
 while(iter.hasNext()){
  re = re+iter.next().length();
  }
  return re;
 }
 
 public boolean equals(Object o){
  Prefix p = (Prefix)o;
  if(p.prefix.size()!=prefix.size()){
   return false;
   }
  else{
   ListIterator<String> iter = prefix.listIterator();
   ListIterator<String> iterp = p.prefix.listIterator();
   while(iter.hasNext()){
    if(!(iter.next()).equals(iterp.next())){
	 return false;
	}
   }
   return true;
  }
 }
// **************************************************************
//  PRIVATE INSTANCE VARIABLE(S)
 //private int length; //store the prefixLength
 private LinkedList<String> prefix;
}










