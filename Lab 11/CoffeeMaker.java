
package lab12;
import java.util.*;
public class CoffeeMaker {
class Pair{
int a,b;
public Pair (int a, int b) {
this.a = a;
this.b = b;
}
}
int make(int a, int b, int v, int M, int N) {
Queue <Pair> q = new LinkedList <Pair>();
int[][] dist = new int[1000][1000];
Pair p = new Pair(a,b); // first state
q.add(p);
dist[a][b] = 1;
boolean[][] visited = new boolean[1000][1000];
for (int i = 0; i < 1000; i++) {
for (int j = 0; j < 1000; j++) {
visited[i][j] = false;
}
}
visited[a][b] = true;
//[Enter your code here]
while (!q.isEmpty()) {
Pair top =q.poll();
a = top.a;
b = top.b;
// System.out.println("debug " + a + " " + b );
if (a == v || b == v) {
System.out.println("Here is your coffee. The total price is
$" + (dist[a][b] - 1) +".") ;
break;
}
if (visited[0][b] == false) {
visited[0][b] = true;
Pair p1 = new Pair(0, b);
q.add(p1);
dist[0][b] = dist[a][b] +1;
}
if (visited[a][0] == false) {
visited[a][0] = true;
Pair p1 = new Pair(a, 0);
q.add(p1);
dist[a][0] = dist[a][b] +1;
}
if (visited[M][b] == false) {
visited[M][b] = true;
Pair p1 = new Pair(M, b);
q.add(p1);
dist[M][b] = dist[a][b] +1;
}
if (visited[a][N] == false) {
visited[a][N] = true;
Pair p1 = new Pair(a, N);
q.add(p1);
dist[a][N] = dist[a][b] +1;
}
if ( a + b <= N) {
if (visited[0][a+b] == false) {
visited[0][a+b] = true;
Pair p1 = new Pair(0, a+b);
q.add(p1);
dist[0][a+b] = dist[a][b] +1;
}
}
else {
if (visited[a - (N-b)][N] == false) {
visited[a - (N-b)][N] = true;
Pair p1 = new Pair(a - (N-b), N);
q.add(p1);
dist[a - (N-b)][N] = dist[a][b] +1;
}
}
if ( a + b <= M) {
if (visited[a+b][0] == false) {
visited[a+b][0] = true;
Pair p1 = new Pair(a+b, 0);
q.add(p1);
dist[a+b][0] = dist[a][b] +1;
}
}
else {
if (visited[M][b- (M-a)] == false) {
visited[M][b- (M-a)] = true;
Pair p1 = new Pair(M, b- (M-a));
q.add(p1);
dist[M][b- (M-a)] = dist[a][b] +1;
}
}
}
return -1; // cannot make that cup of coffee
}
public static void main(String[] args) {
// TODO Auto-generated method stub
Scanner input = new Scanner(System.in);
while(true) {
System.out.print("Enter the initial volume and the capacity of
cup 1 and cup 2: ");
String[] inp = input.nextLine().split(" ");
int a = Integer.parseInt(inp[0]);
int M = Integer.parseInt(inp[1]);
int b = Integer.parseInt(inp[2]);
int N = Integer.parseInt(inp[3]);
System.out.print("Enter new volume that you want: ");
int v = Integer.parseInt(input.nextLine());
CoffeeMaker cm = new CoffeeMaker();
int minSteps = cm.make(a, b, v, M, N);
if(minSteps == -1) {
System.out.println("Sorry, we cannot make your coffee.");
}
else {
System.out.printf("Here is your coffee. The total price is
$%d.\n", minSteps);
}
System.out.println("Do you want to make more coffee? (yes/no)");
String ans = input.nextLine();
if(ans.toLowerCase().equals("no")) {
System.out.println("Thank you for using our service!");
break;
}
}
input.close();
}
}
