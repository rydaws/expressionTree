package infix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Driver {
	/*
	 * @author Ryan Dawson
	 * Date: 9/11/2021
	 * @version 1.0
	 */

	public static void main(String args[]) throws IOException {
		
		List<String> file = new ArrayList<String>(); //Holds elements from file
		LinkedList<String> myList = new LinkedList<String>(); //Holds postfix equations
		ArrayList<Node<String>> postfixTree = new ArrayList<Node<String>>();//Holds Tree nodes
		try {
			//Reads elements from the file and stores them in a list
			file = Files.readAllLines(Paths.get("file"), StandardCharsets.UTF_8);
		} catch (NoSuchFileException e) {
			System.out.println("No file found with that name!");
		}
		//Tree object is made to utilize it's functionality
		Tree run = new Tree();
		for (int i = 0; i < file.size(); i++) {
			//Converts current infix expression to postfix
			String output = run.inToPost(file.get(i));
			myList.add(output);
			//System.out.println("CONVERTED EQUATION: " + output);
		}
		// FINISH CONVERTING

		// BUILD TREE
		//Constructs tree with converted postfix expressions
		postfixTree = run.construct(myList);
		run.printTree(postfixTree);
		
		for (int i = 0; i < postfixTree.size(); i++) {
			//System.out.println("Current equation: " + postfixTree);
			System.out.print("Preorder: ");
			run.preorder(postfixTree.get(i));
			System.out.print("\nInorder: ");
			run.inorder(postfixTree.get(i));
			System.out.print("\nPostorder: ");
			run.postorder(postfixTree.get(i));
			System.out.println("\n");
		}
	}
	
}
