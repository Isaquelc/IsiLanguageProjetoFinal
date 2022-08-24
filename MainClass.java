import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
double  a;
double  b;
String  t1;
a= _key.nextDouble();
b= _key.nextDouble();
t1= _key.nextLine();
a = 1+2*3/b;
t1 = "textao sim";
if (a>b) {
System.out.println(a);}else {
System.out.println(b);}

while (a>b) {
System.out.println(a);a = a-1;}

  }}