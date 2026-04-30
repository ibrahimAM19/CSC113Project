
public class PremiumCustomer  extends Customer{

	public PremiumCustomer(String name, double balance) {
		super(name, balance);
	}
	public PremiumCustomer(String name) {
		super(name);
	}
	public double getDiscount() {
		return 0.85;
	}
	@Override
	public String toString() {
		return "Premium Customer, " +super.toString();
	}
	

}
