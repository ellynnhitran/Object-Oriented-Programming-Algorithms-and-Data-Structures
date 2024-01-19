

Page
1
of 2
package lab05;
import java.util.*;
class Dish{
String name;
int cost;
Dish(String name, int cost){
this.name = name;
this.cost = cost;
}
}
class Customer{
String name;
int account;
Customer(String name, int account){
this.name = name;
this.account = account;
}
}
public class Restaurant {
Queue<Dish> dishes;
Queue<Customer> customers;
public Restaurant() {
dishes = new LinkedList<Dish>();
customers = new LinkedList<Customer>();
}
public void readDishes(Scanner sc) {
System.out.printf("Enter the number of dish(s): ");
int num = sc.nextInt();
for (int i = 0; i < num; i++) {
System.out.printf("Enter dish " + (i+1) + " (<name> <cost>): ");
String name; int cost;
name = sc.next();
cost = sc.nextInt();
Dish dish = new Dish(name, cost);
dishes.add(dish);
}
}
public void readCustomers(Scanner sc) {
System.out.printf("Enter the number of waiting customer(s): ");
int num = sc.nextInt();
for (int i = 0; i < num; i++) {
System.out.printf("Enter customer " + (i+1) + " (<name>
<account>): ");
String name; int account;
name = sc.next();
account = sc.nextInt();
Customer customer = new Customer(name, account);
customers.add(customer);
}
}
public void processOrders() {
while (!dishes.isEmpty()){
for (int i = 0; i < customers.size(); i++)
if (dishes.peek().cost > customers.peek().account){
dishes.remove();
}
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
Restaurant testObj = new Restaurant();
testObj.readDishes(sc);
testObj.readCustomers(sc);
testObj.processOrders();
sc.close();
}
}
