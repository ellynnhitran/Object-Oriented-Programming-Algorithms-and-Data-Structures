
package lab07;
import java.util.*;
public class TreeRecovery {
int n;
int[] pre, in;
int[] left, right;
void readOrders(Scanner sc) {
System.out.printf("Enter the number of node:\n");
n = sc.nextInt();
pre = new int[n];
System.out.print("Enter the preorder traversal:\n");
for (int i = 0; i < n; i++)
pre[i] = sc.nextInt();
in = new int[n];
System.out.print("Enter the order traversal:\n");
for (int i = 0; i<n; i++)
in[i] = sc.nextInt();
}
int getRoot(int lowIn, int highIn, int lowPre, int highPre) {
if (lowIn > highIn)
return -1;
int root = pre[lowPre];
int i = lowIn;
while (in[i] != root)
i++;
left[root] = getRoot(lowIn, i-1, lowPre+1, lowPre + i - lowIn );
right[root] = getRoot(i+1, highIn, lowPre+i-lowIn+1, highPre);
return root;
}
void printArr(int[] arr, int n) {
for (int i = 0; i < n; i++)
System.out.print(arr[i] + " ");
System.out.println();
}
void findTree() {
left = new int[n];
right = new int[n];
getRoot(0, n-1, 0, n-1);
System.out.print("Left child: ");
this.printArr(left, n);
System.out.print("Right child: ");
this.printArr(right, n);
for (int i = 0; i < n; i++) {
//left[i] == getRoot(i, i, i, i)
if (left[i] == -1 && right[i] == -1)
System.out.println("Node " + i + " has no left child
and no right child.");
else if (left[i] == -1)
System.out.println("Node " + i + " has no left child
and right child " + right[i] + ".");
else if (right[i] == -1) {
System.out.println("Node " + i + " has left child " +
left[i] + " and no right child.");
}
else {
System.out.println("Node " + i + " has left child " +
left[i] + " right child "+ right[i] + ".");
}
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
TreeRecovery tr = new TreeRecovery();
System.out.println("INPUT: ");
tr.readOrders(sc);
System.out.println("OUTPUT: ");
tr.findTree();
sc.close();
}
}
