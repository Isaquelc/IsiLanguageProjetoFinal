import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
double  a;
double  b;
String  maior;
boolean  z;
String  t1;
maior = " maior que ";
a= _key.nextDouble();
b= _key.nextDouble();
z = true;
t1 = "Verdade";
System.out.println(t1);
if (a>b) {
System.out.println(a);System.out.println(maior);System.out.println(b);}else {
System.out.println(b);System.out.println(maior);System.out.println(a);}

while (a<100) {
System.out.println(a);a = a+1;}

do {
System.out.println(b);z = false;
} while (a>b);
System.out.println(a);
System.out.println(b);
  }}