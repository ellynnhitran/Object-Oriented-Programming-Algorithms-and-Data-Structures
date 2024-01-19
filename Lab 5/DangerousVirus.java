
package lab06;
import java.util.*;
class Virus{
public int size;
public String name;
public static void display(int pos) {
int i =0;
System.out.println("Virus "+ this.name + ", sized " +i+1+ "is standing
at position "+ i+1 );
}
}
class Array{
Virus[] virus_arr;
public int count = 0;
public void setArray(Queue<Virus> viruses) {
}
public void sort(int[] v, int l, int r) {
if (l < r) {
// Find the middle point
int m =l+ (r-l)/2;
// Sort first and second halves
sort(v, l, m);
sort(v, m + 1, r);
// Merge the sorted halves
merge(v, l, m, r);
}
}
public void merge(int arr[], int l, int m, int r) {
int n1 = m - l + 1;
int n2 = r - m;
/* Create temp arrays */
int L[] = new int[n1];
int R[] = new int[n2];
/*Copy data to temp arrays*/
for (int i = 0; i < n1; ++i)
L[i] = arr[l + i];
for (int j = 0; j < n2; ++j)
R[j] = arr[m + 1 + j];
/* Merge the temp arrays */
// Initial indexes of first and second subarrays
int i = 0, j = 0;
// Initial index of merged subarry array
int k = l;
while (i < n1 && j < n2) {
if (L[i] <= R[j]) {
arr[k] = L[i];
i++;
}
else {
arr[k] = R[j];
j++;
}
k++;
}
/* Copy remaining elements of L[] if any */
while (i < n1) {
arr[k] = L[i];
i++;
k++;
}
/* Copy remaining elements of R[] if any */
while (j < n2) {
arr[k] = R[j];
j++;
k++;
}
}
public void printArray(Virus v) {
for (int i = 0; i < this.virus_arr.length; i++) {
Virus.display(i);
}
}
public void count() {
}
}
public class DangerousVirus {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
System.out.print("Enter the number of viruses: ");
int n = Integer.parseInt(input.nextLine());
Queue<Virus> virus = new LinkedList<Virus>();
Virus v = new Virus();
for (int i = 0; i <= n; i++) {
//System.out.printf("Enter name and size of virus %d: ", i+1);
System.out.print("Enter name and size of virus " + i+1 + ":");
}
Array arr = new Array();
arr.setArray(virus);
arr.sort(v, 0, n-1);
System.out.println();
System.out.println("Sorted Virus Array: ");
arr.printArray(v);
System.out.println();
arr.count();
input.close();
}
}
