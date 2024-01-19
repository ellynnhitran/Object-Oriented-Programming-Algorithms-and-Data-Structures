

package lab09;
import java.util.*;
class Position {
int i;
int j;
public Position(int x, int y) {
this.i = x;
this.j = y;
}
}
public class ChessBoard {
private int size;
private boolean[][] board;
private int[][] status;
static int[] di = { -2, -2, -1, -1, 1, 1, 2, 2 };
static int[] dj = { -1, 1, -2, 2, -2, 2, -1, 1 };
public ChessBoard(int n) {
size = n;
board = new boolean[size][size];
status = new int[size][size];
}
Position p;
public void addKnight(int i, int j) {
if (board[i][j]) {
System.out.printf("There is already a knight on (%d, %d)!\n", i,
j);
} else {
for (int a = 0; a < size; a++) {
for (int b = 0; b < size; b++) {
board[i][j] = true;
}
}
System.out.printf("Added a knight on (%d, %d)!\n", i, j);
}
}
public void removeKnight(int i, int j) {
if (!board[i][j]) {
System.out.printf("There is no knight on (%d, %d)!\n", i, j);}
else {
board[i][j] = false;
System.out.printf("Removed the knight on (%d, %d)!\n", i, j);
}
}
public void print() {
System.out.printf("The current board:\n");
for (int i = 0; i < size; i++) {
for (int j = 0; j < size; j++) {
if (board[i][j])
System.out.print("X ");
else
System.out.print("0 ");
}
System.out.println();
}
System.out.println();
}
// this is for debug only
public void printMatrix(int[][] status) {
for (int i = 0; i < size; i++) {
for (int j = 0; j < size; j++) {
if (status[i][j] == Integer.MAX_VALUE)
System.out.print("* ");
else
System.out.print(status[i][j] + " ");
}
System.out.println();
}
System.out.println();
}
public int moveKnight(int i, int j, int m, int n) {
int numMove = 0;
if (!checkInside(i, j)) {
System.err.printf("(%d, %d) is not inside the board!\n", i, j);
System.exit(0);
}
if (!checkInside(m, n)) {
System.err.printf("(%d, %d) is not inside the board!\n", m, n);
System.exit(0);
}
if (!board[i][j]) {
System.out.printf("There is no knight on (%d, %d)!\n", i, j);
return -1;
}
if (board[m][n]) {
System.out.printf("There is already a knight on (%d, %d)!\n", m,
n);
return -1;
}
for (int x = 0; x < size; x++) {
for (int y = 0; y < size; y++) {
status[x][y] = Integer.MAX_VALUE;
}
}
status[i][j] = 0; // start pos is 0
boolean[][] visited = new boolean[size][size];
Queue<Position> queue = new LinkedList<Position>();
//[ENTER YOUR CODE HERE]
// Adding a new Position at (i,j) and adding to the queue
// Use the moveBFS to travel through all position on the Chessboard
// from this (i,j) position and update the status matrix, which
// store the minimum number of moves.
queue.add(new Position(i, j));
moveBFS(m, n, visited, queue);
//rintMatrix(status); // for debug
numMove = status[m][n];
if (numMove == Integer.MAX_VALUE) {
System.out.printf("There is no valid way to move the knight from
(%d, %d) to (%d, %d)!\n",
i, j, m, n);
} else {
board[m][n] = true;
board[i][j] = false;
System.out.printf("Moved the knight from (%d, %d) to (%d, %d).\
nThe minimum number of moves is: %d!\n",
i, j, m, n, numMove);
}
return numMove;
}
private void moveBFS(int m, int n, boolean[][] visited, Queue<Position>
queue) {
while (!queue.isEmpty()) {
Position pos = queue.poll();
//if (pos.i == m && pos.j == n ) {
// visited.clear();
// return status[pos.i][pos.j];
//}
for (int b = 0; b < di.length; b++) {
int new_di = pos.i + di[b];
int new_dj = pos.j + dj[b];
if ((new_di < size && new_di >= 0 ) && (new_dj < size
&& new_dj >= 0)) {
if (!board[new_di][new_dj] ) {
if (visited[new_di][new_dj]) {
continue;
}
else {
if ( status[new_di][new_dj]>
(status[pos.i][pos.j] + 1)) {
visited[new_di][new_dj]
= true;
queue.add(new
Position(new_di, new_dj));
status[new_di][new_dj] =
status[pos.i][pos.j] + 1;
}
}
}
}
else
continue;
}
}
}
private boolean checkInside(int i, int j) {
return (i < size && i >= 0 ) && (j < size && j >= 0) ;
}
/* THE FOLLOWING TESTCODE SHOULD BE USED WITHOUT MODIFICATION */
public static void testChessBoard(Scanner sc) {
System.out.printf("Enter the board size:\n");
int N = Integer.parseInt(sc.nextLine());
ChessBoard chess = new ChessBoard(N);
System.out.printf("Enter the operations:\n");
while (true) {
String[] argv = sc.nextLine().split(" ");
if (argv[0].equalsIgnoreCase("exit"))
break;
else if (argv[0].equalsIgnoreCase("add") ||
argv[0].equalsIgnoreCase("remove")) {
int x = Integer.parseInt(argv[1]);
int y = Integer.parseInt(argv[2]);
if (!chess.checkInside(x, y)) {
System.err.printf("(%d, %d) is not inside the board!\
n", x, y);
System.exit(-1);
}
if (argv[0].equalsIgnoreCase("add"))
chess.addKnight(x, y);
else
chess.removeKnight(x, y);
} else if (argv[0].equalsIgnoreCase("move")) {
int x0 = Integer.parseInt(argv[1]);
int y0 = Integer.parseInt(argv[2]);
int x1 = Integer.parseInt(argv[3]);
int y1 = Integer.parseInt(argv[4]);
chess.moveKnight(x0, y0, x1, y1);
} else if (argv[0].equalsIgnoreCase("print"))
chess.print();
else {
System.err.printf("Operation %s not supported!\n",
argv[0]);
System.exit(-1);
}
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
ChessBoard.testChessBoard(sc);
sc.close();
}
}
