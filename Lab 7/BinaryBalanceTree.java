
import java.util.*;
import java.lang.Math;
public class BinaryBalanceTree {
class Node {
/**
* value stores the value of the node. left and right store the left
child and
* right child on the tree respectively. If there is no left or right
child,
* left or right will be null. minHeight and maxHeight store the
shortest and
* longest distance from a node to one of its subtree's leaves. These
are used
* to add and pop the correct node.
*/
int value;
Node left, right;
int minHeight, maxHeight, depth;
Node(int value, Node parent) {
this.value = value;
minHeight = maxHeight = 0;
if (parent == null)
depth = 0;
else
depth = parent.depth + 1;
left = right = null;
}
void updateHeight() {
if (left == null) {
minHeight = 0;
if (right == null)
maxHeight = 0;
else {
right.updateHeight();
maxHeight = right.maxHeight + 1;
}
} else if (right == null) {// left!=null, right==null
minHeight = 0;
left.updateHeight();
maxHeight = left.maxHeight + 1;
} else {
left.updateHeight();
right.updateHeight();
minHeight = Math.min(left.minHeight, right.minHeight) + 1;
maxHeight = Math.max(left.maxHeight, right.maxHeight) + 1;
}
}
void addLeft(int value) {
/**
* Add a node such that its height is minimized and it is left
most possible on
* the binary tree. Use minHeight to find the correct position.
The new node
* will be added as child of a node without left child or without
right child.
* (minHeight==0). Prioritize the left subtree over the right
node whenever
* possible. Update the height and depths after the node is added
*/
// [ENTER YOUR CODE HERE]
// 0
// 1 2
// 3 4 5
// 0
// 1 2
// 3 4 5
if (this.left == null) {
this.left = new Node(value, this);
} else if (this.right == null) {
this.right = new Node(value, this);
} else {
Node currentNode;
if (this.left.minHeight > this.right.minHeight) {
currentNode = this.right;
} else {
currentNode = this.left;
}
// while loop in recursive way to reach the deepest node
while (currentNode.minHeight > 0) {
if (currentNode.left.minHeight >
currentNode.right.minHeight) {
currentNode = currentNode.right;
} else {
currentNode = currentNode.left;
}
}
if (currentNode.left != null) {
currentNode.right = new Node(value, currentNode);
} else {
currentNode.left = new Node(value, currentNode);
}
}
this.updateHeight();
}
void addRight(int value) {
/**
* The same as addLeft, only mirrored
*/
// [ENTER YOUR CODE HERE]
if (this.right == null) {
this.right = new Node(value, this);
} else if (this.left == null) {
this.left = new Node(value, this);
} else {
Node currentNode;
if (this.left.minHeight < this.right.minHeight) {
currentNode = this.left;
} else {
currentNode = this.right;
}
// while loop in recursive way to reach the deepest node
while (currentNode.minHeight > 0) {
if (currentNode.left.minHeight <
currentNode.right.minHeight) {
currentNode = currentNode.left;
} else {
currentNode = currentNode.right;
}
}
if (currentNode.right != null) {
currentNode.left = new Node(value, currentNode);
} else {
currentNode.right = new Node(value, currentNode);
}
}
this.updateHeight();
}
int popLeft() {
/**
* Pop the node with highest height, if there are multiple such
node, pop the
* left most Use max depth to find the lowest node. Prioritize
the left subtree
* over the right node whenever possible. Removing the node using
the parent
* because the object can't delete itself (easily). Because we
always go to the
* lowest node, the parent will have maxHeight==1. Update the
height after the
* node is removed
*
*/
int ans = -1;
// [ENTER YOUR CODE HERE]
// Using recursion to Iterate in the tree to the deepest node
if (this.left == null) {
ans = this.right.value;
this.right = null;
} else if (this.right == null) {
ans = this.left.value;
this.left = null;
} else if (this.left != null && this.right != null &&
this.left.maxHeight == 0 // check if the tree has
// height of 1 or not
&& this.right.maxHeight == 0) {
ans = this.left.value;
this.left = null;
} else {
Node currentNode;
if (this.left.maxHeight < this.right.maxHeight) {
currentNode = this.right;
} else {
currentNode = this.left;
}
// while loop in recursive way to reach the deepest node
while (currentNode.maxHeight > 1) {
if (currentNode.left.maxHeight <
currentNode.right.maxHeight) {
currentNode = currentNode.right;
} else {
currentNode = currentNode.left;
}
}
if (currentNode.left == null) {
ans = currentNode.right.value;
currentNode.right = null;
} else {
ans = currentNode.left.value;
currentNode.left = null;
}
}
this.updateHeight();
return ans;
}
int popRight() {
/**
* The same as popLeft, only mirrored
*/
int ans = -1;
// [ENTER YOUR CODE HERE]
if (this.right == null) {
ans = this.left.value;
this.left = null;
} else if (this.left == null && this.right != null) {
ans = this.right.value;
this.right = null;
} else if (this.left != null && this.right != null &&
this.left.maxHeight == 0 // check if the tree has
// height of 1 or not
&& this.right.maxHeight == 0) {
ans = this.right.value;
this.right = null;
} else {
Node currentNode;
if (this.right.maxHeight < this.left.maxHeight) {
currentNode = this.left;
} else {
currentNode = this.right;
}
// while loop in recursive way to reach the deepest node
while (currentNode.maxHeight > 1) {
if (currentNode.left.maxHeight >
currentNode.right.maxHeight) {
currentNode = currentNode.left;
} else {
currentNode = currentNode.right;
}
}
if (currentNode.right == null) {
ans = currentNode.left.value;
currentNode.left = null;
} else {
ans = currentNode.right.value;
currentNode.right = null;
}
}
this.updateHeight();
return ans;
}
void printTree(int id, int[][] tree) {
tree[depth][id] = value;
if (left != null)
left.printTree(id * 2, tree);
if (right != null)
right.printTree(id * 2 + 1, tree);
}
}
Node root;
BinaryBalanceTree() {
root = null;
}
void addLeft(int value) {
if (root == null)
root = new Node(value, null);
else
root.addLeft(value);
}
void addRight(int value) {
if (root == null)
root = new Node(value, null);
else
root.addRight(value);
}
int popLeft() {
if (root == null) {
System.err.printf("Pop when tree is empty!");
System.exit(-1);
return 0;
} else {
int res;
if (root.maxHeight == 0) {
res = root.value;
root = null;
} else
res = root.popLeft();
return res;
}
}
int popRight() {
if (root == null) {
System.err.printf("Pop when tree is empty!");
System.exit(-1);
return 0;
} else {
int res;
if (root.maxHeight == 0) {
res = root.value;
root = null;
} else
res = root.popRight();
return res;
}
}
void printTree() {
if (root == null) {
System.out.printf("The tree is empty!\n");
return;
}
int[][] tree;
tree = new int[root.maxHeight + 1][];
for (int i = 0; i <= root.maxHeight; i++) {
tree[i] = new int[1 << i];
for (int j = 0; j < tree[i].length; j++)
tree[i][j] = -1;
}
root.printTree(0, tree);
System.out.printf("The current tree is:\n");
for (int i = 0; i <= root.maxHeight; i++) {
for (int j = 0; j < tree[i].length; j++)
if (tree[i][j] != -1)
System.out.printf("%d ", tree[i][j]);
else
System.out.printf("x ");
System.out.printf("\n");
}
}
public static void main(String[] args) {
BinaryBalanceTree bt = new BinaryBalanceTree();
Scanner sc = new Scanner(System.in);
System.out.println("Enter your commands:");
while (true) {
String cmd = sc.nextLine();
String[] argv = cmd.trim().split(" ");
if (argv[0].equalsIgnoreCase("add")) {
if (argv[1].equalsIgnoreCase("left")) {
bt.addLeft(Integer.parseInt(argv[2]));
} else if (argv[1].equalsIgnoreCase("right")) {
bt.addRight(Integer.parseInt(argv[2]));
} else {
System.err.printf("Expected either \"left\"
or \"right\", got \"%s\"", argv[1]);
System.exit(-1);
}
} else if (argv[0].equalsIgnoreCase("pop")) {
int res = 0;
if (argv[1].equalsIgnoreCase("left")) {
res = bt.popLeft();
} else if (argv[1].equalsIgnoreCase("right")) {
res = bt.popRight();
} else {
System.err.printf("Expected either \"left\"
or \"right\", got \"%s\"", argv[1]);
System.exit(-1);
}
System.out.printf("Pop: %d\n", res);
} else if (argv[0].equalsIgnoreCase("print")) {
bt.printTree();
} else if (argv[0].equalsIgnoreCase("exit")) {
break;
} else {
System.err.printf("Unsupported argument \"%s\"", argv[0]);
System.exit(-1);
}
}
sc.close();
}
}
