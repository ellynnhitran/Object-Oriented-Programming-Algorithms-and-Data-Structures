

package lab05;
import java.util.*;
public class PowerOperator {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
String inp;
Stack<Integer> operands=new Stack<Integer>();
Stack<Character> operators=new Stack<Character>();
do {
System.out.println("Enter an expression (or Q/q to quit): ");
inp = input.nextLine();
for (int i = 0; i < inp.length(); i++) {
char c = inp.charAt(i);
if (c != 'H' ) {
operands.add((int)c);
}
else
operators.add(c);
}
setExpression(inp);
evaluate();
} while (!inp.equals("q") && !inp.equals("Q"));
}
static void setExpression(String s) {
}
static void evaluate() {
result =
}
}
