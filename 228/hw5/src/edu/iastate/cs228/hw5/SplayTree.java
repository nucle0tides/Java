package edu.iastate.cs228.hw5;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Gabby Ortman
 * 
 */


/**
 * 
 * This class implements a splay tree.  Add any helper methods or implementation details 
 * you'd like to include.
 *
 */


public class SplayTree<E extends Comparable<? super E>> extends AbstractSet<E>
{
	protected Node root; 
	protected int size; 

	private class Node 
	{
		public E data;
		public Node left;
		public Node parent;
		public Node right;

		public Node(E data) {
			this.data = data;
		}

		@Override
		public Node clone() {
			return new Node(data);
		}	
		
		
	}

	
	/**
	 * Default constructor constructs an empty tree. 
	 */
	public SplayTree() 
	{
		clear(); 
	}
	
	
	/**
	 * Needs to call addBST() later on to complete tree construction. 
	 */
	public SplayTree(E data) 
	{
		root = new Node(data); 
		this.size = 1; 
		
	}

	
	/**
	 * Copies over an existing splay tree. The entire tree structure must be copied.  
	 * No splaying. 
	 * 
	 * @param tree
	 */
	public SplayTree(SplayTree<E> tree)
	{  
		if(tree.root == null)
		{ 
			root = null; 
		}
		else 
		{ 
			size = tree.size; 
			root = copy(tree.root); 
		}
	}
	
	/**
	 * This function is here for grading purpose. It is not a good programming practice.
	 * This method is fully implemented and should not be modified.
	 * 
	 * @return root of the splay tree
	 */
	public E getRoot()
	{
		if(root == null)
		{ 
			return null; 
		}
		return (E) root.data; 
	}
	
	
	@Override 
	public int size()
	{
		return size; 
	}
	
	
	/**
	 * Clear the splay tree. 
	 */
	@Override
	public void clear() 
	{
		this.root = null; 
		this.size = 0; 
	}
	
	
	// ----------
	// BST method
	// ----------
	
	/**
	 * Adds an element to the tree without splaying.  The method carries out a binary search tree
	 * addition.  It is used for initializing a splay tree. 
	 * 
	 * @param data
	 * @return true  if addition takes place  
	 *         false otherwise 
	 */
	public boolean addBST(E data)
	{
	    if (root == null)
	    {
	      root = new Node(data);
	      ++size;
	      return true;
	    }
	    
	    Node current = root;
	    while (true)
	    {
	      int comp = current.data.compareTo(data);
	      if (comp == 0)
	      {
	        // key is already in the tree
	        return false;
	      }
	      else if (comp > 0)
	      {
	        if (current.left != null)
	        {
	          current = current.left;
	        }
	        else
	        {
	          current.left = new Node(data);
	          current.left.parent = current; 
	          ++size;
	          return true;
	        }
	      }
	      else
	      {
	        if (current.right != null)
	        {
	          current = current.right;
	        }
	        else
	        {
	          current.right = new Node(data);
	          current.right.parent = current; 
	          ++size;
	          return true;
	        }
	      }
	    } 
	}
	
	
	// ------------------
	// Splay tree methods 
	// ------------------
	
	/**
	 * Inserts an element into the splay tree. In case the element was not contained, this  
	 * creates a new node and splays the tree at the new node. If the element exists in the 
	 * tree already, it splays at the node containing the element. 
	 * 
	 * @param  data  element to be inserted
	 * @return true  if addition takes place 
	 *         false otherwise (i.e., data is in the tree already)
	 */
	@Override 
	public boolean add(E data)
	{
		if(size != 0)
		{ 
			Node n = findEntry(data); 
			if(n!= null && n.data.compareTo(data) == 0)
			{ 
				splay(n); 
				return false; 
			}
			else
			{ 
				addBST(data); 
				n = findEntry(data); 
				splay(n); 
				return true; 
			}
		}
		else
		{ 
			 addBST(data); 
			 return true; 
		}
		
	}
	
	
	/**
	 * Determines whether the tree contains an element.  Splays at the node that stores the 
	 * element.  If the element is not found, splays at the last node on the search path.
	 * 
	 * @param  data  element to be determined whether to exist in the tree
	 * @return true  if the element is contained in the tree 
	 *         false otherwise
	 */
	public boolean contains(E data)
	{
		if(data == null) return false; 
		Node n = findEntry(data);
		if(n.data == null) return false; 
		splay(n); 
		return n.data.compareTo(data) == 0; 
	}
	
	
	/**
	 * Splays at a node containing data.  Exists for coding convenience, code readability, and
	 * testing purpose.
	 * 
	 * @param data
	 */
	public void splay(E data) 
	{
		contains(data);
	}

	
	/**
	 * Removes the node that stores an element.  Splays at its parent node after removal
	 * (No splay if the removed node was the root.) If the node was not found, the last node 
	 * encountered on the search path is splayed to the root.
	 * 
	 * @param  data  element to be removed from the tree
	 * @return true  if the object is removed 
	 *         false if it was not contained in the tree 
	 */
	public boolean remove(E data)
	{
		Node n = findEntry(data); 
		int c = n.data.compareTo(data); 
		if (c != 0)
		{ 
			splay(n);
			return false; 
		}
		else
		{ 
			//I know this isn't correct, but for the life of me I can't figure out if the issue
			//is in join() or how I was implementing remove. 
			//*shrug*
			Node parent = n.parent;  
			unlinkNode(n); 
			if(parent != null)
			{ 
				splay(parent); 
			}
			return true; 
		}
	}
	



