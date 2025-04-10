//class comment
public class BinarySearchTree 
{
	Node root;
	
	//constructor comment
	public BinarySearchTree()
	{
		root = null;
	}//end BinarySearchTree
	
	//method comment
	public void insert(String name, double happiness)
	{
		//Create new country node and set variables
		Node newCountry = new Node();
		newCountry.countryName = name;
		newCountry.happiness = happiness;
		
		//Check if tree is empty
		if(root == null)
		{
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
				
				//Traverse left or right on tree based on if new country's name is lexicographically smaller than current country's
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
	
	//method comment
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
	
	//method comment
	public void delete(String name)
	{
		//Local variable declarations
		Node current = root;
		Node parent = null;
		boolean found = false;
		
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
		
		if(!found)
		{
			System.out.println("Country not found in binary tree. No country deleted.");
		}
		
	}//end delete
	
	//method comment
	public void printInorder() 
	{
		System.out.println("\nInorder traversal:");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		traverseInorder(root);
		
	}//end printInorder
	
	//method comment
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
	
	//method comment
	public void printPreorder()
	{
		System.out.println("\nPreorder traversal:");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		traversePreorder(root);
		
	}//end printPreorder
	
	//method comment
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
	
	//method comment
	public void printPostorder()
	{
		System.out.println("\nPostorder traversal:");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		
		traversePostorder(root);
		
	}//end printPostorder
	
	//method comment
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
	
	public void printBottomCountries(int c)
	{
		System.out.println("Bottom " + c + " countries regarding happiness: \n");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		

	}//end printBottomCountries
	
	public void printTopCountries(int c) 
	{
		System.out.println("Top " + c + " countries regarding happiness: \n");
		System.out.println("Name                               Happiness");
		System.out.println("-------------------------------------------------");
		

		
		
	}//end printTopCountries
	
}//end BinarySearchTree class
