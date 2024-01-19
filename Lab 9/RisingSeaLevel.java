
package lab10;
import java.util.*;
//import lab10.RisingSeaLevel.DSU;
public class RisingSeaLevel {
/**
* Disjoint set data structures, for more details, see for example,
* www.geeksforgeeks.org/disjoint-set-data-structures
*
* @param parent null if this is the root node, otherwise it point to its
* parent.
* @param size size of this set
*/
static class DSU {
DSU parent;
int size;
DSU() {
parent = null;
size = 1;
}
/** find the root of current node */
DSU findRoot() {
// path compression for extra performance boost
//[ENTER YOUR CODE HERE]his
if(this.parent == null) {
return this;
}
return parent.findRoot();
}
/** join two sets if they are different (have different root) */
static void join(DSU a, DSU b) {
//[ENTER YOUR CODE HERE]
//DSU dsu = new DSU();
DSU aRoot = a.findRoot();
DSU bRoot = b.findRoot();
if (aRoot == bRoot)
return;
else {
if(aRoot.size > bRoot.size) {
bRoot.parent = aRoot;
aRoot.size += bRoot.size;
}
else {
aRoot.parent = bRoot;
bRoot.size += aRoot.size;
}
}
}
void join(DSU d) {
DSU.join(this, d);
}
};
/** Cell class to represent each (x, y) cell */
class Cell implements Comparable<Cell> {
int x, y;
Cell(int x, int y) {
this.x = x;
this.y = y;
}
@Override
public int compareTo(Cell c) {
return elevation[x][y] - elevation[c.x][c.y];
}
/**
* This method process the merging of this cell to the underwater
component.
* Note that we process the cells in increasing order of elevation,
it's like we
* raise the sea level bit by bit. Each cells will be processed once
the water
* drown that cell. We only call this method if the elevation of this
cell is
* less than the water level
*/
void process() {
for (int i = 0; i < 4; i++) {
int u = x + dx[i];
int v = y + dy[i];
if ((u < 0) || (u >= height) || (v < 0) || (v >= width)) {
// if this cell has a neighbor outside of the map
then it is on the
// border, so we can connect it to the underwater
component
component[x][y].join(underwater);
} else if (elevation[x][y] >= elevation[u][v]) {
// water can only flow from this cell to some cell
with lower or equal
// elevation, so we will only connect in that case if
some cell is not connected
// now, it will be connected later when that cell is
processed.
component[x][y].join(component[u][v]);
}
}
}
}
/**
* @param height the height of the island entered from user
* @param width the width of the island entered from user
* @param elevation matrix of the island entered from user
*/
int height, width;
int[][] elevation;
/**
* @param component the component that each cell belong to
* @param underwater the special component that will be connected to every
cells
* that are underwater
* @param cells the order that we should process the cells in
*/
DSU[][] component;
DSU underwater;
static final int dx[] = { 0, 0, -1, 1 };
static final int dy[] = { -1, 1, 0, 0 };
ArrayList<Cell> cells;
/** Process the input from user */
/* this method is given and should not be changed */
void input() {
Scanner sc = new Scanner(System.in);
System.out.printf("Enter the height and width of the island: ");
height = sc.nextInt();
width = sc.nextInt();
elevation = new int[height][width];
System.out.printf("Enter the elevation:\n");
for (int i = 0; i < height; i++)
for (int j = 0; j < width; j++)
elevation[i][j] = sc.nextInt();
sc.close();
}
void process() {
/* initialize the DSU array */
component = new DSU[height][width];
underwater = new DSU();
for (int i = 0; i < height; i++)
for (int j = 0; j < width; j++)
component[i][j] = new DSU();
/* initialize the cells arraylist */
cells = new ArrayList<Cell>();
for (int i = 0; i < height; i++)
for (int j = 0; j < width; j++)
cells.add(new Cell(i, j));
Collections.sort(cells);
/*
* The naive to solve this problem would be to simulate the submerge
process
* like the problem statement said: Try all possible sea level height,
then for
* each height use bfs or dfs to find all the cell that are underwater.
That
* would be too slow. Another possible solution is to let the water
increase
* slowly, and we add cells in whenever they are underwater. This is
possible,
* it is another MST algorithm called Prim's algorithm.
*
* However, in this problem, we will use DSU, in the Kruskal's
algorithm. Notice
* how we keep a size infomation for DSU, we can use this to see how
many nodes
* are in the component. Then instead of doing DFS/BFS everytime, we
can just
* connect the node and check for the size each time.
*
* To count the number of cell underwater, we use a special node called
* "underwater" to support the operation. All the cells underwater will
be
* connected to this special node
*
* Because the water will continue to flow through every cell lower
than it, we
* can connect two cells when their elevation are both underwater.
*
* This only happen when the higher cell is underwater. So we can
iterate the
* water height in increasing order and connect cells.
*
* Because the problem only asks for the water heights that submerge
new cells,
* we only need to care about the heights that are also the elvation of
at least
* one cell. Of course, we need to process all cells of the same
elevation at
* once or we will give the wrong answer. Therefore, we sort all cell
in
* increasing order of elevation.
*
* The loop will then process each range of cells with equal elevation.
After
* that we can just process each cells and answer by the end. Because
there
* might not be any new submerged cell, we need to control for that.
*
* Notice that the number of cell underwater is underwater.size - 1,
because
* underwater.size is the size of the whole component, so we need to
remove the
* special vertex.
*/
//[ENTER YOUR CODE HERE]
int seaLevel = elevation[cells.get(0).x][cells.get(0).y];
for (int i = 0; i < cells.size(); i++) {
if(elevation[cells.get(i).x][cells.get(i).y] > seaLevel) {
System.out.printf("When the sea level raise to %d, %d
cell(s) would be underwater!\n", seaLevel , underwater.size - 1);
seaLevel = elevation[cells.get(i).x][cells.get(i).y];
}
cells.get(i).process();
}
System.out.printf("When the sea level raise to %d, %d cell(s) would be
underwater!\n", seaLevel , underwater.size - 1);
}
public static void main(String[] args) {
RisingSeaLevel SLR = new RisingSeaLevel();
SLR.input();
SLR.process();
}
}
