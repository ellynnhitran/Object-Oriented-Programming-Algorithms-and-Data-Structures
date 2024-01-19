
//package lab05;
import java.util.*;
public class BracketSequence {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
System.out.print("Enter a bracket sequence: ");
String arr = input.nextLine();
System.out.println();
if (BracketSequence( arr))
System.out.println(arr+" is a VALID bracket sequence.");
else
System.out.println(arr+" is an INVALID bracket sequence!!!");
}
static boolean BracketSequence(String arr) {
Deque<Character> stack = new ArrayDeque<Character>();
for (int i = 0; i < arr.length(); i++) {
char x = arr.charAt(i);
if (x == '(' || x == '[' || x == '{') {
stack.push(x);
continue;
}
if (stack.isEmpty())
return false;
char check;
switch (x) {
case ')':
check = stack.pop();
if (check == '{' || check == '[')
return false;
break;
case '}':
check = stack.pop();
if (check == '(' || check == '[')
return false;
break;
case ']':
check = stack.pop();
if (check == '{' || check == '(')
return false;
break;
}
}
return (stack.isEmpty());
}
void isSupported() {
}
void isValid() {
}
}
