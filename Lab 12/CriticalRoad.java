
package lab13;
//package lab13;
import java.util.*;
class Graph {
int Nodes, Edges;
// adjacencyList[u] contains all the edges to adjacent node v.
Set<Integer>[] adjacencyList;
// visited[u] tracks whether node u is visited in a bfs operation
boolean[] visited;
// A 2D array of size Mx2 containing the edge list,
// the ith edge with 2 nodes edgeList[i][0] and edgeList[i][1]
int[][] edgeList;
Graph(int N, int M) {
Nodes = N;
adjacencyList = new TreeSet[Nodes + 1];
edgeList = new int[M][2];
//visited = new boolean[Nodes + 1];
for (int i = 1; i <= Nodes; i++) {
adjacencyList[i] = new TreeSet<Integer>();
}
Edges = 0;
}
// If you want to reuse this method after adding all edges to the graph at
the first step,
// use the boolean addNew parameter
void addEdge(int u, int v, boolean addNew) {
if (addNew == true) {
adjacencyList[u].add(v);
adjacencyList[v].add(u);
edgeList[Edges][0] = u;
edgeList[Edges][1] = v;
Edges += 1;
}
else {
adjacencyList[u].add(v);
adjacencyList[v].add(u);
}
//use boolean addNew parameter ntn
}
// The idea is to remove each edge one by one,
// then check whether the graph is still connected
void printAllCriticalRoads() {
int count = 0;
ArrayList<Integer> arr = new ArrayList<Integer>();
for (int i = 0; i < edgeList.length ; i++) {
deleteEdge(edgeList[i][0], edgeList[i][1]);
visited = new boolean[Nodes +1];
bfs(edgeList[i][0]);
for (int j = 1; j < visited.length; j++) {
if (visited[j] == false) {
count += 1;
arr.add(i);
// System.out.println("not connected");
break;
}
}
addEdge(edgeList[i][0], edgeList[i][1], false);
}
System.out.println(count);
for (int i = 0; i < count; i++) {
if (edgeList[arr.get(i)][0] < edgeList[arr.get(i)][1]) {
System.out.println(edgeList[arr.get(i)][0] +" " +
edgeList[arr.get(i)][1]);
}
else {
System.out.println(edgeList[arr.get(i)][1] +" " +
edgeList[arr.get(i)][0]);
}
}
}
private void deleteEdge(int u, int v) {
adjacencyList[u].remove(v);
adjacencyList[v].remove(u);
}
private void bfs(int u) {
//mark current node as visited
visited[u] = true;
LinkedList<Integer> queue = new LinkedList();
queue.add(u);
while (queue.size() != 0) {
u = queue.poll();
Iterator<Integer> i = adjacencyList[u].iterator() ;
while(i.hasNext()) {
int next = i.next();
if (!visited[next]) {
visited[next] = true;
queue.add(next);
}
}
}
}
}
public class CriticalRoad {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
int N = sc.nextInt();
int M = sc.nextInt();
Graph g = new Graph(N, M);
for (int i = 0; i < M; i++) {
int u = sc.nextInt();
int v = sc.nextInt();
g.addEdge(u, v, true);
}
sc.close();
g.printAllCriticalRoads();
}
}
