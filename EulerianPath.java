package eulerianpath;

import java.util.ArrayList;
import java.util.Stack;
 
class EulerianPath 
{   // Function to find out the path.
    // It takes the adjacency matrix representation of the graph as input,
    // the start point of the path to find out and 
    // an array of Strings for the nodes
    static void printPath(int[][] graph, int n,int startPoint,String verteces[]){
        ArrayList<Integer> numofadj = new ArrayList<>();
 
        // Find the number of edges each vertex has
        for (int i = 0; i < n; i++)
            numofadj.add(grade(graph[i]));
 
        // Find how many verteces have odd number of edges
        // If more than two, there is no eulerian path 
        int numofodds = 0;
        for (int i = n - 1; i >= 0; i--){
            if (numofadj.get(i) % 2 == 1){
                numofodds++;
        //Change the start vertex if there is semi-eulerian path        
                startPoint=numofadj.get(i);
            }
        }
        // If the number of verteces with odd number of edges
        // is greater than two retutn.
        if (numofodds > 2 || numofodds==1){
            System.out.println("No Solution");
            return;
        }
        // Else if there is a path find out the path
        // Initialize an empty stack and path
        // take the starting vertex as current
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> path = new ArrayList<>();
        int cur = startPoint;
        // Loop will run until there is element in the stack
        // or current edge has some neighbour.
        while (!stack.isEmpty() || grade(graph[cur]) != 0){
            // If current node has not any neighbour
            // add it to path and pop stack
            // set new current to the popped element
            if (grade(graph[cur]) == 0){
                path.add(cur);
                cur = stack.pop();
                // If the current vertex has at least one
                // neighbour add the current vertex to stack,
                // remove the edge between them and set the
                // current to its neighbour.
            } 
            else{
                for (int i = 0; i < n; i++){
                    if (graph[cur][i] >0){
                        stack.add(cur);
                        graph[cur][i] --;
                        graph[i][cur] --;
                        cur = i;
                        break;
                    }
                }
            }
        }
        // print the path
        for (int ele : path)
            System.out.print(verteces[ele] + " -> ");
        System.out.println(verteces[cur]);
    }
 
    static int grade(int[] arr){
        int count=0;
        for (int i : arr)
            count += i;
        return count;
    }
 
    
    public static void main(String[] args){
        int[][] graph1 = { { 0, 1, 1, 0, 0, 2, 0 },
                           { 1, 0, 1, 0, 0, 0, 0 },
                           { 1, 1, 0, 1, 0, 0, 1 },
                           { 0, 0, 1, 0, 2, 0, 1 },
                           { 0, 0, 0, 2, 0, 1, 1 }, 
                           { 2, 0, 0, 0 ,1, 0, 1 }, 
                           { 0, 0, 1, 1, 1, 1, 0} };
        int n = graph1.length;
        String []verteces={"A","B","C","D","E","F","G"};
        printPath(graph1, n,0,verteces);    
    }
}
 

