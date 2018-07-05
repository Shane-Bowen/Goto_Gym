/**
 * Name: Shane Bowen
 * ID: R00149085
 */
import java.io.*;
import java.util.Scanner;
public class GotoGymShaneBowen {

	final static Scanner kb = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException
	{

		final int MAX_NO_OF_CLASSES = 12; //This is the max number of classes

		String[] classTypes = new String[MAX_NO_OF_CLASSES];

		loadClassTypes(classTypes);//storing the class types in the main 

		final int NO_OF_INSTRUCTORS = 4; //This is the total number of instructors
		String[] instructorsNames = new String[NO_OF_INSTRUCTORS];

		loadInstructors(instructorsNames);//storing the instructor names in the main

		String[] classCodes = new String[MAX_NO_OF_CLASSES];//storing the class codes in the main

		String[] classDays = new String[MAX_NO_OF_CLASSES];//storing the class days in the main

		int [] classTimes = new int[MAX_NO_OF_CLASSES];//storing the class times in the main

		String[] classInstructors = new String[MAX_NO_OF_CLASSES];//storing the class instructors in the main

		int noOfClasses = loadClassDetails(classCodes, classDays, classTimes, classInstructors ); 

		String[] daysList = {"Mon", "Tues", "Wed"}; //a list of all the days in order
		int[] timesList = {7, 8, 9}; //a list of all the times in order
		String[] codesList = {"PI", "YO", "SP"}; //list of all the codes in order

		boolean exit = false;

		while (exit == false) {
			System.out.println("1. Add a Class Session"); 
			System.out.println("2. Show Times all Classes"); 
			System.out.println("3. Show Instructor Payments Due"); 
			System.out.println("4. Print TimeTable for Instructor"); 	
			System.out.println("5. Show Ordered TimeTable with Codes"); 
			System.out.println("6. Exit"); 

			int choice = kb.nextInt();
			kb.nextLine();
			switch (choice) {

			case 1: //if the user types 1 then the user will add a class
				printTitleAddAClass ();
				noOfClasses = addAClass(instructorsNames, timesList, daysList, codesList, classTypes, noOfClasses, MAX_NO_OF_CLASSES, classCodes, classDays, classTimes, classInstructors );
				break;
			case 2: //if the user types 2 then it will show the times of all classes
				printTitleClasses();
				showTimetable(classCodes, classDays, classTimes, classInstructors, classTypes, codesList );
				break;
			case 3: //if the user types 3 then it will show the instructor payments due
				printTitlePayments();
				printPayments(classTimes, classInstructors, instructorsNames, noOfClasses, MAX_NO_OF_CLASSES );
				break;
			case 4://if the user types 4 then it will print the timetable for the instructor 
				printTitleTimetable(); 
				printTimetable(classCodes, classDays, classTimes, classInstructors, noOfClasses, MAX_NO_OF_CLASSES );
				break;
			case 5://if the user types 5 then it will show the ordered timetable with codes
				printTitleOrderedTimetable();
				printOrderedTimetable(daysList, timesList, MAX_NO_OF_CLASSES, classCodes, classDays, classTimes);
				break;
			case 6: //if the user types 6 then it will exit the program
				exit = true;
				break;
			}
			System.out.println("\nPress Return to continue");
			kb.nextLine();
		}
	}

	public static int loadClassTypes (String[] classTypeList) throws FileNotFoundException //this method loads the class types
	{

		File file = new File("ClassTypes.txt");
		Scanner inputFile = new Scanner(file);

		int i=0;

		while (inputFile.hasNext())
		{
			String classTypes = inputFile.nextLine();

			classTypeList[i] = classTypes;

			i++;
		}

		inputFile.close();

		return i;

	}

	public static int loadInstructors (String[] instructorsNames) throws FileNotFoundException //this method load the instructors
	{

		File file = new File("Instructors.txt");
		Scanner inputFile = new Scanner(file);

		int i=0;

		while (inputFile.hasNext())
		{
			String instructors = inputFile.nextLine();

			instructorsNames[i] = instructors;

			i++;
		}

		inputFile.close();

		return i;

	}

	public static int loadClassDetails(String[] classCodes, String[] classDays, int[] classTimes, String[] classInstructors) throws FileNotFoundException //this method loads the class details
	{
		File file = new File("ClassDetails.txt");
		Scanner inputFile = new Scanner(file);

		int i=0;

		while (inputFile.hasNext())
		{
			String classCode = inputFile.nextLine();
			classCodes[i] = classCode;

			String classDay = inputFile.nextLine();
			classDays[i] = classDay;

			int classTime = inputFile.nextInt();
			inputFile.nextLine();
			classTimes[i] = classTime;

			String classInstructor = inputFile.nextLine();
			classInstructors[i] = classInstructor;

			i++;
		}
		inputFile.close();
		return i;
	}

