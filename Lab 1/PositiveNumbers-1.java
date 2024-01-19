

//package lab02;
import java.util.Scanner;
public class PositiveNumbers
{
public static void main(String args[])
{
int countp=0, countn=0, countz=0, i, total_positives=0;
int arr[] = new int[10];
Scanner scan = new Scanner(System.in);
System.out.print("Enter number of integers: ");
int j = scan.nextInt() ;
System.out.print("Enter 5 Numbers : ");
for(i=0; i<j; i++)
{
arr[i] = scan.nextInt();
}
for(i=0; i<10; i++)
{
if(arr[i] < 0)
{
countn++;
}
else if(arr[i] == 0)
{
countz++;
}
else
{
countp++;
total_positives += arr[i];
}
}
double average = (double) total_positives / countp;
//display results
System.out.println(
"The number of positives is " + countp +
"\nThe total of positives is " + total_positives +
"\nThe average of positives is " + average);
}
}
