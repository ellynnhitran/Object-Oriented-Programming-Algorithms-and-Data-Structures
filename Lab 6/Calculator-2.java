
package lab07;
import java.util.*;
class Node {
char value;
Node left;
Node right;
// constructor
Node(char value) {
this.value = value;
this.left = null;
this.right = null;
}
}
class BinaryTree {
Node root;
BinaryTree(char root) {
this.root = new Node(root);
}
public void add_left(BinaryTree bt) {
this.root.left = bt.root;
}
public void add_right(BinaryTree bt) {
this.root.right = bt.root;
}
}
public class Calculator {
/* To store the value of each variable:
* var[0] => variable a, var[1] => variable b, etc
*/
public int[] variables = new int[30];
Scanner sc = new Scanner(System.in);
public Calculator() {
for (int i = 0; i < 30; i++) {
variables[i] = -1;
}
}
/* Return operator precedence:
* '/' and '*' is higher than '+' and '-' */
public int getPrecedence(char operator) {
if (operator == '+' || operator == '-') {
return 1;
} else if (operator == '*' || operator == '/') {
return 2;
}
return -1;
}
public boolean isOperator(char c) {
/* return true if c is a supported operator */
if (c == '%' || c == '/' || c== '*' ||c== '+' ||c == '-'){
return true;
}
return false;
}
public int getIndex(char c) {
return (int) c - 97;
}
public String infixToPostfix(String exp ,Scanner input)
{
// initializing empty String for result
String result = new String("");
// initializing empty stack
Stack<Character> stack = new Stack<>();
for (int i = 0; i<exp.length(); ++i)
{
char c = exp.charAt(i);
// If the scanned character is an
// operand, add it to output.
if (Character.isLetterOrDigit(c)) {
if (variables[getIndex(exp.charAt(i))] < 0 ) {
System.out.printf ("Enter the value of %s: ",exp.charAt(i));
variables [getIndex(exp.charAt(i))] =
Integer.parseInt(input.nextLine());
}
result += c;
}
// If the scanned character is an '(',
// push it to the stack.
else if (c == '(')
stack.push(c);
// If the scanned character is an ')',
// pop and output from the stack
// until an '(' is encountered.
else if (c == ')')
{
while (!stack.isEmpty() &&
stack.peek() != '(')
result += stack.pop();
stack.pop();
}
else // an operator is encountered
{
while (!stack.isEmpty() && getPrecedence(c)
<= getPrecedence(stack.peek())){
result += stack.pop();
}
stack.push(c);
}
}
// pop all the operators from the stack
while (!stack.isEmpty()){
if(stack.peek() == '(')
return "Invalid Expression";
result += stack.pop();
}
return result;
}
/* Implement postfix expression to BinaryTree
* See this if you need some help
* (also the Video posted on Canvas)
* https://www.geeksforgeeks.org/expression-tree/
*/
public BinaryTree postfixToTree(String s) {
/* Hint: Use a Stack of BinaryTree type to store the
* left/right trees and combine when you see an operator */
Stack<BinaryTree> myStack = new Stack<BinaryTree>();
for (int i = 0 ; i < s.length(); i++)
if (isOperator(s.charAt(i)))
{
BinaryTree tempTree = new BinaryTree(s.charAt(i));
tempTree.add_right(myStack.pop());
tempTree.add_left(myStack.pop());
myStack.add(tempTree);
}
else {
BinaryTree tempTree = new BinaryTree(s.charAt(i));
myStack.add(tempTree);
}
return myStack.pop();
}
/* Recursively evaluate the expression tree,
* starting from root as an input
*/
public int evaluate(Node root) {
/* return the variable value if this is a leaf */
if (root.left == null || root.right == null) {
return variables[getIndex(root.value)];
}
/* when the root is an operator, recursively get
* the value from the left and the right and
* evaluate the operator at root
*/
switch (root.value){
case '+': return evaluate(root.left) + evaluate(root.right);
case '-': return evaluate(root.left) - evaluate(root.right);
case '*': return evaluate(root.left) * evaluate(root.right);
case '/': return evaluate(root.left) / evaluate(root.right);
default: return -1;
}
}
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
System.out.println("Enter an expression (only variables, operators and
round brackets): ");
String exp = input.nextLine();
System.out.println();
Calculator cal = new Calculator();
String res = cal.infixToPostfix(exp, input);
System.out.println("\nInfix to Postfix: " + exp + " => " + res);
System.out.println();
BinaryTree expTree = cal.postfixToTree(res);
int ans = cal.evaluate(expTree.root);
System.out.println("The result is " + ans);
}
}