	public static int addAClass (String[] instructorsNames, int[] timesList, String[] daysList, String[] codesList, String[] classTypes, //this methods adds a class
			int noOfClasses, final int MAX_NO_OF_CLASSES, String[] classCodes, String[] classDays, int[] classTimes, String[] classInstructors)

	{
		int i;
		if(noOfClasses<MAX_NO_OF_CLASSES)
		{	
			System.out.println("Enter the class Type:");//ask the user for the class type
			for(i=0; i < classTypes.length; i++)
			{	
				if( classTypes[i] != null )
				{
					System.out.println((i+1) + ". " + classTypes[i]);
				}	
			}
			int classChoice = kb.nextInt(); //store their choice in class choice

			classCodes[noOfClasses] = codesList[classChoice-1] ;

			System.out.println("Enter the Day:");//ask the user for the day
			for(i=0; i < daysList.length; i++)
			{
				if( daysList[i] != null )
				{
					System.out.println((i+1) + ". " + daysList[i]);
				}	
			}
			int dayChoice = kb.nextInt(); //store their day choice

			classDays[noOfClasses] = daysList[dayChoice-1];

			System.out.println("Choose a Time: ");//ask the user for the time
			for(i=0; i < timesList.length; i++)
			{
				if( timesList[i] != 0 )
				{
					System.out.println((i+1) + ". " + timesList[i] + " o'clock");
				}	
			}
			int timeChoice = kb.nextInt(); //store their time choice

			classTimes[noOfClasses] = timesList[timeChoice-1];

			System.out.println("Choose a Teacher: ");//ask the user to choose a teacher
			for(i=0; i < instructorsNames.length; i++)
			{
				if( instructorsNames[i] != null )
				{
					System.out.println((i+1) + ". " + instructorsNames[i]);
				}	
			}
			int teacherChoice = kb.nextInt(); //store teacher choice

			classInstructors[noOfClasses] = instructorsNames[teacherChoice-1];

			System.out.println("Added " + codesList[classChoice-1] + " on " + daysList[dayChoice-1] + " at " + timesList[timeChoice-1] + " with " + instructorsNames[teacherChoice-1]);

		}

		else if (noOfClasses>=MAX_NO_OF_CLASSES)//if the no. of classes is greater than the Max number of class then it will read no more classes can be added
		{
			System.out.println("No more classes can be added");
		}

		noOfClasses ++;

		return noOfClasses;
	}


	private static void showTimetable(String[] classCodes, String[] classDays, int[] classTimes, String[] classInstructors, String[] classTypes, String[] codesList) //this method shows the timetable
	{
		System.out.println(classTypes[0]);
		for(int i = 0; i < classCodes.length; i++)
		{
			if( classCodes[i] != null )
			{
				if( classCodes[i].equals(codesList[0]))
				{	
					System.out.println("\t" + classDays[i] + " at " + classTimes[i] + " with " + classInstructors[i]);
				}
			}
		}

		System.out.println(classTypes[1]);
		for(int i = 0; i < classCodes.length; i++)
		{
			if( classCodes[i] != null )
			{
				if( classCodes[i].equals(codesList[1]))
				{	
					System.out.println("\t" + classDays[i] + " at " + classTimes[i] + " with " + classInstructors[i]);
				}
			}
		}

		System.out.println(classTypes[2]);
		for(int i = 0; i < classCodes.length; i++)
		{
			if( classCodes[i] != null )
			{
				if( classCodes[i].equals(codesList[2]))
				{	
					System.out.println("\t" + classDays[i] + " at " + classTimes[i] + " with " + classInstructors[i]);
				}
			}
		}

	}

