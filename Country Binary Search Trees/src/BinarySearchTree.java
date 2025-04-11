/**
 * Create, search from, insert into, delete from, and display the country binary tree using pre-order, in-order, and post-order traversals.
 * 
 * @author Shirley Chen
 * @version 04/10/2025
 */
public class BinarySearchTree 
{
	//Class variable declaration
	Node root;
	
	/**
	 * Constructor to create the country binary search tree
	 */
	public BinarySearchTree()
	{
		root = null;
	}//end BinarySearchTree
	
	/**
	 * Create and insert a country node into the binary search tree
	 * 
	 * @param name The name of country to be inserted
	 * @param happiness The happiness of country to be inserted
	 */
	public void insert(String name, double happiness)
	{
		//Create new country node and set variables
		Node newCountry = new Node();
		newCountry.countryName = name;
		newCountry.happiness = happiness;
		
		//Insert new country node into tree
		if(root == null)
		{
			//Set root to new country node if tree is empty
			root = newCountry;
		}
		else 
		{
			//Set tracker nodes
			Node current = root;
			Node parent;
			
			while(true)
			{
				parent = current;
				
				//Traverse left or right on tree based on if new country's name is lexicographically smaller or larger than current country's
				if(newCountry.countryName.compareTo(current.countryName) < 0)
				{
					current = current.leftChild;
					
					if(current == null)
					{
						parent.leftChild = newCountry;
						return;
					}//end if
				}
				else 
				{
					current = current.rightChild;
					
					if(current == null)
					{
						parent.rightChild = newCountry;
						return;
					}//end if
				}//end inner if-else
			}//end while
		}//end outer if-else		
	}//end insert
	
	/**
	 * Find a country node within tree and print its path
	 * 
	 * @param name The name of country to find
	 * 
	 * @return The country's happiness or -1 if the country was not found
	 */
	public double find(String name)
	{
		//Local variable declarations
		Node current = root;
		String path = "";
		
		//Traverse through the tree to find the country name
		while(current != null)
		{
			//Update path with current country name
			if(current == root)
			{
				path = root.countryName;
			}
			else
			{
				path = path + " -> " + current.countryName;
			}
			
			//Check if name is equal to the current country name
			if(name.equalsIgnoreCase(current.countryName))
			{
				//Display path of country found
				System.out.println(name + " is found with happiness of " + current.happiness);
				System.out.println("Path to " + name + " is " + path + ".");
				
				return current.happiness;
			}
			else if(name.compareTo(current.countryName) < 0)
			{
				current = current.leftChild;
			}
			else
			{
				current = current.rightChild;
			}//end if-else if-else
			
		}//end while
		
		return -1;
		
	}//end find
	
	/**
	 * Delete a country node from tree
	 * 
	 * @param name The name of country to delete
	 */
	public void delete(String name)
	{
		//Local variable declarations
		Node current = root;
		Node parent = null;
		boolean found = false;
		
		//Traverse tree to find and delete the desired country
		while(current != null)
		{
			//Check if name matches current country name
			if(name.equalsIgnoreCase(current.countryName))
			{
				found = true;
				
				//Handle the children of node to be deleted
				if(current.leftChild == null && current.rightChild == null) //Case 1: No children (leaf node)
				{
					if(current == root)
					{
						root = null;
					}
					else if(current == parent.leftChild)
					{
						parent.leftChild = null;
					}
					else
					{
						parent.rightChild = null;
					}//end if-else if-else
	
				}
				else if(current.rightChild == null) //Case 2: Only left child
				{
					if(current == root)
					{
						root = current.leftChild;
					}
					else if(current == parent.leftChild)
					{
						parent.leftChild = current.leftChild;
					}
					else
					{
						parent.rightChild = current.leftChild;
					}
				}
				else if(current.leftChild == null) //Case 3: Only right child
				{
					if(current == root)
					{
						root = current.rightChild;
					}
					else if(current == parent.leftChild)
					{
						parent.leftChild = current.rightChild;
					}
					else
					{
						parent.rightChild = current.rightChild;
					}
				}
				else //Case 4: Two children
				{
					//Set successor tracker nodes
					Node successor = current.rightChild;
					Node successorParent = null;
					
					//Find in-order successor of node to be deleted
					while(successor.leftChild != null)
					{
						successorParent = successor;
						successor = successor.leftChild;
					
					}//end while
					
		             // If successor is not the immediate right child
	                if (successorParent != null) 
	                {
	                    successorParent.leftChild = successor.rightChild;
	                    successor.rightChild = current.rightChild;
	                }//end if

	                successor.leftChild = current.leftChild;

	                // Replace node to be deleted with successor
	                if (current == root) 
	                {
	                    root = successor;
	                } 
	                else if (parent.leftChild == current) 
	                {
	                    parent.leftChild = successor;
	                } 
	                else 
	                {
	                    parent.rightChild = successor;
	                }//end if-else if-else
					
				}//end if-else if- else if-else
				
				System.out.println("\n" + name + " is deleted from binary search tree.");
				return;
			}
			else
			{
				parent = current; 
				
				if(name.compareTo(current.countryName) < 0)
				{
					current = current.leftChild;
				}
				else
				{
					current = current.rightChild;
				}//end inner if-else
			
			}//end outer if-else
			
		}//end while
		
		//Display message if the country was not found
		if(!found)
		{
			System.out.println("Country not found in binary tree. No country deleted.");
		}
		
	}//end delete
	
