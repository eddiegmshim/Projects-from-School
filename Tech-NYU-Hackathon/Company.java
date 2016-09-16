import java.util.ArrayList;


public class Company {

	double SD;
	double mean;
	double[] returns;
	String name;
	String [] Date;
	double[] price;

	public Company(String Name, ArrayList <String> Date ,ArrayList <Float> Price) {
		this.name = Name;
		this.price = new double [Price.size()];
		for (int i = 0; i< Price.size(); i++){
			price[i] = Price.get(i);
		}
		this.returns = Methods.createReturnsData(this.price);
		this.mean = Methods.calculateMean(this.returns);
		this.SD = Methods.calculateSD(this.returns, this.mean);
	}






}