	private static void printPayments(int[] classTimes, String[] classInstructors, String[] instructorsNames, int noOfClasses, final int MAX_NO_OF_CLASSES)//this method prints the payments
	{
		final int COST_FROM_7TO8 = 60; //Amount the instructor gets paid for working 7 and 8
		final int COST_AT_9 = 80; //Amount the instructor gets paid for working at 9
		final int BONUS = 10;
		final int MIN_NUM_CLASSES = 2;

		int payForAmanda = 0; //Initialize the pay for all the instructors
		int payForGerry = 0;
		int payForAidan = 0;
		int payForFrank = 0;

		int totalPay = 0; //Initialize the total pay

		int noOfClassesAmanda = 0; //Initialize the no. of classes for the instructors
		int noOfClassesGerry = 0;
		int noOfClassesAidan = 0;
		int noOfClassesFrank = 0;

		System.out.println("Name \tClasses \tPay \n---- \t------- \t---");

		for (int i=0; i<MAX_NO_OF_CLASSES; i++)
		{
			if (classInstructors[i] != null)
			{
				if( classInstructors[i].equals(instructorsNames[0]))
				{
					if ( classTimes[i] == 7 || classTimes[i] == 8)
					{
						payForAmanda += COST_FROM_7TO8;
						noOfClassesAmanda ++;
					}

					else if ( classTimes[i] == 9)
					{
						payForAmanda += COST_AT_9;
						noOfClassesAmanda ++;
					}

					if ( noOfClassesAmanda > MIN_NUM_CLASSES)
					{
						payForAmanda = payForAmanda + (BONUS*(i+1));
					}
				}	

				else if (classInstructors[i].equals(instructorsNames[1]))
				{
					if ( classTimes[i] == 7 || classTimes[i] == 8)
					{
						payForGerry += COST_FROM_7TO8;
						noOfClassesGerry ++;
					}

					else if ( classTimes[i] == 9)
					{
						payForGerry +=COST_AT_9;
						noOfClassesGerry ++;
					}
				}

				else if (classInstructors[i].equals(instructorsNames[2]))
				{
					if ( classTimes[i] == 7 || classTimes[i] == 8)
					{
						payForAidan += COST_FROM_7TO8;
						noOfClassesAidan ++;
					}

					else if ( classTimes[i] == 9)
					{
						payForAidan += COST_AT_9;
						noOfClassesAidan ++;
					}
				}

				else if (classInstructors[i].equals(instructorsNames[3]))
				{
					if ( classTimes[i] == 7 || classTimes[i] == 8)
					{
						payForFrank += COST_FROM_7TO8;
						noOfClassesFrank ++;
					}

					else if ( classTimes[i] == 9)
					{
						payForFrank += COST_AT_9;
						noOfClassesFrank ++;
					}

				}
			}
		}

		totalPay = payForAmanda + payForGerry + payForAidan + payForFrank;
		System.out.println(instructorsNames[0] + "\t" + noOfClassesAmanda + "\t\t" + payForAmanda );
		System.out.println(instructorsNames[1] + "\t" + noOfClassesGerry + "\t\t" + payForGerry );
		System.out.println(instructorsNames[2] + "\t" + noOfClassesAidan + "\t\t" + payForAidan );
		System.out.println(instructorsNames[3] + "\t" + noOfClassesFrank + "\t\t" + payForFrank );

		System.out.println("\nTotal Paybill: " + totalPay);
	}

	private static boolean checkName(String name) //this method checks the name the user enters for print timetable to make sure it's valid
	{
		for(int i = 0; i < name.length(); i++)
		{
			if(Character.isDigit(name.charAt(i)))
			{
				return false;
			}

		}
		return true;

	}
	private static void printTimetable(String [] classCodes, String [] classDays, int[] classTimes, String[] classInstructors, int noOfClasses, final int MAX_NO_OF_CLASSES ) throws FileNotFoundException //this method prints a timetable
	{
		System.out.print("Please enter a name:");

		String name = kb.nextLine();
		String instructorName = "";
		if(checkName(name))
		{
			instructorName = name;

			PrintWriter outputFile = new PrintWriter(instructorName + "sTimetable.txt");

			System.out.println("\n" + instructorName + "sTimetable.txt Created \nWriting to File: \n");

			for(int i=0; i<MAX_NO_OF_CLASSES; i++)
			{
				if(classInstructors[i] != null)
				{
					if(instructorName.equalsIgnoreCase(classInstructors[i]))
					{
						System.out.println(classCodes[i] + "\t" + classDays[i] + " at " + classTimes[i] );
						outputFile.println(classCodes[i] + "\t" + classDays[i] + " at " + classTimes[i]);
					}

				}

			}

			if(!instructorName.equalsIgnoreCase(classInstructors[0]) && !instructorName.equalsIgnoreCase(classInstructors[1]) && !instructorName.equalsIgnoreCase(classInstructors[2]) && !instructorName.equalsIgnoreCase(classInstructors[3]) 
					&& !instructorName.equalsIgnoreCase(classInstructors[4]) && !instructorName.equalsIgnoreCase(classInstructors[5]) && !instructorName.equalsIgnoreCase(classInstructors[6]) && !instructorName.equalsIgnoreCase(classInstructors[7]) 
					&& !instructorName.equalsIgnoreCase(classInstructors[8]) && !instructorName.equalsIgnoreCase(classInstructors[9]) && !instructorName.equalsIgnoreCase(classInstructors[10]) && !instructorName.equalsIgnoreCase(classInstructors[11]))
			{
				System.out.println("No classes assigned this week. ");
				outputFile.println("No classes assigned this week. ");
			}

			outputFile.close();
		} 

		else
		{
			System.out.println("\nName can't contain numbers");
		}

	}

