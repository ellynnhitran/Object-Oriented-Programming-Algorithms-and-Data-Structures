import java.util.*;
import java.text.DecimalFormat;
import java.math.*;
public class CylinderVolume {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
System.out.print("Enter the radius and length of a cylinder: ".trim());
double n1, n2;
n1 = scanner.nextDouble();
n2 = scanner.nextDouble();
var area = n1*n1*Math.PI;
var volume = area * n2;
DecimalFormat df = new DecimalFormat("0.00");
System.out.println( "The area is " + df.format(area));
System.out.println( "The volume is " + df.format(volume));
}
}
