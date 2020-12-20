package eulerianpath;

import java.util.ArrayList;
import java.util.Stack;


 
class EulerianPath 
{
 
    // Function to find out the path.
    // It takes the adjacency matrix representation of the graph as input,
    // the start point of the path to find out and 
    // an array of Strings for the nodes
    static void findpath(int[][] graph, int n,int startPoint,String verteces[])
    {
        ArrayList<Integer> numofadj = new ArrayList<>();
 
        // Find out number of edges each vertex has
        for (int i = 0; i < n; i++)
            numofadj.add(grade(graph[i], 0));
 
        // Find out how many vertex has odd number edges
        int numofodd = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            if (numofadj.get(i) % 2 == 1) 
            {
                numofodd++;
                startPoint=numofadj.get(i);
            }
        }
 
        // If number of vertex with odd number of edges
        // is greater than two return "No Solution".
        if (numofodd > 2 || numofodd==1)
        {
            System.out.println("No Solution");
            return;
        }
 
        // If there is a path find the path
        // Initialize empty stack and path
        // take the starting current as discussed
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> path = new ArrayList<>();
        int cur = startPoint;
 
        // Loop will run until there is element in the stack
        // or current edge has some neighbour.
        while (!stack.isEmpty() || grade(graph[cur], 0) != 0)
        {
 
            // If current node has not any neighbour
            // add it to path and pop stack
            // set new current to the popped element
            if (grade(graph[cur], 0) == 0)
            {
                path.add(cur);
                cur = stack.pop();
 
                // If the current vertex has at least one
                // neighbour add the current vertex to stack,
                // remove the edge between them and set the
                // current to its neighbour.
            } 
            else
            {
                for (int i = 0; i < n; i++)
                {
                    if (graph[cur][i] >0) 
                    {
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
 
    static int grade(int[] arr, int sum)
    {
        for (int i : arr)
            sum += i;
        return sum;
    }
 
    // Driver Code
    public static void main(String[] args)
    {
 
        // Test case 1
        int[][] graph1 = { { 0, 1, 1, 0, 0, 2, 0 },
                           { 1, 0, 1, 0, 0, 0, 0 },
                           { 1, 1, 0, 1, 0, 0, 1 },
                           { 0, 0, 1, 0, 2, 0, 1 },
                           { 0, 0, 0, 2, 0, 1, 1 }, 
                           { 2, 0, 0, 0 ,1, 0, 1 }, 
                           { 0, 0, 1, 1, 1, 1, 0} };
        int n = graph1.length;
        String []verteces={"A","B","C","D","E","F","G"};
        findpath(graph1, n,0,verteces);
 
        
    }
}
 

