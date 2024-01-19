
package lab09;
import java.util.*;
/**
* Class Node represent a class in OOP
*
* @param depth of the current node
* @param className represent a Class name
* @param parent point to its parent (you can find this after the tree
* map is built)
* @param adjacentNodes List of its adjacent nodes (parent + children)
*/
class Node {
int depth;
String className;
Node parent;
LinkedList<Node> adjacentNodes;
Node(String name) {
className = name;
depth = -1;
parent = null;
adjacentNodes = new LinkedList<Node>();
}
}
/**
* the main Class MetaOOP
*
* @param edgeList to store all the edges enter from input
* @param label a mapping from one class (name) to its parent or children
* @param rootClass the name of the root class
* @param ROOT the rood Node class
*/
public class MetaOOP {
ArrayList<String> edgeList;
TreeMap<String, Node> label;
String rootClass;
Node ROOT;
/** Constructor */
public MetaOOP() {
edgeList = new ArrayList<String>();
label = new TreeMap<String, Node>();
}
/** Read the Root class name and each edge/inheritant class pair */
/* You should not make any change - read and understand the code */
void readRootAndEdgeList(Scanner sc) {
System.out.print("Enter the name of the rootClass class name: ");
rootClass = sc.nextLine();
System.out.println("Enter the class pairs relationship (one pair at a
time): ");
while (true) {
String[] edge = sc.nextLine().trim().split(" ");
if (edge[0].equals("END"))
break;
edgeList.add(edge[0]);
edgeList.add(edge[1]);
}
}
/** Create a Map of class name to a class Node */
/* You should not make any change - read and understand the code */
void labelNodes() {
label.put(rootClass, new Node(rootClass));
for (int i = 0; i < edgeList.size(); i++) {
if (!label.containsKey(edgeList.get(i))) {
label.put(edgeList.get(i), new Node(edgeList.get(i)));
}
}
}
/** Build a graph from the label and initialize the ROOT node */
void buildGraph() {
for (int i = 0; i < edgeList.size(); i+=2) {
Node x = label.get(edgeList.get(i));
Node y = label.get(edgeList.get(i+1));
x.adjacentNodes.add(y);
y.adjacentNodes.add(x);
}
}
/**
* After a graph is built, we will use BFS/DFS to find the depth and parent
of
* each node by travel through the graph from the root
*/
void assignDepthAndParent() {
Queue<Node> queue = new LinkedList<>();
ROOT = label.get(rootClass);
ROOT.depth = 0;
queue.add(ROOT);
while(!queue.isEmpty()){
Node current = queue.poll();
for (Node n: current.adjacentNodes) {
if (n.depth == -1) {
n.parent = current;
n.depth = current.depth + 1;
queue.add(n);
}
}
}
// [ENTER YOU CODE HERE]
}
/** Find the closest common accestor/class of two nodes */
String find_lca(Node u, Node v) {
while (u.depth < v.depth) {
v = v.parent;
}
while (u.depth > v.depth) {
u = u.parent;
}
while (u != v) {
u = u.parent;
v = v.parent;
}
return u.className;
}
/**
* Read two class name from user and print out their closest common
* accestor/class
*/
public void findCommonClass(Scanner sc) {
String lca;
System.out.println("Query the nearest common parent class of the pair:
");
/* Keep the println() out of the loop to pass CMS large test case */
while (true) {
String[] edge = sc.nextLine().trim().split(" ");
// [ENTER YOU CODE HERE]
if (edge[0].equals("END"))
break;
lca = find_lca(label.get(edge[0]), label.get(edge[1]));
System.out.printf("The answer of %s and %s is %s\n", edge[0],
edge[1], lca);
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
MetaOOP testObj = new MetaOOP();
testObj.readRootAndEdgeList(sc);
testObj.labelNodes();
testObj.buildGraph();
testObj.assignDepthAndParent();
testObj.findCommonClass(sc);
sc.close();
}
}
