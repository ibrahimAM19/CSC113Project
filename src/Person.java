
public abstract class Person   implements Wallet{
	protected String name;
	protected double balance;
	public Person(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	public Person(String name) {
		this(name,0);
	}
	public void addMoney(double m) {
		balance += m;
	}
	@Override
	public String toString() {
		return "name: " + name + ", balance: " + balance;
	}
	public boolean withdraw(double m) {
		if (m <= this.balance) {
			this.balance -= m;
			System.out.println("withdraw is succcesful ");
			return true;
		}
		else {
			System.out.println(" the amount is large that balance");
			return false;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	

}
