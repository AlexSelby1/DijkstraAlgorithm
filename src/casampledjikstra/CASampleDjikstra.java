package casampledjikstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alex.Selby
 */
public class CASampleDjikstra {

    // A method to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    int vertices;
    static int adjacencyMatrix[][];
    int source;
    String id;

    public CASampleDjikstra(int vertices) throws IOException{
                String fileName = "C:\\Users\\Alex.Selby\\Documents\\Software Dev Diploma\\Semester 3\\Advanced Programming\\CA\\VertexCSV.csv";
        FileReader fr = new FileReader(fileName);
        //Scanner parser = new Scanner(fileName);
        BufferedReader in = new BufferedReader(fr);
        this.vertices = vertices;
        adjacencyMatrix = new int[vertices][vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    //This method returns the vertex in an array of vertices which has the minimum distance
    public int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < vertices; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A method function to print the constructed distance array
    public void printAllSolutions(int dist[]) {

        System.out.println("Vertex \t\t Distance from Source");
        for (int i = source; i < vertices; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }
    

    // A method function to print the constructed distance array
    public void printSolution(int dist[], int source, int destination) throws IOException {
        //file writer adding each shortest path to text file
        FileWriter writer = new FileWriter("file.txt",true);
        System.out.println("Shortest Path from Vertex " + source + " to Vertex " + destination);
        System.out.println(dist[destination]);
        writer.write("Shortest Path from Vertex " + source + " to Vertex " + destination + ": " + Integer.toString(dist[destination]) + "\n");
        writer.close();
    }

    // Method that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    public void dijkstraStart(int graph[][], int source, int destination) throws IOException {

        int dist[] = new int[vertices]; // The output array.

        Boolean sptSet[] = new Boolean[vertices];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[source] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < vertices - 1; count++) {

            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to source in first
            // iteration.
            //LECTURE NOTES: Let u be the vertex not in the cloud, that has the smallest weight D[u]
            //Add u to the cloud (our source at the beginning)
            int u = minDistance(dist, sptSet);
            // Mark the picked vertex as processed
            sptSet[u] = true;
           // System.out.println("Setting position " + u + " in sptSet[u] to true: ");

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < vertices; v++) {
                // Update dist[v] if
                if (!sptSet[v]
                        && //it is not in sptSet and
                        graph[u][v] != 0
                        && //there is an edge from u to v and
                        dist[u] != Integer.MAX_VALUE
                        && //
                        dist[u] + graph[u][v] < dist[v]) //total weight of path from source to v through u is smaller than current value of dist[v]
                {
                    dist[v] = dist[u] + graph[u][v];
                    //System.out.println("updating dist[v] to: " + dist[v]);
                }
            }

        }

        // print the constructed distance array
        printSolution(dist, source, destination);
        //  printAllSolutions(dist);
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyMatrix[source][destination] = weight;
    }

    // Main method
    public static void main(String[] args) throws IOException {
        try {
            int s;
            int d;
            FileWriter writer = new FileWriter("file.txt",false); //Clears file before each loop
           do{
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter the start vertex");
             s = input.nextInt();
            System.out.println("Please enter the destination vertex");
             d = input.nextInt();
            CASampleDjikstra sp = new CASampleDjikstra(16);

            sp.addEdge(0, 1, 3);
            sp.addEdge(1, 0, 3);
            sp.addEdge(0, 9, 2);
            sp.addEdge(9, 0, 2);
            sp.addEdge(0, 10, 18);
            sp.addEdge(10, 0, 18);
            sp.addEdge(10, 11, 9);
            sp.addEdge(11, 10, 9);
            sp.addEdge(11, 12, 4);
            sp.addEdge(12, 11, 4);
            sp.addEdge(12, 5, 4);
            sp.addEdge(5, 12, 4);
            sp.addEdge(1, 2, 7);
            sp.addEdge(2, 1, 7);
            sp.addEdge(2, 15, 2);
            sp.addEdge(15, 2, 2);
            sp.addEdge(15, 3, 4);
            sp.addEdge(3, 15, 4);
            sp.addEdge(15, 11, 6);
            sp.addEdge(11, 15, 6);
            sp.addEdge(3, 4, 6);
            sp.addEdge(4, 3, 6);
            sp.addEdge(4, 5, 12);
            sp.addEdge(5, 4, 12);
            sp.addEdge(5, 13, 6);
            sp.addEdge(13, 5, 6);
            sp.addEdge(13, 14, 14);
            sp.addEdge(14, 13, 14);
            sp.addEdge(5, 6, 3);
            sp.addEdge(6, 5, 3);
            sp.addEdge(6, 7, 10);
            sp.addEdge(7, 6, 10);
            sp.addEdge(7, 8, 14);
            sp.addEdge(8, 7, 14);
            sp.addEdge(8, 9, 8);
            sp.addEdge(9, 8, 8);

            sp.dijkstraStart(adjacencyMatrix, s, d);
           }while(true);
        } catch (InputMismatchException ime) {
            System.err.println("\nInputMisMatchException occurs.\n");
            ime.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException abo) {
            System.err.println("\nArrayIndexOutOfBoundsException occurs.\n");
            abo.printStackTrace();
        } catch (NumberFormatException nfe) {
            System.err.println("\nNumberFormatException occurs.\n");
            nfe.printStackTrace();
        }
        finally { //If exception thrown, close connection.
            System.out.println("\n* Closing connection... *");
          System.exit(1); //Step 9.
    }
    }
}
