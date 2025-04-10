//class comment

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project4
{
	/**
	 * Main method 
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args)
	{
		//Local variable declarations
		Scanner scnr = new Scanner(System.in);
		BinarySearchTree countryBST = new BinarySearchTree();
		File countryFile = null;
		Scanner fscnr = null;
		int numCountries = 0;
		String name;
		double happiness;
		int userChoice = 0;
		
		//Display project title
		System.out.println("COP3530 Project 4\n");
		
		do
		{
			//Get file name
			System.out.println("Enter the file name: ");
			String fileName = scnr.next();
			
			//Find file and create file scanner
			try
			{
				countryFile= new File(fileName);
				fscnr = new Scanner(new File(fileName));
			}
			catch(FileNotFoundException e)
			{
				System.out.println("File not found. Try again.\n");
				continue;
			}//end try-catch
			
		}while (!countryFile.exists()); //end do-while
		
		fscnr.nextLine(); //Skip header line
		
		while(fscnr.hasNext())
		{
			//Reads in each country's data
			String[] line = fscnr.nextLine().split(",");
			name = line[0];
			happiness = Double.parseDouble(line[5]);
			
			//Insert new country into the binary tree
			countryBST.insert(name, happiness);
			numCountries++;	
			
		}//end while
		
		//Display number of country data read
		System.out.println("\nThere were " + numCountries + " records read to build a binary search tree.\n");
		
		do
		{
			//Display menu options
			System.out.println("1. Print tree inorder\n" + 
								"2. Print tree preorder\n" +
								"3. Print tree postorder\n" +
								"4. Insert a country with name and happiness\n" +
								"5. Delete a country for a given name\n" +
								"6. Search a print a country and its path for a given name\n" +
								"7. Print bottom countries regarding happiness\n" +
								"8. Print top countries regarding happiness\n" +
								"9. Exit\n" +
								"Enter your choice: ");
			
			//Get user menu choice and validate the input
			if(scnr.hasNextInt())
			{ 
				userChoice = scnr.nextInt();
				scnr.nextLine();
					
				if(userChoice >= 1 && userChoice <= 9)
				{
					//Call corresponding methods or end program based on user menu choice
					switch(userChoice)
					{
						case 1: 
							countryBST.printInorder();
							break;
						
						case 2:
							countryBST.printPreorder();
							break;
						
						case 3:
							countryBST.printPostorder();
							break;
						
						case 4:
							System.out.println("Enter country name: ");
							name = scnr.nextLine();
							
							System.out.println("Enter country happiness: ");
							happiness = scnr.nextDouble();
							
							countryBST.insert(name, happiness);
							System.out.println(name + " with happiness of " + happiness + " is inserted.");
							break;
							
						case 5: 
							System.out.println("Enter country name: ");
							name = scnr.nextLine();
							countryBST.delete(name);
							break;
							
						case 6:
							System.out.println("Enter country name: ");
							name = scnr.nextLine();
							
							if(countryBST.find(name) == -1)
							{
								System.out.println("\n" + name + " is not found.");
							}
							break;
						
						case 7: 
							System.out.println("Enter the number of countries ");
							int a = scnr.nextInt();
							countryBST.printBottomCountries(a);
							break;
						
						case 8:
							System.out.println("Enter the number of countries ");
							int b = scnr.nextInt();
							countryBST.printBottomCountries(b);
							break;
						
						default:
							System.out.println("\nHave a good day!\n");	
							
					}//end switch
				}
				else
				{
					System.out.println("Invalid choice. Enter 1-9.");
				}//end first inner if-else
			}
			else
			{
				scnr.next();
				System.out.println("Invalid choice. Enter 1-9.");
			}//end outer if-else
			
			System.out.println();
			
		}while(userChoice != 9); //end outer do-while
		
		//Close scanners
		fscnr.close();
		scnr.close();
		
	}//end main
}