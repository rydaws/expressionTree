package infix;

public class Node<T> {
	/*
	 * @author Ryan Dawson
	 * Date: 9/11/2021
	 * @version 1.0
	 */

	private T data;
	Node<T> left;
	Node<T> right;
	
	//Node<T> next;
	
	public Node() {
		
	}
	
	public Node(T data) {
		this.data = data;
	}
	
	public Node(T data, Node<T> leftChild, Node<T> rightChild) {
		this.data = data;
		this.left = leftChild;
		this.right = rightChild;
	}
	@Override
	/*
	 * @param o is object to be evaluated
	 */
	public boolean equals(Object o) {
		
		if(o == this)
		{
			return true;
		}
		return false;
	}
	/*
	 * returns true if matches or false if it does not
	 */
	
	@Override
	public String toString() {
		return "Node root: " + data + " Left: " + left + " Right: " + right + "\n";
	}
	/*
	 * @return returns string representation of tree
	 */


	public T getData() {
		return data;
	}
	/*
	 * @return data is generic data that was saved to the tree node
	 */

	/*
	 * @param data is generic data being saved to root of tree node
	 */
	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeft() {
		return left;
	}
	/*
	 * @return left is generic data that was saved to the left node
	 */

	/*
	 * @param left is generic node that is being saved as the left child
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}
	/*
	 * @return left is generic data that was saved to the right node
	 */

	/*
	 * @param right is generic node that is being saved as the right child
	 */
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
}
