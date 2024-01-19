
package lab12;
import java.util.*;
class Subject{
String name;
boolean status;
ArrayList <Subject> adj = new ArrayList <Subject>();
public Subject(String name) {
this.name = name;
this.status = true;
}
}
public class Schedule {
TreeMap<String,Subject> subjects = new TreeMap<String, Subject>(); // List of
subjects that the user is studying
Stack <Subject> myStack = new Stack<Subject>(); // Order of subject (after
sorting using topo sort)
TreeSet<String> visited; // use in dfs
TreeSet<String> ban = new TreeSet<String>(); // list of subjects that have
been banned
void print() {
update(); // update the order before printing
Stack<Subject> tmp = (Stack<Subject>) myStack.clone();
if(!tmp.isEmpty()) {
System.out.println("Learn order:");
System.out.printf("%s", tmp.pop().name);
}
else {
System.out.printf("You have no subject in your list.\n");
return;
}
while(!tmp.isEmpty()) {
System.out.printf(" --> %s", tmp.pop().name);
}
System.out.println();
}
void add(String A) {
// Check if A has not been banned and the user is not studying A then
add A to the list, else report the user (follow the example)
//[Enter your code here]
if (!ban.contains(A) && !subjects.containsKey(A)) {
Subject subject = new Subject(A);
subjects.put(A, subject);
}
if (ban.contains(A)){
System.out.println("you are not allowed to study " + A + ".");
}
}
void add(String A, String B) {
// Check if A and B have not been banned and the user is not studying A
and/or B then add A and/or B to the list, else report the user (follow the example)
// remember to update the adj list
//[Enter your code here]
if (ban.contains(A) || ban.contains(B)) {
return;
}
if (!subjects.containsKey(A)) {
Subject subject = new Subject(A);
subjects.put(A, subject);
}
if (!subjects.containsKey(B)){
Subject subject2 = new Subject(B);
subjects.put(B, subject2);
}
Subject sub1 = subjects.get(A);
Subject sub2 = subjects.get(B);
sub1.adj.add(sub2);
}
void remove(String A) {
// check whether the user is studying subject A or not. If not, report
the user (follow the example)
if(!subjects.containsKey(A)) {
System.out.printf("You are not studying %s.\n", A);
return;
}
// dfs from subject A to find all subjects that will be banned (use the
below dfs method, the banned subjects will be stored in myStack)
visited = new TreeSet<String>();
Subject subject_ = subjects.get(A);
myStack.clear();
dfs(subject_);
//[Enter your code here]
myStack.pop();
ban.add(subject_.name);
subjects.remove(subject_.name);
// Check if removing subject A affects other subjects or not (follow
the example)
//[Enter your code here]
ban.add(subject_.name);
if (myStack.isEmpty()) {
System.out.println("Remove subject " + A + " successfully.
removing this subject does not affect other subjects.");
}
System.out.print("Remove subject " + A + " successfully. Note that due
to removing this subject");
while (!myStack.isEmpty()) {
Subject sub = myStack.pop();
ban.add(sub.name);
subjects.remove(sub.name);
System.out.print(", " + sub.name);
}
System.out.print(" will also be removed.");
System.out.println();
}
void dfs(Subject s) {
// Do not touch
visited.add(s.name);
for(Subject v: s.adj) {
if(!visited.contains(v.name) && subjects.containsKey(v.name)) {
dfs(v);
}
}
myStack.push(s);
}
void update() {
// Do not touch
// topo sort - https://www.geeksforgeeks.org/topological-sorting/
while(!myStack.isEmpty()) {
myStack.pop();
}
visited = new TreeSet<String>();
for(String s: subjects.keySet()) {
// if subject s has not been visited, dfs(subject s)
// [Enter your code here]
if (!visited.contains(s)) {
Subject sub_ = subjects.get(s);
dfs(sub_);
}
}
}
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
Schedule schedule = new Schedule();
System.out.print("Enter your name: ");
String username = input.nextLine();
System.out.println("Hello " + username + ". Let's make your learn
order!");
while(true) {
System.out.println("Enter your query: ");
String[] inp = input.nextLine().split(" ");
if(inp[0].toLowerCase().equals("exit")) {
System.out.println("Thank you for using our service!");
break;
}
if(inp[0].toLowerCase().equals("print")) {
schedule.print();
}
else if(inp[0].toLowerCase().equals("remove")) {
schedule.remove(inp[1]);
}
else if(inp.length == 2) {
schedule.add(inp[1]);
}
else {
schedule.add(inp[1],inp[2]);
}
}
input.close();
}
}