	/**
	 * This method finds an element stored in the splay tree that is equal to data as decided 
	 * by the compareTo() method of the class E.  This is useful for retrieving the value of 
	 * a pair <key, value> stored at some node knowing the key, via a call with a pair 
	 * <key, ?> where ? can be any object of E.   
	 * 
	 * Splays at the node containing the element or the last node on the search path. 
	 * 
	 * @param  data
	 * @return element such that element.compareTo(data) == 0
	 */
	public E findElement(E data) 
	{
		Node n = findEntry(data); 
		if(size == 0 || n == null)
		{ 
			return null; 
		}
		else if(n.data.compareTo(data) != 0)
		{ 
			splay(n); 
			return null; 
		}
		else
		{ 
			splay(n); 
			return n.data; 
		}
	}

	
	/**
	 * Finds the node that stores an element. It is called by several methods including contains(), 
	 * add(), remove(), and findElement(). 
	 * 
	 * No splay at the found node. 
	 *
	 * @param  data  element to be searched for 
	 * @return node  if found or the last node on the search path otherwise
	 *         null  if size == 0. 
	 */
	protected Node findEntry(E data)
	{
		Node current = root;
		Node previous = root; 
		if(size == 0 || data == null)
		{ 
			return null; 
		}
	    while (current != null)
	    {
	      int comp = current.data.compareTo(data);
	      if (comp == 0)
	      {
	        return current;
	      }
	      else if (comp > 0)
	      {
	    	previous = current; 
	        current = current.left;
	      }
	      else
	      {
	    	previous = current; 
	        current = current.right;
	      }
	    }   
	    return previous; 
	}
	
	
	/** 
	 * Join the two subtrees T1 and T2 rooted at root1 and root2 into one.  It is 
	 * called by remove(). 
	 * 
	 * Precondition: All elements in T1 are less than those in T2. 
	 * 
	 * Access the largest element in T1, and splay at the node to make it the root of T1.  
	 * Make T2 the right subtree of T1.  The method is called by remove(). 
	 * 
	 * @param root1  root of the subtree T1 
	 * @param root2  root of the subtree T2 
	 * @return the root of the joined subtree
	 */
	protected Node join(Node root1, Node root2)
	{
		//okay I can't figure this out, it's fine though............................................................
		//totally fine................................................................................
		if(root1 == null)
		{ 
			return root2; 
		}
		else if(root2 == null)
		{ 
			return root1; 
		}
		Node max = findMax(root1); 
		splay(max); 
		max.right = root2; 
		root2.parent = max;
		return max; 
	}

	
	/**
	 * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag 
	 * operations until the current node is moved to the root of the tree.
	 * 
	 * Pseudocode courtesy of the UC Berkeley lecture on Splay Trees on YT
	 * 
	 * @param current  node to splay
	 */
	protected void splay(Node current)
	{
		while(root != current)
		{ 
			if(current.parent == root)
			{ 
				zig(current); 
			}
			else 
			{ 
				Node parent = current.parent; 
				Node grandpappy = parent.parent; 
				if(grandpappy != null && ((grandpappy.left == parent && parent.left == current) || (grandpappy.right == parent && parent.right == current)))
				{ 
					zigZig(current); 
				}
				else
				{ 
					zigZag(current); 
				}
			}
		}
		//while N is not the root
			//if N is a child of the root: 
				//ZIG 
					//Rotate about the root to bring n to the root
			//else 
				// P = N.parent
				//G = P.parent 
				//if N and P are both left or both right children 
					//ZIG-ZIG 
					//rotate about G, then about P to bring N up two levels 
				//else 
					//ZIG-ZAG: 
					//Rotate about P then about G to bring N up two levels 
	}	
	

