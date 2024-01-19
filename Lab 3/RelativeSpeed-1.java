

//package lab04;
import java.util.*;
interface Vehicle{
void speed(int s);
int getSpeed();
void setDirection(boolean backward);
String getFirstName();
boolean isBackward();
int calculateRelativeSpeed(Vehicle v);
}
class Motorbike implements Vehicle{
int speed; boolean direction; String name;
public void speed(int s) {
this.speed= s;
}
public int getSpeed() {
return speed;
};
public void setDirection(boolean backward) {
this.direction = backward;
};
public String getFirstName() {
return name;
};
public boolean isBackward() {
return direction;
};
public int calculateRelativeSpeed(Vehicle v) {
if (isBackward() == true && v.isBackward() == true) {
return Math.abs(speed - v.getSpeed());
}
else if (isBackward() == false && v.isBackward() == true) {
return Math.abs(speed + v.getSpeed());
}
else if (isBackward() == true && v.isBackward() == false) {
return Math.abs(speed + v.getSpeed());
}
else if (isBackward() == false && v.isBackward() == false) {
return Math.abs(speed - v.getSpeed());
}
return speed;
};
}
public class RelativeSpeed{
public static void main(String[] args) {
Vehicle vehicle1 = new Motorbike();
Vehicle vehicle2 = new Motorbike();
int speed1, speed2;
Scanner input = new Scanner(System.in);
System.out.print("Enter the name of the first vehicle: ");
String name1 = input.nextLine();
System.out.print("Enter the vehicle's speed and backward: ");
speed1 = input.nextInt();
boolean direction1 = input.nextBoolean();
input.nextLine();
System.out.print("Enter the name of the second vehicle: ");
String name2 = input.nextLine();
System.out.print("Enter the vehicle's speed and backward: ");
speed2 = input.nextInt();
boolean direction2 = input.nextBoolean();
input.nextLine();
if (direction1 == direction2) {
System.out.println(name1 + " and " + name2 + " go in the same
direction.");
}
else {
System.out.println(name1 + " and " + name2 + " go in the opposite
directions!!!");
}
vehicle1.speed(speed1);
vehicle2.speed(speed2);
vehicle1.setDirection(direction1);
vehicle2.setDirection(direction2);
System.out.print("Their relative speed is " +
vehicle1.calculateRelativeSpeed(vehicle2));
}
}
