import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: amedee
 * Date: 10/25/13
 * Time: 11:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Solution {
  class Handhold implements Comparable<Handhold> {
    public final String name;
    public ArrayList<Edge> adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Handhold previous;

    public Handhold(String argName) {
      adjacencies = new ArrayList<Edge>();
      name = argName;
    }

    public String toString() {
      return name;
    }

    public int compareTo(Handhold other) {
      return Double.compare(minDistance, other.minDistance);
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
    Collections.reverse(path);
    return path;
  }

  public Handhold matrixToGraph(int mountain[][], int N, int M) { //N rows, M columns [N][M] array
    Handhold start = new Handhold("start");
    Handhold top = new Handhold("top");
    Handhold[][] handholds = new ArrayList<Handhold>[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        handholds[i][j] = new Handhold(i + "," + j);
      }
    }

    for (int i = 0; i < mountain.length; i++) {
      start.adjacencies.add(new Edge(handholds[0][i], 0));
      handholds[N - 1][i].adjacencies.add(new Edge(top, 0));
    }
    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < M; j++) {
        handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j], mountain[i + 1][j]));
        if (j > 1) {
          handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j - 1], mountain[i + 1][j] - 1E6));
        }
        if (j < M - 1)
          handholds[i][j].adjacencies.add(new Edge(handholds[i + 1][j], mountain[i + 1][j + 1] + 1E6));
      }
    }
    new Listhandholds
    return top;
  }
  void main() {

  }

}
