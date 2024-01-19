

package lab08;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Comparator;
class Member{
String name;
int attractiveness_score;
Member(String name, int attractiveness_score){
this.name = name;
this.attractiveness_score = attractiveness_score;
}
}
class maxScoreComparator implements Comparator<Member>{
@Override
public int compare(Member m1, Member m2){
return m1.attractiveness_score < m2.attractiveness_score ? 1 : -1; //
max to min
}
}
class minScoreComparator implements Comparator<Member>{
@Override
public int compare(Member m1, Member m2){
return m1.attractiveness_score > m2.attractiveness_score ? 1 : -1; //
min to max
}
}
public class IdolClubManager {
public static void main(String[] args) {
// TODO Auto-generated method stub
String[] myList;
String name = "";
Member member, item;
PriorityQueue<Member> minHeap, maxHeap;
Scanner sc = new Scanner(System.in);
System.out.print("What is your club's name? ");
String club = sc.nextLine();
minHeap = new PriorityQueue<Member>(new minScoreComparator());
maxHeap = new PriorityQueue<Member>(new maxScoreComparator());
while(true){
System.out.print("What is your request? ");
myList = sc.nextLine().trim().split(" ");
if (myList.length > 1 && myList[0].equalsIgnoreCase("recruit")){
name = String.join(" ", Arrays.copyOfRange(myList, 1,
myList.length - 1));
member = new Member(name,
Integer.parseInt(myList[myList.length - 1]));
minHeap.add(member);
maxHeap.add(member);
} else if (myList.length == 1) {
if (myList[0].equalsIgnoreCase("max")){
System.out.printf("%s - %d\n", maxHeap.peek().name,
maxHeap.peek().attractiveness_score);
} else if (myList[0].equalsIgnoreCase("min")){
System.out.printf("%s - %d\n", minHeap.peek().name,
minHeap.peek().attractiveness_score);
} else if (myList[0].equalsIgnoreCase("reform")){
System.out.printf("Welcome to %s!\n", club);
System.out.printf("Here is our lineup of %d members!\
n", minHeap.size());
while (! minHeap.isEmpty()) {
item = minHeap.poll();
System.out.printf("%s - %d\n", item.name,
item.attractiveness_score);
}
System.out.println();
break;
}
}
}
}
}