	public static void printOrderedTimetable(String[] daysList, int[] timesList, final int MAX_NO_OF_CLASSES, String[] classCodes, String[] classDays, int[] classTimes) //this method shows the ordered timetable
	{
		System.out.print(daysList[0] + ":");

		System.out.print("\n\t" + timesList[0]);
		for(int i=0; i<classDays.length; i++)
		{
			if(classDays[i] != null)
			{
				if(classDays[i].equals(daysList[0]) && classTimes[i] == (timesList[0]))
				{	
					System.out.print("\t" + classCodes[i]);
				}
			}	
		}

		System.out.print("\n\t" + timesList[1]);
		for(int i=0; i<classDays.length; i++)
		{
			if(classDays[i] != null)
			{
				if (classDays[i].equals(daysList[0]) && classTimes[i] == (timesList[1]))
				{
					System.out.print("\t" + classCodes[i]);
				}
			}	
		}

		System.out.print("\n\t" + timesList[2]);
		for(int i=0; i<classDays.length; i++)
		{
			if(classDays[i] != null)
			{
				if (classDays[i].equals(daysList[0]) && classTimes[i] == (timesList[2]))
				{
					System.out.print("\t" + classCodes[i]);
				}	
			}	
		}

		System.out.print("\n" + daysList[1] + ":");

		System.out.print("\n\t" + timesList[0]);
		for(int k =0; k<classDays.length; k++)
		{
			if(classDays[k] != null)
			{
				if(classDays[k].equals(daysList[1]) && classTimes[k] == (timesList[0]))
				{	
					System.out.print("\t" + classCodes[k]);
				}
			}
		}

		System.out.print("\n\t" + timesList[1]);
		for(int k =0; k<classDays.length; k++)
		{
			if(classDays[k] != null)
			{
				if (classDays[k].equals(daysList[1]) && classTimes[k] == (timesList[1]))
				{
					System.out.print("\t" + classCodes[k]);
				}
			}
		}

		System.out.print("\n\t" + timesList[2]);
		for(int k =0; k<classDays.length; k++)
		{
			if(classDays[k] != null)
			{
				if (classDays[k].equals(daysList[1]) && classTimes[k] == (timesList[2]))
				{
					System.out.print("\t" + classCodes[k]);
				}
			}
		}

		System.out.print("\n" + daysList[2] + ":");

		System.out.print("\n\t" + timesList[0]);
		for(int f=0; f<classDays.length; f++)
		{
			if(classDays[f] != null)
			{
				if(classDays[f].equals(daysList[2]) && classTimes[f] == (timesList[0]))
				{	
					System.out.print("\t" + classCodes[f]);
				}
			}
		}

		System.out.print("\n\t" + timesList[1]);
		for(int f=0; f<classDays.length; f++)
		{
			if(classDays[f] != null)
			{
				if (classDays[f].equals(daysList[2]) && classTimes[f] == (timesList[1]))
				{
					System.out.print("\t" + classCodes[f]);
				}
			}
		}	

		System.out.print("\n\t" + timesList[2]);
		for(int f=0; f<classDays.length; f++)
		{
			if(classDays[f] != null)
			{
				if (classDays[f].equals(daysList[2]) && classTimes[f] == (timesList[2]))
				{
					System.out.print("\t" + classCodes[f]);
				}	
			}	
		}
	}	

	private static void printTitleClasses() //this method prints the title for classes
	{
		System.out.println("The Goto Gym Classes ");
		System.out.println("-------------------------\n");
	}

	private static void printTitlePayments() //this methods prints the title for payments
	{
		System.out.println("The Goto Gym Payments Due");
		System.out.println("--------------------------\n");
	}

	private static void printTitleTimetable() //this method prints the title for timetables
	{
		System.out.println("The Goto Gym Titmetables");
		System.out.println("-------------------------\n");
	}

	private static void printTitleAddAClass () //this method prints the title for add a class
	{
		System.out.println("The Goto Gym Add a Class");
		System.out.println("-------------------------\n");
	}

	private static void printTitleOrderedTimetable() //this method prints the title for a ordered timetable
	{
		System.out.println("The Goto Gym Ordered Timetable");
		System.out.println("-------------------------------\n");
	}
}