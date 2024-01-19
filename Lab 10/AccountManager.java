
package lab11;
import java.util.*;
/**
* Class User
*
* @param username account username
* @param passwordHash hash of the account password plus salt
* @param salt an integer added to password before hashing to improve
* security (in practice, it can be a random number)
*/
class User {
String username;
int passwordHash;
int salt;
User(String username, String pwd) {
this.username = username;
this.salt = 1;
//check lai pwd
this.passwordHash = hashPassword(pwd, salt);
//AccountManager account = new AccountManager();
//username = account.get(username);
}
/** Return the value of the hashCode() method on the specified object */
private int hashPassword(String password, int salt) {
String newPass = password + Integer.toString(salt);
return newPass.hashCode();
}
/** Check if oldPwd is correct, in so, increase salt and set newPwd */
void changePassword(String oldPwd, String newPwd) {
//how to check old password is correct: old password matches the user
if (hashPassword(oldPwd, this.salt) == this.passwordHash) {
this.salt += 1;
this.passwordHash = hashPassword(newPwd, this.salt);
}
}
/** Return true if password is correct */
boolean login(String password) {
//password is correc when the hashcode of password matches the hashcode of
user
if (hashPassword(password, this.salt) == this.passwordHash) {
return true;
}
return false;
}
@Override
public String toString() {
return String.format("%s %d %d", username, salt, passwordHash);
}
}
/**
* Class AccountManager to manage multiple users sessions
*
* @param database A database to save user account
* @param sessionManager A database to save which user is logged in by its
* username
*/
public class AccountManager {
private Map<String, User> database;
private Set<String> sessionManager;
/*
* The general approach is to retrieve a User object from the database, then
* perform the appropriate command on the User object. The account database
* should be HashMap type in order to ensure matching printing order with the
* answer output
*/
AccountManager() {
database = new HashMap<String, User>();
sessionManager = new HashSet<String>();
}
/** If there is no username, add this username to database */
void signup(String username, String password) {
if (database.containsKey(username) == false) {
//how to add username to database
User user = new User(username, password);
database.put(username, user);
}
}
/**
* return if there is no such username, or already login; otherwise, check if
* password is correct, then add this username to sessionManager
*/
void login(String username, String password) {
//if username in the database, allow to login and check if the pass is
correct
//key = username
if (database.containsKey(username) == false ||
sessionManager.contains(username) == true) {
return;
}
else {
User user = database.get(username);
if (user.login(password) == true) {
sessionManager.add(user.username);
}
}
}
/** Do the opposite of login to remove the username from sessionManager */
void logout(String username) {
if (sessionManager.contains(username) == false)
return;
else {
sessionManager.remove(username);
}
}
/**
* if the user is currently logged in, and the oldPass password matches with
the
* username, and the two passwords are case-insensitively different, then
* perform password change using User.changePassword() method
*/
void changePassword(String username, String oldPwd, String newPwd) {
User user = database.get(username);
if ( sessionManager.contains(username) == true && user.login(oldPwd) &&
!oldPwd.equalsIgnoreCase(newPwd)) {
user.changePassword(oldPwd, newPwd);
}
}
/**
* Print out the user account database in the format: username salt
* passwordHash, then terminate the program
*/
void generateBackup() {
for (String username : database.keySet()) {
System.out.println(database.get(username));
}
}
/** the main method is provided, you should NOT change it */
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
AccountManager manager = new AccountManager();
System.out.println("Enter some commands (1 line at a time):");
while (sc.hasNext()) {
String cmd = sc.next();
if (cmd.equalsIgnoreCase("backup")) {
manager.generateBackup();
break;
} else {
String username = sc.next();
if (cmd.equalsIgnoreCase("logout"))
manager.logout(username);
else {
String password = sc.next();
if (cmd.equalsIgnoreCase("signup")) {
manager.signup(username, password);
} else if (cmd.equalsIgnoreCase("login")) {
manager.login(username, password);
} else if (cmd.equalsIgnoreCase("cpwd")) {
String newPwd = sc.next();
manager.changePassword(username, password,
newPwd);
}
}
}
}
sc.close();
}
}
