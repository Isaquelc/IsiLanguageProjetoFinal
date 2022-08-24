import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
double  a;
double  b;
boolean  z;
String  t1;
a= _key.nextDouble();
b= _key.nextDouble();
a = 1+2*3/b;
z = true;
z = false;
if (a>b) {
System.out.println(a);}else {
System.out.println(b);}

while (a>b) {
System.out.println(a);a = a-1;}

do {
System.out.println(b);b = b+1;
} while (a<b);
  }}