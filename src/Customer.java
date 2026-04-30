
public abstract class Customer extends Person{
	public Customer(String name, double balance) {
		super(name,balance);
	}
	public Customer(String name) {
		super(name);
	}
	public abstract double getDiscount();
}
