
package lab13;
import java.util.*;
class Student{
String name;
double score;
public Student(String name, double score) {
this.name = name;
this.score = score;
}
public void display() {
// print the lowest score and the owner
//[Enter your code here]
System.out.printf("The lowest score of midterm exam belongs to %s
(%.2f/20)", this.name, this.score);
System.out.println();
}
}
public class MinHeap {
private int maxSize;
private int size = 0;
private Student[] minHeap;
public MinHeap(int maxSize) {
this.maxSize = maxSize;
minHeap = new Student[this.maxSize*2];
for(int i=0;i<maxSize;i++) {
minHeap[i] = null;
}
}
public Student getLowestScore() {
if(this.size == 0) {
return null;
}
// return the student who has the lowest score
return minHeap[0];
}
private boolean isLeaf(int pos) {
// check if the node at position pos is a leaf or not
//[Enter your code here]
if ((2*pos + 1) >= size ) {
return true;
}
return false;
}
private void swap(int pos1, int pos2) {
// swap 2 students at position pos1 and pos2
//[Enter your code here]
Student tmp;
tmp = minHeap[pos1];
minHeap[pos1] = minHeap[pos2];
minHeap[pos2]= tmp;
}
public boolean add(Student st) {
// add a student to the heap
//[Enter your code here]
//sort
if (size == maxSize) {
System.out.println("Min Heap is full!");
//System.out.println("Terminated!");
return false;
}
minHeap[size++] = st;
int current = size-1;
while (current != 0 && minHeap[current].score <
minHeap[(current-1)/2].score ) {
swap(current, (current-1)/2);
current = (current-1)/2;
}
return true;
}
public boolean remove() {
// remove a student from the heap
//[Enter your code here]
//sort
if (size ==0) {
System.out.println("Min Heap is empty");
//System.out.println("Terminated!");
return false;
}
size = size -1;
//Student popped = minHeap[0];
minHeap[0] = minHeap[size];
//minHeap[size] = null;
int current = 0;
while (isLeaf(current) == false ) {
boolean check = false;
if ( (current*2 +2) >= size) {
//swap(current, current*2 +2);
if (minHeap[current*2 +1].score < minHeap[current].score) {
swap(current*2+1, current);
current = current*2+1;
check = true;
}
}
else {
if (minHeap[current*2+1].score <
minHeap[current*2+2].score) {
if (minHeap[current*2 +1].score <
minHeap[current].score) {
swap(current*2+1, current);
current = current*2+1;
check = true;
}
}
else {
if (minHeap[current*2 +2].score <
minHeap[current].score) {
swap(current*2+2, current);
current = current*2+2;
check = true;
}
}
}
if (check == false) {
break;
}
}
return true;
}
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
int maxSize;
System.out.print("Enter the maximum number of students: ");
maxSize = Integer.parseInt(input.nextLine());
MinHeap heap = new MinHeap(maxSize);
while(true) {
System.out.println();
System.out.print("Enter your query: ");
String[] inp = input.nextLine().trim().split(" ");
if(inp[0].toLowerCase().equals("remove")) {
if(!heap.remove()) {
System.out.println("Terminated!");
break;
}
}
else if(inp[0].toLowerCase().equals("add")) {
String name = "";
int len = inp.length;
double score = Double.parseDouble(inp[len-1]);
for(int i=1;i<len-1;i++) {
String word = inp[i];
if(!word.equals("")) {
name = name +
Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase() + " ";
}
}
name = name.trim();
Student student = new Student(name, score);
if(!heap.add(student)) {
System.out.println("Terminated!");
break;
}
}
else if(inp[0].toLowerCase().equals("show")) {
Student topStudent = heap.getLowestScore();
if(topStudent == null) {
System.out.println("Min Heap is empty");
System.out.println("Terminated!");
break;
}
topStudent.display();
}
else if(inp[0].toLowerCase().equals("exit")) {
// terminate the program
System.out.println("Terminated!");
break; //[Enter your code here]
}
else {
// terminate the program
System.out.println("Query is not recognized -----
Terminated!");
break; //[Enter your code here]
}
}
input.close();
}
}
