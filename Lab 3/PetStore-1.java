//package lab04;
import java.util.*;
class Animal {
public String name, color, type;
public int age;
public static int available = 100;
protected static void update() {
available -= 1;
}
public Animal(String name, String color, int age) {
this.name = name;
this.color = color;
this.age = age;
}
public void display() {
if (this.age > 1) {
} else {
}
}
public void speak() {}
}
class Dog extends Animal {
// constructors
public Dog(String name) {
super(name, "white", 1);
}
public Dog(String name, String color) {
super(name, color, 1);
}
public Dog(String name, String color, int age) {
super(name, color, age);
}
@Override
public void speak() {
System.out.println("bark bark ...");
}
@Override
public void display() {
System.out.println(name + " is " + color + " and " + age + " years
old." +
"\nThis is a very good dog.");
}
}
class Cat extends Animal {
// constructors
public Cat(String name) {
super(name, "white", 1);
}
public Cat(String name, String color) {
super(name, color, 1);
}
public Cat(String name, String color, int age) {
super(name, color, age);
}
@Override
public void speak() {
System.out.println("meow meow ...");
}
@Override
public void display() {
System.out.println(name + " is " + color + " and " + age + " years
old." +
"\nThis is a very good cat.");
}
}
public class PetStore {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
final int lucky = 68;
String[] inp = new String[3];
String type;
int userNumber;
Animal animal;
String choose;
do {
System.out.printf("Welcome to the PetStore vending machine, we
currently have %d pets left.\n",
Animal.available);
System.out.print("Enter a number that you like (less than 100):
");
userNumber = Integer.parseInt(input.nextLine());
System.out.print("What type of pet do you want (cat or dog)? ");
type = input.nextLine();
System.out.print("Enter the information for your pet (name,
color, age): ");
inp = input.nextLine().split(" ");
if (inp.length == 1) {
if (type.equals("cat")) {
animal = new Cat(inp[0]);
} else {
animal = new Dog(inp[0]);
}
} else if (inp.length == 2) {
if (type.equals("cat")) {
animal = new Cat(inp[0], inp[1]);
} else {
animal = new Dog(inp[0], inp[1]);
}
} else {
if (type.equals("cat")) {
animal = new Cat(inp[0], inp[1],
Integer.parseInt(inp[2]));
} else {
animal = new Dog(inp[0], inp[1],
Integer.parseInt(inp[2]));
}
}
System.out.println();
animal.display();
System.out.println("Let's listen to it saying something...");
animal.speak();
System.out.println();
if (userNumber == lucky) {
System.out.println("Congratulations, you get a big round of
applause.");
} else {
System.out.println("Sorry, you are not the lucky one.");
}
Animal.update();
System.out.println();
System.out.print("Enter B/b to buy a pet and Q/q to quit: ");
choose = input.nextLine().toLowerCase();
System.out.println();
System.out.println("Loading...");
System.out.println();
} while (choose.equals("b"));
System.out.println("Good bye.");
input.close();
}
}
