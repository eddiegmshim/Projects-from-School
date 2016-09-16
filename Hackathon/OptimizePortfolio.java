public class OptimizePortfolio {
	//given 2x2 covariance inverse matrix with identity matrix u, where u is [1,1]T
	//formula for 2x2 MVP weights 
		// w = (SIGMA(-1)u)/(uTSIGMA(-1)u)
	
	
	
	
	
	public static double[][] weightMultiplier(double covarianceInverse[][]){
		int size = 2;
		
		double [][] uMatrix =new double  [size][1];
		double [][] uMatrixTranspose = new double [1][size];
		double [][] weights = new double [size][1];
		double [][] numerator = new double [size][1];
		double denominator = 0;
		

		for (int i=0; i<= size-1; i++){
			
				uMatrix[i][0]=1;
				uMatrixTranspose [0][i]=1;
				
				for (int k=0; k <= size-1 ; k++){
					numerator[i][0]+= covarianceInverse[i][k] * 1;
				
				}
		}
		
//		for (int i=0; i<= size-1; i++){
//			System.out.println(numerator[i][0]);
//		}
//		
		for (int i=0; i<= size-1; i++)
		{
			denominator += 1* numerator [i][0];
			
		}
		for (int i=0; i<= size-1; i++){
			weights[i][0]= numerator[i][0]/denominator;
		}
		

		return weights;
		
	}
	
}