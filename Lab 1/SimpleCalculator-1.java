

//package lab02;
import java.util.*;
public class SimpleCalculator {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
//String answer = input.next();
String answer = "y";
while (answer.toLowerCase().equals("y")) {
System.out.println("Enter an expression (with a space in
between): ");
double n1; char operator; double n2;
n1 = input.nextDouble();
operator = input.next().charAt(0);
n2 = input.nextDouble();
double result = -1;
switch(operator) {
case '+':
result = n1 + n2;
System.out.println("The result is " +
String.format("%.1f", result));
System.out.print("Do you want to continue? ");
answer = input.next();
break;
case '*':
result = n1 * n2;
System.out.println("The result is " +
String.format("%.1f", result));
System.out.print("Do you want to continue? ");
answer = input.next();
break;
case '-':
result = n1 - n2;
System.out.println("The result is " +
String.format("%.1f", result));
System.out.print("Do you want to continue? ");
answer = input.next();
break;
case '/':
if (n2 == 0) {
System.out.println("Cannot divide by zero!");
System.out.println("The result is " + result);
System.out.print("Do you want to continue? ");
answer = input.next();
}
else {
result = (double) n1 / n2;
System.out.println("The result is " +
String.format("%.1f", result));
System.out.print("Do you want to continue? ");
answer = input.next();}
break;
default:
System.out.println("Operator '"+operator+"' is not
supported!" +
"\nThe result is -1");
System.out.print("Do you want to continue? ");
answer = input.next();
break;
}
if (answer.toLowerCase().equals("n"))
break;
}
}
}