	/**
	 * Print all country nodes using in-order traversal
	 */
	public void printInorder() 
	{
		System.out.println("\nInorder traversal:");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		traverseInorder(root);
		
	}//end printInorder
	
	/**
	 * Recursive helper method to traverse and print all country nodes using in-order traversal
	 * 
	 * @param localRoot The root of the tree/subtree
	 */
	private void traverseInorder(Node localRoot)
	{
		if(localRoot == null)
		{
			return;
		}
		
		traverseInorder(localRoot.leftChild);
		localRoot.printNode();
		traverseInorder(localRoot.rightChild);
		
	}//end traverseInorder
	
	/**
	 * Print all country nodes using pre-order traversal
	 */
	public void printPreorder()
	{
		System.out.println("\nPreorder traversal:");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		traversePreorder(root);
		
	}//end printPreorder
	
	/**
	 * Recursive helper method to traverse and print all country nodes using pre-order traversal
	 * 
	 * @param localRoot The root of the tree/subtree
	 */
	private void traversePreorder(Node localRoot)
	{
		if(localRoot == null)
		{
			return;
		}
		
		localRoot.printNode();
		traverseInorder(localRoot.leftChild);
		traverseInorder(localRoot.rightChild);
		
	}//end traversePreorder
	
	/**
	 * Print all country nodes using post-order traversal
	 */
	public void printPostorder()
	{
		System.out.println("\nPostorder traversal:");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		traversePostorder(root);
		
	}//end printPostorder
	
	/**
	 * Recursive helper method to traverse and print all country nodes using post-order traversal
	 * 
	 * @param localRoot The root of the tree/subtree
	 */
	private void traversePostorder(Node localRoot)
	{
		if(localRoot == null)
		{
			return;
		}
		
		traversePostorder(localRoot.leftChild);
		traversePostorder(localRoot.rightChild);
		localRoot.printNode();
		
	}//end traversePreorder
	
	/**
	 * Find and print in ascending order the bottom c countries regarding happiness
	 * 
	 * @param c The number of countries to find and print
	 */
	public void printBottomCountries(int c)
	{
		//Local variable declarations
		double lastMin = -1;
		Node currentMin;
		
		//Display header
		System.out.println("Bottom " + c + " countries regarding happiness: \n");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		//Find and print next minimum happiness country data
		for(int i = 0; i < c; i++)
		{
			currentMin = root;
			currentMin = findNextMin(root, currentMin, lastMin);
			currentMin.printNode();
			lastMin = currentMin.happiness;
		}//end for
		
	}//end printBottomCountries
	
	/**
	 * Helper function to traverse tree and find country node with next minimum happiness value
	 * 
	 * @param localRoot The root of the tree/subtree
	 * @param currentMin The country node with current minimum happiness value
	 * @param lastMin The last minimum happiness value
	 * @return currentMin The country node with current minimum happiness value
	 */
	private Node findNextMin(Node localRoot, Node currentMin, double lastMin)
	{
		//Check if node is null
		if(localRoot == null)
		{
			return currentMin;
		}
		
		//Find county node with next minimum value
		currentMin = findNextMin(localRoot.leftChild, currentMin, lastMin);
		currentMin = findNextMin(localRoot.rightChild, currentMin, lastMin);
		
		if(localRoot.happiness > lastMin && localRoot.happiness < currentMin.happiness)
		{
			currentMin = localRoot;
		}
		
		return currentMin;
	
	}//end findNextMin
	
	
	/**
	 * Find and print in descending order the top c countries regarding happiness
	 * 
	 * @param c The number of countries to find and print
	 */
	public void printTopCountries(int c) 
	{
		//Local variable declarations
		double lastMax = 100;
		Node currentMax;
		
		//Display header
		System.out.println("Top " + c + " countries regarding happiness: \n");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		//Find and print next max happiness country data
		for(int i = 0; i < c; i++)
		{
			currentMax = root;
			currentMax = findNextMax(root, currentMax, lastMax);
			currentMax.printNode();
			lastMax = currentMax.happiness;
		}//end for
		
	}//end printTopCountries
	
	/**
	 * Helper function to traverse tree and find country node with next max happiness value
	 * 
	 * @param localRoot The root of the tree/subtree
	 * @param currentMax The country node with current max happiness value
	 * @param lastMax The last max happiness value
	 * @return currentMin The country node with current max happiness value
	 */
	private Node findNextMax(Node localRoot, Node currentMax, double lastMax)
	{
		//Check if node is null
		if(localRoot == null)
		{
			return currentMax;
		}
		
		//Find county node with next max value
		currentMax = findNextMax(localRoot.leftChild, currentMax, lastMax);
		currentMax = findNextMax(localRoot.rightChild, currentMax, lastMax);
		
		if(localRoot.happiness < lastMax && localRoot.happiness > currentMax.happiness)
		{
			currentMax = localRoot;
		}
		
		return currentMax;
		
	}//end findNextMax
	
}//end BinarySearchTree class
