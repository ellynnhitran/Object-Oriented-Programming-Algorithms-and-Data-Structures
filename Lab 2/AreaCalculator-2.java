

//package lab03;
import java.util.*;
public class AreaCalculator {
public static void main(String[] args) {
Circle circle = new Circle();
Square square = new Square();
int i;
Scanner input = new Scanner(System.in);
System.out.print("Enter the number of circles: ");
int num = input.nextInt();
for(i=0; i < num; i++) {
System.out.print("Enter the radius of a circle: ");
double dimension = input.nextDouble();
circle = new Circle(dimension);
System.out.println("Circle with radius "+ String.format("%.2f",
dimension) + " is created.");
circle.displayArea();
}
System.out.print("Enter the number of squares: ");
int num2 = input.nextInt();
for(i=0; i < num2; i++) {
System.out.print("Enter the side of a square: ");
double dimension = input.nextFloat();
square = new Square(dimension);
System.out.println("Square with side "+ String.format("%.2f",
dimension) + " is created.");
square.displayArea();
}
SimpleShape.displayNumbeOfShapes();
}
}
class SimpleShape {
double dimension;
static int count;
SimpleShape(){
count++;
}
public static void displayNumbeOfShapes() {
System.out.print(count + " shapes created.");
}
}
class Circle extends SimpleShape{
Circle(){
}
Circle(double dimension){
this.dimension = dimension;
}
public void displayArea() {
double area = (double) dimension * dimension * Math.PI;
System.out.println("The area of circle with radius " +
String.format("%.2f", dimension) + " is " + String.format("%.2f", area));
return;
}
}
class Square extends SimpleShape{
Square(){
}
Square(double dimension){
this.dimension = dimension;
}
public void displayArea() {
double area = (double) dimension * dimension;
System.out.println("The area of square with side " +
String.format("%.2f", dimension) + " is " + String.format("%.2f", area));
return;
}
}
