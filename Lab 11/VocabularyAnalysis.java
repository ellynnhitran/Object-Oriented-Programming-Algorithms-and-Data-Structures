
package lab12;
import java.util.*;
public class VocabularyAnalysis {
/*
* Print the last time each word appear in text. Using a tree map would be
too
* slow because it need to compare the string (strings would be pretty long).
* Using a hash map with default hashCode would also be too slow because it
will
* have a lot of hash collision.
*/
static class HashString {
String value;
int hash;
/**
* Implement the constructor. Remember to call calculateHash
*
* @param value The actual value of the string
*/
HashString(String value) {
this.value = value;
calculateHash();
}
/**
* Calculate the hash of the value and store it to hash.
*/
void calculateHash() {
//[ENTER YOUR CODE HERE]
int shift = 1;
for (int i = 0; i < value.length(); i++) {
hash = hash << shift | hash >>> 31;
hash += (int) value.charAt(i);
}
//return;
}
/**
* override the hashCode function used by HashMaps This should not be
changed.
*/
@Override
public int hashCode() {
return hash;
}
/**
* override the equals function to be used by HashMaps check if this
and obj are
* equal
*/
@Override
public boolean equals(Object obj) {
/**
* Check if two HashString are equal
*/
HashString hs = (HashString) obj;
if (this.hash == hs.hash) {
return true;
}
else {
return false;
}
}
}
HashMap<HashString, Integer> lastAppearance;// store last time a word appear
int wordCount;
HashString[] word;// HashString object for each word
/**
* read the line from input and convert them into HashString objects do not
* change this
*
* @param sc input Scanner object
*/
void input(Scanner sc) {
System.out.printf("Enter the number of words: ");
wordCount = sc.nextInt();
System.out.printf("Enter the text:\n");
word = new HashString[wordCount];
for (int i = 0; i < wordCount; i++)
word[i] = new HashString(sc.next());
}
/**
* For each word in the input, print out its last seen position. If it is the
* first time the word appear then print -1 instead.
*/
void process() {
//[ENTER YOUR CODE HERE]
HashMap<HashString, Integer> lastAppearance = new HashMap<HashString,
Integer>();
for (int i = 0; i < word.length ; i++) {
if (lastAppearance.containsKey(word[i])) {
System.out.print(lastAppearance.get(word[i]) + " ");
lastAppearance.put(word[i], i);
}
else {
System.out.print("-1 ");
}
lastAppearance.put(word[i], i);
}
}
public static void main(String[] argv) {
/**
* Testing codes, do not change
*/
Scanner sc = new Scanner(System.in);
VocabularyAnalysis VA = new VocabularyAnalysis();
VA.input(sc);
VA.process();
sc.close();
}
}