	/**
	 * This method performs the zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * Pseudocode courtesy of the UC Berkeley lecture on Splay Trees on YT
	 * 
	 * @param current  node to perform the zig operation on
	 */
	protected void zig(Node current)
    {
		if(current == null || current.parent == null)
		{ 
			return; 
		}
		
		if(current == current.parent.left)
		{ 
			rightRotate(current); 
		}
		else if (current == current.parent.right)
		{ 
			leftRotate(current); 
		}
		else
		{ 
			return; 
		}
	}

	
	/**
	 * This method performs the zig-zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * Pseudocode courtesy of the UC Berkeley lecture on Splay Trees on YT
	 * 
	 * @param current  node to perform the zig-zig operation on
	 */
	protected void zigZig(Node current)
	{
		Node parent = current.parent; 
		Node grandpappy = parent.parent; 
		if(grandpappy.left == parent && parent.left == current)
		{ 
			rightRotate(parent); 
			rightRotate(current); 
		}
		if(grandpappy.right == parent && parent.right == current)
		{ 
			leftRotate(parent); 
			leftRotate(current); 
		}
		//x is a left child of a left child 
			//rotate parent right 
			//rotate x right 
		//x is a right child of a right child 
			//rotate parent left
			//rotate x left 
		 
	}

	
    /**
	 * This method performs the zig-zag operation on a node. Calls leftRotate() 
	 * or rightRotate() or both.
	 * 
	 * Pseudocode courtesy of the UC Berkeley lecture on Splay Trees on YT
	 * 
	 * @param current  node to perform the zig-zag operation on
	 */
	protected void zigZag(Node current)
	{
		Node parent = current.parent; 
		Node grandpappy = parent.parent; 
		if(grandpappy.right == parent && parent.left == current)
		{ 
			rightRotate(current); 
			leftRotate(current); 
		}
		if(grandpappy.left == parent && parent.right == current)
		{ 
			leftRotate(current); 
			rightRotate(current); 
		}
		//x is left child of a right child 
			//
			//
		//or x is a right child of a left child 
			//rotate left 
			//rotate right
	}
	
	/**
	 * Return the max (rightmost) node in the BST 
	 * @param current
	 * @return
	 */
	private Node findMax(Node current)
	{ 
		if(current.right == null)
		{ 
			return current; 
		}
		else
		{ 
			return findMax(current.right); 
		}
	}
	
	/**
	 * Return the min (leftmost) node in the BST
	 * @param current
	 * @return
	 */
	private Node findMin(Node current)
	{ 
		if(current == null)
		{ 
			return null; 
		}
		else if(current.left == null)
		{ 
			return current; 
		}
		else
		{ 
			return findMin(current.left); 
		}
	}
	
	/**
	 * Carries out a left rotation at a node such that after the rotation 
	 * its former parent becomes its left child. 
	 * 
	 * Pseudocode courtesy of the UC Berkeley lecture on Splay Trees on YT
	 * 
	 * @param current
	 */
	private void leftRotate(Node current)
	{
		if (current== null || current.parent == null || current.parent.right != current) 
		{
		      return;
		} 

		    Node parent = current.parent;
		    Node grandpappy = parent.parent;

		    parent.right = current.left; 
		    if (current.left != null) {
		      current.left.parent = parent;
		    }

		    current.left = parent;
		    parent.parent = current; 

		    current.parent = grandpappy;
		    if (grandpappy == null) {
		      root = current; 
		    } else if (grandpappy.right == parent) {
		    	grandpappy.right = current; 
		    } else {
		    	grandpappy.left = current; 
		    }
	}

	
	/**
	 * Carries out a right rotation at a node such that after the rotation 
	 * its former parent becomes its right child. 
	 * 
	 * Pseudocode courtesy of the UC Berkeley lecture on Splay Trees on YT
	 * 
	 * @param current
	 */
	private void rightRotate(Node current)
	{
		if (current == null || current.parent == null || current.parent.left!= current) {
			return;
		}

		Node parent = current.parent;
		Node grandpappy = parent.parent;

		parent.left = current.right; 
		if (current.right != null) {
		  current.right.parent = parent;
		}

		    current.right = parent; 
		    parent.parent = current; 

		    current.parent = grandpappy;
		    if (grandpappy == null) {
		      root = current;
		    } else if (grandpappy.right == parent) {
		    	grandpappy.right = current; 
		    } else {
		    	grandpappy.left = current;
		    }
	}
	
