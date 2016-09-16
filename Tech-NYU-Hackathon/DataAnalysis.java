import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class DataAnalysis {

	public static ArrayList <Company> Companies = new ArrayList <Company> ();;

	public static void main (String args[])
	{

		// Welcome Message


		// Validates if the dictionary file exists in the command line
		if (args.length < 2)
		{
			System.err.printf("Error: invalid number of arguments. CSV file does not exist in the command line \n \n");
			System.exit(1);
		}


		for (int i = 0; i< args.length; i++)
		{

			// Verifies if the dictionary text file can be read
			File CSVFile = new File(args[i]);
			
			if (!CSVFile.canRead())
			{
				System.err.printf("Error: Unable to read the CSV file %s \n \n", args[0]);
				System.exit(1);
			}

			Scanner Reader = null;
			try
			{
				Reader = new Scanner (CSVFile);
			}
			catch (FileNotFoundException e)
			{
				System.err.printf("Error: Unable to scan and read the dictionary file %s \n \n", args[0]);
				System.exit(1);
			}

			// Read in the company name and instantiate the company object
			String RawFileName = CSVFile.getName();
			String CompanyName = "";

			Scanner tokenizer = new Scanner (RawFileName);

			while (tokenizer.hasNext())
			{

				if (! tokenizer.equals("(") )
					break;

				CompanyName += tokenizer.next();
			}

			tokenizer.close();

			// Reads in all the words into an ArrayList object
			ArrayList<String> Date = new ArrayList<String> ();
			ArrayList<Float> Price = new ArrayList<Float> ();

			while (Reader.hasNext())
			{
				
				//skip the first line
				Reader.nextLine();
								
				String line = Reader.next();
				String [] datasplit = line.split("\",\"");
				
				Date.add(datasplit[0]);
				Price.add(Float.parseFloat(datasplit[1]));
			}

			Companies.add( new Company ( CompanyName, Date, Price ) );

		}
		
		Company Apple = Companies.get(0);
		Company Google = Companies.get(1);

		
		double[][] x = (Methods.inverseCovMatrix(Methods.covarianceMatrix(Apple, Google)));
		
		System.out.printf("Optimal Porfolio Weight for Apple: %2f \n", OptimizePortfolio.weightMultiplier(x)[0][0] * 100);
		System.out.printf("Optimal Porfolio Weight for Google: %2f \n",OptimizePortfolio.weightMultiplier(x)[1][0]* 100);
		
	}
}