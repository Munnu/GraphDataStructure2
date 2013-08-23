import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * This program allows a user to load a file
 * then add nodes, remove nodes, add edges,
 * remove edges, and perform traversals
 * 
 *  @author  Monique Blake
 *  @version CSC 212, 20 April 2011
 */

public class GraphReader{
  
  public static void main(String[] args) {
    ArrayList<Object> contents;
    /** instance of the graph class */
    Graph g = new Graph();
    /** initializes a new graph */
    Graph<String, String> info = new Graph<String, String>();
    
    /** print statements for menu */
    System.out.println("Choose an option: ");
    System.out.println("1. Load file ");
    System.out.println("2. Add a node ");
    System.out.println("3. Remove a node ");
    System.out.println("4. Add an edge");
    System.out.println("5. Remove an edge");
    System.out.println("6. BFT");
    System.out.println("7. Quit");
    

    /** reads the option inputted from the menu selection */
    Scanner scanner = new Scanner(System.in);
    String menuOpt = scanner.nextLine();
    
    /** As long as the user does not want to quit, perform specific tasks*/
    while(!menuOpt.equals("8")) {
      // read file
      if (menuOpt.equals("1")) {
        try {
          scanner = new Scanner(System.in);
          System.out.print("Enter filename: ");
          String fileName = scanner.nextLine();
          File file = new File(fileName);
          
          if ( file.exists() )                          
          {                                             
            Scanner inFile = new Scanner( file );
            
            while ( inFile.hasNext() ) {
              String line = inFile.nextLine(); 

              // split lines on whitespace
              String[] splitter = line.split("\\s+");
              
              for ( int i = 0; i < splitter.length; i++) {
                String spl = splitter[i];
                
                if(splitter[i].equals("n")) {
                  // increment i
                  i++;
                  // check next thing, add thing
                  info.addNode(splitter[i]);
                  // increment i again
                  // create node based on the data currently at splitter[i]
                  //add nodes
                } else if(splitter[i].equals("e")) {
                  try {
                    i++;
                    info.addEdge(splitter[i], info.getNode(Integer.parseInt(splitter[i+1])-1), info.getNode(Integer.parseInt(splitter[i+2])-1));
                  }
                  catch (IndexOutOfBoundsException e) {
                    System.out.println("Error, index out of bonuds");
                  }   
                } // end of else if
              } // end of for 
            }
            
            // close scanner
            inFile.close();
          }
        } catch (FileNotFoundException e) {
          System.out.println("Error, File not found."); 
        }
      } else if (menuOpt.equals("2")) { // adds a node
        System.out.println("Name of node: ");
        scanner = new Scanner(System.in);
        String nodeName = scanner.nextLine();
        info.addNode(nodeName);
      } else if (menuOpt.equals("3")) { // removes a node
        // to ensure user is not deleting from an empty graph
        if( info.numNodes() != 0) {
          // ask user for which node to delete
          scanner = new Scanner(System.in);
          String nodeName = scanner.nextLine();
          
          for(int i = 0; i < info.numNodes(); i++) {
            if(info.getNode(i).getData().contains(nodeName)){
              info.removeNode(info.getNode(i));
            }
          }
          
        } else {
          System.out.println("There are no nodes available to delete"); 
        }
      } else if (menuOpt.equals("4")){ // adds an edge
        // add an edge
        System.out.println("Name of edge: ");
        scanner = new Scanner(System.in);
        String edgeName = scanner.nextLine();
        System.out.println("Head node? Must be already in graph ");
        scanner = new Scanner(System.in);
        String headN = scanner.nextLine();
        System.out.println("Tail node? Must be already in graph ");
        scanner = new Scanner(System.in);
        String tailN = scanner.nextLine();
        
        // do a check to see if the data is found in any of the nodes
        
        for(int i = 0; i < info.numNodes(); i++) {
          for( int j = 0; j<info.numNodes(); j++ ) {
          if( (info.getNode(i).getData().contains(headN)) ) {
            if ((info.getNode(j).getData().contains(tailN))) {
              info.addEdge(edgeName, info.getNode(i), info.getNode(j) );

                          }
          }
          }  
        }
        
      } else if (menuOpt.equals("5")) { // removes an edge
        // ask user for which edge to remove
        if( info.numNodes() != 0) {
          System.out.println("Provide the name of the edge to remove");
          scanner = new Scanner(System.in);
          String edgeName = scanner.nextLine();
          for(int i = 0; i < info.numNodes(); i++) {
            if(info.getEdge(i).getData().contains(edgeName)){
              info.removeEdge(info.getEdge(i));
            }
          }
        }
      } else if (menuOpt.equals("6")) { //traverse
        System.out.println("Provide a start node");
        scanner = new Scanner(System.in);
        String startNode = scanner.nextLine();
        // find the node with this data
        for(int i = 0; i < info.numNodes(); i++) {
          if(info.getNode(i).getData().contains(startNode)){
            info.BFT(info.getNode(i));
          }
        }
      } else if (menuOpt.equals("7")) {
        info.print();
      }
      
      /*else if (menuOpt.equals("7")) {
        System.out.println("Provide a start node");
        scanner = new Scanner(System.in);
        String startNode = scanner.nextLine();
        // find the node with this data
        for(int i = 0; i < info.numNodes(); i++) {
          if(info.getNode(i).getData().contains(startNode)){
            info.DFTstart(info.getNode(i));
          }
        }
      }
      */
      

      
      // menu would be in here, another one
      System.out.println();
      System.out.println("Choose an option: ");
      System.out.println("1. Load file ");
      System.out.println("2. Add a node ");
      System.out.println("3. Remove a node ");
      System.out.println("4. Add an edge");
      System.out.println("5. Remove an edge");
      System.out.println("6. BFT");
      System.out.println("7. Print");
      System.out.println("8. Quit");
      
      menuOpt = scanner.nextLine();
      
    } // end of while
  }
  
}