	  /**
	   * Returns the successor of the given node.
	   * @param n
	   * @return the successor of the given node in this tree, 
	   *   or null if there is no successor
	   *   
	   *   Method from class notes
	   */
	  private Node successor(Node n)
	  {
	    if (n == null)
	    {
	      return null;
	    }
	    else if (n.right != null)
	    {
	      // leftmost entry in right subtree
	      Node current = n.right;
	      while (current.left != null)
	      {
	        current = current.left;
	      }
	      return current;
	    }
	    else 
	    {
	      // we need to go up the tree to the closest ancestor that is
	      // a left child; its parent must be the successor
	      Node current = n.parent;
	      Node child = n;
	      while (current != null && current.right == child)
	      {
	        child = current;
	        current = current.parent;
	      }
	      // either current is null, or child is left child of current
	      return current;
	    }
	  }
	  
	  /**
	   * Removes the given node, preserving the binary search
	   * tree property of the tree.
	   * @param n node to be removed
	   * 
	   * Method from class notes
	   */
	  private void unlinkNode(Node n)
	  {
	    // first deal with the two-child case; copy
	    // data from successor up to n, and then delete successor 
	    // node instead of given node n
	    if (n.left != null && n.right != null)
	    {
	      Node s = successor(n);
	      n.data = s.data;
	      n = s; // causes s to be deleted in code below
	    }
	    
	    // n has at most one child
	    Node replacement = null;    
	    if (n.left != null)
	    {
	      replacement = n.left;
	    }
	    else if (n.right != null)
	    {
	      replacement = n.right;
	    }
	    
	    // link replacement into tree in place of node n 
	    // (replacement may be null)
	    if (n.parent == null)
	    {
	      root = replacement;
	    }
	    else
	    {
	      if (n == n.parent.left)
	      {
	        n.parent.left = replacement;
	      }
	      else
	      {
	        n.parent.right = replacement;
	      }
	    }
	    
	    if (replacement != null)
	    {
	      replacement.parent = n.parent;
	    }
	    
	    --size;
	  }
	  
	  /**
		 * Fun and fresh recursive copying. 
		 * @param n
		 * @return
	   */
		private Node copy (Node n)
		{ 
			Node temp; 
			if (n == null)
			{ 
				temp = null; 
			}
			else
			{ 
				temp = new Node(null); 
				temp.data = n.data;
				temp.parent = n.parent;
				temp.left = copy(n.left); 
				temp.right = copy(n.right); 
				
			}
			return temp; 
		}

	
	
	@Override
	public Iterator<E> iterator()
	{
	    return new SplayTreeIterator();
	}

	
	/**
	   * Returns a representation of this tree as a multi-line string.
	   * The tree is drawn with the root at the left and children are
	   * shown top-to-bottom.  Leaves are marked with a "-" and non-leaves
	   * are marked with a "+".
	   */
	  @Override
	  public String toString()
	  {
	    StringBuilder sb = new StringBuilder();
	    toStringRec(root, sb, 0);
	    return sb.toString();
	  }
	  
	  /**
	   * Preorder traversal of the tree that builds a string representation
	   * in the given StringBuilder.
	   * @param n root of subtree to be traversed
	   * @param sb StringBuilder in which to create a string representation
	   * @param depth depth of the given node in the tree
	   */
	  private void toStringRec(Node n, StringBuilder sb, int depth)
	  {
		  for (int i = 0; i < depth; ++i)
		  {
				sb.append("    ");
		  }

		  if (n == null)
		  {
				sb.append("null\n");
				return;
		  }
		  sb.append(n.data.toString());
		  sb.append("\n");
		  if (n.left != null || n.right != null)
		  {
			toStringRec(n.left, sb, depth + 1);
			toStringRec(n.right, sb, depth + 1);
		  }
	  }
	
	
	/**
	   *
	   * Iterator implementation for this splay tree.  The elements are returned in 
	   * ascending order according to their natural ordering.  All iterator methods 
	   * are exactly the same as those for a binary search tree --- no splaying at 
	   * any node as the cursor moves. 
	   *
	   * Pretty much all of the code is from class notes
	   *
	   */
	private class SplayTreeIterator implements Iterator<E>
	{
		Node cursor;
		Node pending; 

	    public SplayTreeIterator()
	    {
	    	cursor = findMin(root); 
	    	pending = null; 
	    }
	    
	    @Override
	    public boolean hasNext()
	    {
	    	return cursor != null; 
	    }

	    @Override
	    public E next()
	    {
	    	if(!hasNext()) throw new NoSuchElementException(); 
	    	pending = cursor; 
	    	cursor = successor(cursor); 
	    	return pending.data; 
	    }

	    @Override
	    public void remove()
	    {
	      if (pending == null) throw new IllegalStateException();
	      if (pending.left != null && pending.right != null)
	      {
	    	  cursor = pending;
	      }
	      unlinkNode(pending);
	      pending = null;
	    }
	}
}
