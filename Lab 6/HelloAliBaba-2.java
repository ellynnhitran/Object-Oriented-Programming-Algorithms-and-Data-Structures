

package lab07;
import java.util.*;
public class HelloAliBaba {
public static void main(String[] args) {
Set<String> treeSet = new TreeSet<String>();
String[] name = new String[2];
Scanner sc = new Scanner(System.in);
while (true) {
System.out.print("What is his/her name? ");
name = sc.nextLine().strip().split(" ");
if(name.length == 2) {
String firstname = name[0];
String lastname = name[1];
firstname = firstname.substring(0, 1).toUpperCase() +
firstname.substring(1).toLowerCase() ;
lastname = lastname.substring(0, 1).toUpperCase() +
lastname.substring(1).toLowerCase() ;
String name2 = firstname + " " + lastname;
if (treeSet.contains(name2) ) {
System.out.println("Hi " + name2 +", have a good day!");
}
else if (!treeSet.contains(name2) ) {
System.out.println("Hello, nice to meet you!");
treeSet.add(name2);
}
}
else if (name.length == 1) {
String name1 = name[0];
name1 = name1.substring(0, 1).toUpperCase() +
name1.substring(1).toLowerCase() ;
if (treeSet.contains(name1) ) {
System.out.println("Hi " + name1 +", have a good day!");
}
else if (name[0].equals("exit") ||
name[0].toUpperCase().equals("exit")) {
System.out.println("Goodbye!");
break;
}
else if (!treeSet.contains(name1) ) {
System.out.println("Hello, nice to meet you!");
treeSet.add(name1);
}
}
}
}
}
