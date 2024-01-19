
//package lab03;
import java.util.*;
public class CubeVolume {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
System.out.print("Case 1. Enter a space key to create a cube with
length 1.00 and color blue, or"+
"\nCase 2. Enter one number to create a cube with length s and color
blue, or"+
"\nCase 3. Enter a number and a color to create a cube with
length s and color c: ");
String[] arr = input.nextLine().split(" ");
double dimension = Double.parseDouble(arr[0]);
String color;
if (arr.length == 0) {
Cube.cubeInformation(1, 1, 1);
RectangularPrism.getSurfaceArea(1, 1, 1);
RectangularPrism.getVolume(1, 1, 1);
Cube.printColor("blue");
}
else if (arr.length == 1)
{
Cube.cubeInformation(dimension, dimension, dimension);
RectangularPrism.getSurfaceArea(dimension, dimension, dimension);
RectangularPrism.getVolume(dimension, dimension, dimension);
}
else if (arr.length == 2) {
Cube.cubeInformation(dimension, dimension, dimension);
RectangularPrism.getSurfaceArea(dimension, dimension, dimension);
RectangularPrism.getVolume(dimension, dimension, dimension);
Cube.printColor(arr[1]);
}
}
}
class RectangularPrism{
float length, width, height;
static void getSurfaceArea(double length, double width, double height)
{
double surfacearea = 2*(length*height + height*width +
length*width);
System.out.println("\nComputing the surface area..." + "\nThe
surface area of the cube is " + String.format("%.2f", surfacearea));
return;
}
static void getVolume(double length, double width, double height) {
double volume = length*height*width;
System.out.println("Computing the volume..."+"\nThe volume of the
cube is " + String.format("%.2f", volume));
return;
}
}
class Cube extends RectangularPrism{
String color;
static void printColor(String color) {
System.out.println("The cube is "+color);
}
static void cubeInformation(double length, double width, double height)
{
//show cube information
System.out.print("Cube's detail: "+
"\nlength: "+ (double)length +
"\nwidth: " + (double) width +
"\nheight " + (double) height);
}
}
