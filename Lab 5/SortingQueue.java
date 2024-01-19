

package lab06;
import java.util.*;
public class SortingQueue {
public static <T> T get(Queue<T> queue, int index) {
synchronized (queue) {
if (queue == null) {
return null;
}
int size = queue.size();
if (index < 0 || size < index + 1) {
return null;
}
T element = null;
for (int i = 0; i < size; i++) {
if (i == index) {
element = queue.remove();
} else {
queue.add(queue.remove());
}
}
return element;
}
}
static Queue<Integer> numbers;
public SortingQueue() {
//Queue<Integer> numbers = new LinkedList<Integer>();
}
public void add() {
}
public void print() {
System.out.println("The current queue is: " + numbers);
}
public void poll() {
}
public void sort() {
int temp;
for (int i = numbers.size() - 1; i > 0; i -- ) {
for (int j = 0; j < i; j++) {
if (get(numbers, j) > get(numbers, j+1)) {
temp = get(numbers, j);
Integer temp1 = get(numbers, j+1);
temp = temp1;
}
}
}
}
public static void main(String[] args) {
SortingQueue queue = new SortingQueue();
Scanner sc = new Scanner(System.in);
int i = 1;
while(true) {
System.out.print("Enter the #" + i + " command: ");
String[] command = sc.nextLine().split(" ");
if (input.equals("exit")) {
break;
}
else if (input.equals("print")) {
queue.print();
}
else if (input.equals("add")) {
queue.add();
}
else if (input.equals("poll")) {
queue.poll();
}
else if (input.equals("sort")) {
queue.sort();
System.out.println("The current queue is: " + numbers);
}
numbers.add(Integer.parseInt(input));
i++;
}
}
}
