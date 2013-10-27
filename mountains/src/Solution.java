import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Handhold implements Comparable<Handhold> {
  public int row, col;
  public ArrayList<Edge> adjacencies;
  public double minDistance = Double.POSITIVE_INFINITY;
  public Handhold previous;

  public Handhold(int i, int j) {
    adjacencies = new ArrayList<Edge>();
    row = i;
    col = j;
  }

  public String toString() {
     return ("[" + (6 - row) + "," + col + "]");
  }

  public int compareTo(Handhold other) {
    int comp = Double.compare(minDistance, other.minDistance);
    if (comp == 0 ) {
      assert (this.col != other.col);
      if (this.col <  other.col) { comp = -1; }
      else comp = 1;
    }
    return comp;
  }
}

class Edge {
  public final Handhold target;
  public final double weight;

  public Edge(Handhold argTarget, double argWeight) {
    target = argTarget;
    weight = argWeight;
  }
}
public class Solution {

  public static void computePaths(Handhold source) {
    source.minDistance = 0.;
    PriorityQueue<Handhold> vertexQueue = new PriorityQueue<Handhold>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
      Handhold u = vertexQueue.poll();

      // Visit each edge exiting u
      for (Edge e : u.adjacencies) {
        Handhold v = e.target;
        double weight = e.weight;
        double distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
          vertexQueue.remove(v);
          v.minDistance = distanceThroughU;
          v.previous = u;
          vertexQueue.add(v);
        }
      }
    }
  }

  public static List<Handhold> getShortestPathTo(Handhold target) {
    List<Handhold> path = new ArrayList<Handhold>();
    for (Handhold vertex = target; vertex != null; vertex = vertex.previous)
      path.add(vertex);
//    Collections.reverse(path);
    return path;
  }

  public static Handhold[] matrixToGraph(int weights[][], int N, int M) { //N rows, M columns [N][M] array
    Handhold start = new Handhold(-1,0);
    Handhold top = new Handhold(0,-1);
    Handhold[][] handholds = new Handhold[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        handholds[i][j] = new Handhold(i,j);
      }
    }

    for (int i = 0; i < M; i++) {
      start.adjacencies.add(new Edge(handholds[0][i], weights[0][i]));
      handholds[N - 1][i].adjacencies.add(new Edge(top, 0));
    }

    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < M; j++) {
        handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j], weights[i + 1][j]));
        if (j > 1) {
          handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j - 1], weights[i + 1][j - 1]));
        }
        if (j < M - 1)
          handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j + 1], weights[i + 1][j + 1]));
      }
    }
    Handhold[] a = {start, top};
    return a;
  }
  public static int[][] loadMatrix() {
    String input = "";
    BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    //Read input and get the size of the matrices
    try{input = br.readLine();}
    catch(Exception e){System.out.println("ERROR");}
    String[] sizeArray = input.split(" ");

    //Get the size
    int rows = Integer.parseInt(sizeArray[0]);
    int columns = Integer.parseInt(sizeArray[1]);

    String[] rowArray = new String[columns];
    int[][] intMountain = new int[rows][columns];

    for (int i = rows -1; i >= 0; i--)
    {
      try { input = br.readLine();}
      catch(Exception e){ System.out.println("ERROR"); }
      rowArray = input.split(" "); //And then put that into the first row of the mountain matrix
      for (int j = 0; j < columns; j++)
        intMountain[i][j] = Integer.parseInt(rowArray[j]);
    }

    return intMountain;
  }


  public static void main(String[] args) {
    int[][] weights = loadMatrix();
    int N = weights.length;
    int M = weights[0].length;

    Handhold start = new Handhold(-1,1);
    Handhold top = new Handhold(-1,-1);
    Handhold[][] handholds = new Handhold[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        handholds[i][j] = new Handhold(i,j);
      }
    }

    for (int i = 0; i < M; i++) {
      start.adjacencies.add(new Edge(handholds[0][i], weights[0][i]));
      handholds[N - 1][i].adjacencies.add(new Edge(top, 0));
    }

    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < M; j++) {
        handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j], weights[i + 1][j]));
        if (j >= 1) {
          handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j - 1], weights[i + 1][j - 1]));
        }
        if (j < M - 1)
          handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j + 1], weights[i + 1][j + 1]));
      }
    }

    computePaths(start);
    List<Handhold> paths = getShortestPathTo(top);
    double totalRisk = 0;
    System.out.print("Minimum risk path = ");
    for( Handhold h : paths) {
      if(h.row < 0) continue;
      System.out.print(h.toString());
      //totalRisk += h.adjacencies.
    }
    System.out.println();
    System.out.println("Risks along the path = " + (int)(top.minDistance));
  }

}
