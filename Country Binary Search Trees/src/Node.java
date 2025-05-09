/**
 * Stores and prints a country's data
 * 
 * @author Shirley Chen
 * @version 04/10/2025
 */
public class Node 
{
	//Class variable declarations
	String countryName;
	double happiness;
	Node leftChild = null;
	Node rightChild = null;
	
	//Print country name and happiness
	public void printNode()
	{
		System.out.printf("%-35s%-14.3f\n", countryName, happiness);
		
	}//end printNode
	
}//end Node class