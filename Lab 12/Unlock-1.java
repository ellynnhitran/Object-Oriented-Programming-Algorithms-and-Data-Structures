
package lab13;
import java.util.*;
class Code{
// This is the architecture of a code
int a,b,c,d; // 4 digits
int value; // value of a code: 0089 => 89
String text; // text_value of a code: 0089 => "0089"
public Code(int a, int b, int c, int d) {
//[Enter your code here]
this.a = a ;
this.b = b;
this.c = c;
this.d = d;
//this.text = Integer.toString(a) + Integer.toString(b) +
Integer.toString(c) + Integer.toString(d) ;
this.text = ""+a+b+c+d;
}
ArrayList <Pair> adj = new ArrayList <Pair>(); // adjacency list (for dfs)
int computeValue() {
// return the value of a code: 0089 => 89 (integer); 0129 => 129
(integer)
return (a*1000 + b*100 + c*10 +d); //[Enter your code here]
}
@Override
public String toString() {
// what will display when print out an instance of class Code
return text; //[Enter your code here]
}
}
class Pair{
// A pair of code c and the number of steps that is needed to get code c
(from a start code)
// DO NOT TOUCH
Code c;
int w;
public Pair(Code c, int w) {
this.c = c;
this.w = w;
}
}
class Edge implements Comparable<Edge>{
// Edge for Kruskal algorithm
Code c1,c2;
int w;
int distance(Code c1, Code c2) {
// minimum number of rolls to turn from c1 to c2
//[Enter your code here]
int distance = 0;
//int c1Value = c1.computeValue();
//int c2Value = c2.computeValue();
//while (c1Value > 0 || c2Value >0) {
// int c1Digit = c1Value % 10;
//int c2Digit = c2Value % 10;
distance += Math.min(Math.abs(c1.a - c2.a), 10 - Math.abs(c1.a-
c2.a));
//System.out.println(distance);
distance += Math.min(Math.abs(c1.b - c2.b), 10 - Math.abs(c1.b-
c2.b));
distance += Math.min(Math.abs(c1.c - c2.c), 10 - Math.abs(c1.c-
c2.c));
distance += Math.min(Math.abs(c1.d - c2.d), 10 - Math.abs(c1.d-
c2.d));
//c1Value /= 10;
//c2Value /= 10;
return distance;
}
public Edge(Code c1, Code c2) {
this.c1 = c1;
this.c2 = c2;
this.w = distance(c1, c2);
}
@Override
public int compareTo(Edge e) {
// DO NOT TOUCH
return Integer.valueOf(this.w).compareTo(Integer.valueOf(e.w));
}
}
class DSU{
// DO NOT TOUCH
int[] size;
int[] par;
public DSU(int n) {
size = new int[n];
par = new int[n];
for(int i=0;i<n;i++) {
size[i] = 1;
par[i] = i;
}
}
public int find(int v) {
while(v != par[v]) {
v = par[v];
}
return v;
}
public boolean isSame(int a, int b) {
return find(a) == find(b);
}
public void swap(int a, int b) {
a = a+b;
b = a-b;
a = a-b;
}
public void join(int a, int b) {
int root_a = find(a) , root_b = find(b);
if(size[root_a] < size[root_b]) {
swap(root_a, root_b);
}
size[root_a] += size[root_b];
par[root_b] = root_a;
}
}
public class Unlock {
// main class
ArrayList <Edge> edges = new ArrayList <Edge>(); // list of all edges
TreeSet<String> visited; // to check if a code is visited or not while dfs
int cost; // cost of unlock process
Stack <String> process = new Stack<String>(); // save the unlock process
void buildGraph(Code[] codes, int n) {
// build a graph: each node is a code, 2 nodes will be connected by an
edge (weight of this edge is the minimum number of rolls)
for(int i=0; i<n; i++) {
for(int j=i+1; j<n+1; j++) {
Edge e = new Edge(codes[i], codes[j]) ; //[Enter your
code here];
edges.add(e);
}
}
}
void findMST(){
// Sort edges
//[Enter your code here]
Collections.sort(edges); //sort arraylist
DSU dsu = new DSU(10000);
for(Edge e: edges) {
// DO NOT TOUCH
int v1 = e.c1.computeValue();
int v2 = e.c2.computeValue();
if(!dsu.isSame(v1, v2)) {
e.c1.adj.add(new Pair(e.c2, e.w)); //enter code
e.c2.adj.add(new Pair(e.c1, e.w));
dsu.join(v1, v2);
}
}
}
void dfs(Pair s) {
// this code is to traverse the whole MST to print out unlock process
// if you want to have more challenges, you can code design this method
yourself (feel free to delete this code), else uncomment the below code
Code c = s.c;
visited.add(c.text);
for(Pair v:c.adj) {
if(!visited.contains(v.c.text)) {
int w = v.w;
this.cost += w;
process.push("t" + " " + c + " " + v.c + " " + w);
dfs(v);
process.push("j" + " " + v.c + " " + c + " " + c);
}
}
}
void showProcess(Code root) {
// this code is to print out the unlock process
// if you want to have more challenges, you can code design this method
yourself (feel free to delete this code), else uncomment the below code
visited = new TreeSet<String>();
Pair p = new Pair(root, 0);
dfs(p);
while(this.process.peek().charAt(0) == 'j') {
this.process.pop();
}
for(String sent:this.process) {
String[] words = sent.split(" ");
if(words[0].equals("t")) {
System.out.printf("Turn %s into %s, rolls: %s\n", words[1],
words[2], words[3]);
}
else {
System.out.printf("Jump %s into %s (%s has been unlocked
before)\n", words[1], words[2], words[3]);
}
}
}
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
int n;
System.out.print("Enter the number of code: ");
n = Integer.parseInt(input.nextLine());
Code[] codes = new Code[n+1];
// Create root code 0000
Code root = new Code(0,0,0,0);
codes[0] = root;
for(int i=1; i<=n; i++) {
System.out.printf("Enter code %d: ", i);
String textCode = input.nextLine();
String[] text = textCode.split("");
int a = Integer.parseInt(text[0]);
int b = Integer.parseInt(text[1]);
int c = Integer.parseInt(text[2]);
int d = Integer.parseInt(text[3]);
Code code = new Code(a,b,c,d);
codes[i] = code;
}
Unlock u = new Unlock();
u.buildGraph(codes, n);
u.findMST();
System.out.println();
System.out.println("Unlock process:");
u.showProcess(root);
System.out.println();
System.out.printf("Total cost to unlock all codes is: %d\n", u.cost);
input.close();
}
}
