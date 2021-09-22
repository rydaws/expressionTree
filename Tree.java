package infix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Tree {
	/*
	 * @author Ryan Dawson
	 * Date: 9/11/2021
	 * @version 1.0
	 */

	public LinkedList<String> myList = new LinkedList<String>();
	public ArrayList<Node<String>> tree = new ArrayList<Node<String>>();
	// public String[] postArray;

	// Default constructor
	public Tree() {
	}

	public Tree(String infix) {
	}

	@Override
	/*
	 * @param o is object to be evaluated
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		return false;
	}

	/*
	 * returns true if matches or false if it does not
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	/*
	 * @return class and simple name
	 */

	// Converts tree to preorder notation
	/*
	 * @param root to convert tree to preorder notation
	 */
	public void preorder(Node<String> root) {
		if (root != null) {
			System.out.print(root.getData() + " ");
			preorder(root.getLeft());
			preorder(root.getRight());
		}
	}

	// Converts tree to inorder notation
	/*
	 * @param root to convert tree to inorder notation
	 */
	public void inorder(Node<String> root) {
		if (root != null) {
			inorder(root.getLeft());
			System.out.print(root.getData() + " ");
			inorder(root.getRight());
		}
	}

	// Converts tree to postorder notation
	/*
	 * @param root to convert tree to postorder notation
	 */
	public void postorder(Node<String> root) {
		if (root != null) {
			postorder(root.getLeft());
			postorder(root.getRight());
			System.out.print(root.getData() + " ");
		}
	}

	// Converts infix expression to postfix expression
	/*
	 * @param I is incoming infix equation
	 */
	public String inToPost(String I) {
		// Initializes variables and stack to hold operators
		// Tokenizes expressions using spaces as delimiters
		StringTokenizer token = new StringTokenizer(I, " ");
		Stack<String> stack = new Stack<String>();
		String postString = "";
		//String org = I;
		// Uses for loop to increment through the string parameter
		while (token.hasMoreTokens()) {
			String temp = token.nextToken();
			char ch = temp.charAt(0);
			// Checks to make sure character is letter or digit, then adds it to the output
			// string.
			if (Character.isLetterOrDigit(ch)) {
				postString += temp + " ";
				// If current char = '(' or stack is empty, push current char onto stack
			} else if (ch == '(' || stack.isEmpty()) {
				stack.push(temp);
				// If current char = ')', then adds the the stack to the string until '(' is
				// reached
			} else if (ch == ')') {
				while (!stack.isEmpty() && stack.peek().charAt(0) != '(') {
					postString += stack.pop() + " ";

				}
				// Pops off '(' to prevent interference.
				stack.pop();
			} else {
				// Pops off stack and adds to string when precedence of current char is less
				// than or = to
				// the operator on top of stack
				while (!stack.isEmpty() && stack.peek().charAt(0) != '('
						&& precedence(ch) <= precedence(stack.peek().charAt(0))) {
					postString += stack.pop() + " ";
				}
				stack.push(temp);
			}
			// Prints out current stack so user can visuailze what is going on
			// System.out.println("Current Stack:" + stack);
			// System.out.println(postString + "\n");
		}
		// pops off remaining items on stack if they are = to '(' or ';' then appends
		// the rest of the items to the string
		while (!stack.isEmpty()) {
			if (stack.peek().charAt(0) == '(' || stack.peek().charAt(0) == ';') {
				stack.pop();
			} else {
				postString += stack.pop() + " ";
			}
		}
		// Adds orginal and converted equation to linked list.
		// myList.add("Original infix: " + org + " \nConverted postfix: " + postString +
		// "\n");
		myList.add(postString);
		return postString;

	}
	/*
	 * @return postString which is the final converted postfix expression string
	 */

	// Used to find precdence of operators
	/*
	 * @param ch to check precedence of operator
	 */
	public int precedence(char ch) {
		switch (ch) {
		case '-':
		case '+':
			return 1;
		case '/':
		case '*':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}
	/*
	 * @return returns precdence of operator
	 */
	// Method to verify if incoming element is an operator

	/*
	 * @param c operator being evaluated
	 */
	boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
			return true;
		}
		return false;
	}
	/*
	 * @return returns tree if matches false if not
	 */

	// Constructs a tree from the converted postfix expressions

	/*
	 * @param list is list of postfix expressions
	 */
	public ArrayList<Node<String>> construct(LinkedList<String> list) {
		Stack<Node<String>> stack = new Stack<Node<String>>();
		String postfix = "";
		String workingChar; //Holds each token

		for (int i = 0; i < myList.size(); i++) {
			postfix = myList.get(i);
			StringTokenizer token = new StringTokenizer(postfix, " ");
			// System.out.println("POSTFIX EXP " + postfix);
			while (token.hasMoreTokens()) {
				workingChar = token.nextToken();
				// System.out.println("TOKEN: " + workingChar);

				// System.out.println("CURRENT WORKING ELEMENT: " + workingChar);
				// System.out.println(workingChar.charAt(0));

				// If element is not an operator it is added to the stack
				if (!isOperator(workingChar.charAt(0))) {
					Node<String> current = new Node<String>();
					current.setData(workingChar);
					stack.push(current);
				} else {
					// System.out.println("Stack has more 2 elements");
					// System.out.println(workingChar);

					// If element is not an operator, 2 elements on the stack are popped
					// and are make into the left and right child of the incoming node
					Node<String> left = new Node<String>();
					Node<String> right = new Node<String>();
					Node<String> root = new Node<String>();
					right = stack.pop();
					left = stack.pop();
					root.setData(workingChar);
					root.setLeft(left);
					root.setRight(right);
					stack.push(root);
				}

			}
			Node<String> addToList = new Node<String>();
			addToList = stack.peek();
			// System.out.println("BUILD NODE: " + addToList.toString());

			// Adds completed tree to list
			tree.add(addToList);
		}
		return tree;
	}
	/*
	 * @return tree is list of Tree's from all equations
	 */

	// Prints the tree for the user to visualize

	/*
	 * @param tree is arraylist of trees to print to user
	 */
	public String printTree(ArrayList<Node<String>> tree) {
		for (int i = 0; i < tree.size(); i++) {
			/*
			Node<String> root = new Node<String>();
			root = tree.get(i);

			System.out.println("Tree print " + i + ": " + root.toString());
			*/
		}
		return null;
	}

	/*
	 * @return null
	 */

}
