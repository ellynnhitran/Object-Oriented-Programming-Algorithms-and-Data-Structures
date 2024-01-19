

//package lab04;
import java.text.NumberFormat;
import java.util.*;
abstract class SoftwareEngineer {
String firstName;
double startingSalary;
double yearWorked;
abstract String getFirstName();
abstract double getCurrentSalary();
public SoftwareEngineer(String firstName, double startingSalary, double
yearWorked) {
this.firstName = firstName;
this.startingSalary = startingSalary;
this.yearWorked = yearWorked;
}
}
class JuniorSoftwareEngineer extends SoftwareEngineer{
public JuniorSoftwareEngineer(String firstName){
super(firstName, 5000, 0);
}
public JuniorSoftwareEngineer(String firstName, double startingSalary){
super(firstName, startingSalary, 0);
}
public JuniorSoftwareEngineer(String firstName, double startingSalary, int
yearWorked){
super(firstName, startingSalary, yearWorked);
}
public String getFirstName() {
return firstName;
};
public double getCurrentSalary() {
double startingSalary =
this.startingSalary*Math.pow(1.05,this.yearWorked);
return startingSalary;
}
}
class SeniorSoftwareEngineer extends SoftwareEngineer{
public SeniorSoftwareEngineer(String firstName){
super(firstName, 8000, 5);
}
public SeniorSoftwareEngineer(String firstName, double startingSalary){
super(firstName, startingSalary, 5);
}
public SeniorSoftwareEngineer(String firstName, double startingSalary, double
yearWorked){
super(firstName, startingSalary, yearWorked);
}
public String getFirstName() {
return firstName;
};
public double getCurrentSalary() {
double startingSalary = this.startingSalary*Math.pow(1.1,
this.yearWorked);
return startingSalary;
}
}
public class SoftwareCompany{
public static void main(String[] args) {
Locale locale = new Locale("en", "US");
NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
SoftwareEngineer engineer;
String[] inp = new String[4];
Scanner input = new Scanner(System.in);
System.out.print("Enter number of engineers: ");
int n = Integer.parseInt(input.nextLine());
for (int i = 1; i <= n; i++) {
System.out.println("Enter the information for engineer " + i + "
(Name, Junior/Senior, [startSalary], [yearWorks]): ");
inp = input.nextLine().split(" ");
if (inp.length == 2) {
if (inp[1].equals("Junior")) {
engineer = new JuniorSoftwareEngineer(inp[0]);
System.out.println("Engineer " + engineer.firstName +
" has current salary: " + fmt.format(engineer.getCurrentSalary()));
} else {
engineer = new SeniorSoftwareEngineer(inp[0]);
System.out.println("Engineer " +
engineer.getFirstName() + " has current salary: " +
fmt.format(engineer.getCurrentSalary()));
}
} else if (inp.length == 3) {
if (inp[1].equals("Junior")) {
engineer = new JuniorSoftwareEngineer(inp[0],
Double.parseDouble(inp[2]));
System.out.println("Engineer " + engineer.firstName +
" has current salary: " + fmt.format(engineer.getCurrentSalary()));
} else {
engineer = new SeniorSoftwareEngineer(inp[0],
Double.parseDouble(inp[2]));
System.out.println("Engineer " +
engineer.getFirstName() + " has current salary: " +
fmt.format(engineer.getCurrentSalary()));
}
} else if (inp.length == 4) {
if (inp[1].equals("Junior")) {
engineer = new JuniorSoftwareEngineer(inp[0],
Double.parseDouble(inp[2]), Integer.parseInt(inp[3]));
System.out.println("Engineer " + engineer.firstName +
" has current salary: " + fmt.format(engineer.getCurrentSalary()));
} else {
engineer = new SeniorSoftwareEngineer(inp[0],
Double.parseDouble(inp[2]), Integer.parseInt(inp[3]));
System.out.println("Engineer " +
engineer.getFirstName() + " has current salary: " +
fmt.format(engineer.getCurrentSalary()));
}
}
}
}
}
