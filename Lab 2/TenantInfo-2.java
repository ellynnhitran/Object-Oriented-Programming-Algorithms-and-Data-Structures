

//package lab03;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
public class TenantInfo {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
String[] inp = new String[3];
System.out.print("Enter Apartment Information (block, floor, unit): ");
inp = input.nextLine().split(" ");
int bl = Integer.parseInt(inp[0]);
int fl = Integer.parseInt(inp[1]);
int un = Integer.parseInt(inp[2]);
Apartment.displayApartment(bl, fl, un);
System.out.print("\nEnter tenant name: ");
String name = input.nextLine();
System.out.println("Enter tenant rent: ");
double rent = Double.parseDouble(input.nextLine());
System.out.println("Enter new Apartment name: ");
String apartmentName = input.next();
TenantInformation.displayTenant(bl, un, fl, name, rent);
input.close();
}
}
class Apartment{
static String apartmentName = "Sunshine Apartment";
static void displayApartment(int bl, int fl, int un) {
System.out.println("\nWelcome to Sunside Apartment"
+ "\nThis apartment is at block#" + bl + ", floor#" + fl +
", unit#" +un);
}
}
class TenantInformation extends Apartment {
static Locale locale = new Locale("en", "US");
static NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
//static DecimalFormat moneyFormat = new DecimalFormat("$0.00");
static String tenantName;
static double rent;
TenantInformation( int bl, int fl, int un, String tenantName, double
rent){
super();
this.tenantName = tenantName;
this.rent = rent;
}
static void displayTenant(int bl, int un, int fl, String name, double
rent2) {
NumberFormat format = NumberFormat.getCurrencyInstance();
System.out.println("\nWelcome to VinUni Apartment"+
"\nThis apartment is at block#" + bl + ", floor#" + fl + ", unit#" + un
+
"\nTenant name: "+ name + ", Rent: " + fmt.format(rent2) );
}
}
