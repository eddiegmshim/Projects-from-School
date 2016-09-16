public class Methods {
	
	public static double[] createReturnsData(double [] price){
		double [] returns = new double[price.length-1];
		for (int i =0; i<price.length-1; i++){
			returns[i] = (price[i+1] - price[i])/price[i];
		}
		
		return returns;
	}
	
	
	public static double calculateMean(double[] returns){
		double totalSum = 0;
		for (int i = 0; i<returns.length; i++){
			totalSum += returns[i];	
		}
		
		double mean = totalSum/returns.length;
		return mean;
	}
	
	public static double calculateSD(double[] returns, double mean){
		
		double variance =0;
		for (int i =0; i<returns.length; i++){
			variance += (returns[i]-mean)*(returns[i]-mean);
		}
		
		variance = variance/ (returns.length-1);
		
		
		double SD = Math.sqrt(variance);
		return SD;
	}
	
	public static double[][] covarianceMatrix (Company Company1, Company Company2){
		double company1SD = Company1.SD;
		double company2SD = Company2.SD;
		double company1Mean = Company1.mean;
		double company2Mean = Company2.mean;
		double [] company1Returns = Company1.returns;
		double [] company2Returns = Company2.returns;
		
		double cov = 0;
		for (int i =0; i<company1Returns.length; i++){
			cov += (company1Returns[i] - company1Mean) * (company2Returns[i] - company2Mean);
		}
		cov = cov/(company1Returns.length-1);
		
		double [][] covarianceMatrix = new double[2][2];
		covarianceMatrix[0][0] = Company1.SD*Company1.SD;
		covarianceMatrix[1][1] = Company2.SD*Company2.SD;
		covarianceMatrix[1][0] = cov; 
		covarianceMatrix[0][1] = cov; 
		
		
		//System.out.println(company1SD)
		
		return covarianceMatrix;
	}
	
	public static double[][] inverseCovMatrix (double[][] covarianceMatrix){
		double [][] inverseCovMatrix = new double [2][2];
		
		double determinant = 1/((covarianceMatrix[0][0] * covarianceMatrix[1][1]) - (covarianceMatrix[0][1] * covarianceMatrix[1][0]));
		

		
		inverseCovMatrix[0][0] = determinant * covarianceMatrix[1][1];
		inverseCovMatrix[0][1] = determinant * -covarianceMatrix[0][1];		
		inverseCovMatrix[1][1] = determinant *covarianceMatrix[0][0];
		inverseCovMatrix[1][0] = determinant * -covarianceMatrix[1][0];

		return inverseCovMatrix;
	}
	

	

}
