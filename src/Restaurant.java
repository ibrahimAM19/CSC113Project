import java.util.Arrays;

public class Restaurant implements Wallet {
	private String name;
	private int numOfDish,numOfOrder;
	private double revenue,balance;
	Dish[] menu;
	Order[] listOfOrder;
	public Restaurant(String name,int sizeOfOrder) {
		
		this.name = name;
		this.numOfDish = 0;
		this.numOfOrder = 0;
		this.revenue = 0;
		this.balance = 0;
		menu = new Dish[10];
		listOfOrder = new Order[sizeOfOrder];
	}
	public Restaurant(Restaurant  r) {
		
		this.name = r.name;
		this.numOfDish = r.numOfDish;
		this.numOfOrder = r.numOfOrder;
		this.revenue = r.revenue;
		this.balance = r.balance;
		
		this.listOfOrder = new Order[r.listOfOrder.length];
		for (int i = 0; i< this.numOfOrder; i++) 
			this.listOfOrder[i] = r.listOfOrder[i];
		this.menu = new Dish[r.menu.length];
		for (int i =0; i<this.numOfDish;i++)
			this.menu[i] = r.menu[i];
	}
	public void addMoney(double r) {
		revenue += r;
		balance+=r;
	}
	public boolean withdraw(double cash) {
		if (cash > balance) {
			System.out.println("Error: the number is lagre that the balance ");
			return false;
		}
		else {
			balance -=cash;
			System.out.println("withdraw successful");
			return true;
		}
		
	}
	public boolean addDish(Dish d) {
		if (numOfDish < menu.length) {
			menu[numOfDish++] = d;
			
			return true;
		}
		else {
			System.out.println("you can`t add anymore dishs");
			return false;
		}
	}
	// return a dish by indexing
	public Dish getDish(int i) {
		if( i < this.menu.length)
			return this.menu[i];
		else {
			
			return null;
		}
	}
	public boolean addOrder(Order o) {
		if (this.numOfOrder < this.listOfOrder.length) {
			this.listOfOrder[this.numOfOrder++] = o;
			return true;
		}
		else {
			System.out.println("you can`t add anymore orders");
			return false;
		}
	}
	public void displayMenu() {
		System.out.println(this.name + " menu");
		for (int i =0 ; i<numOfDish;i++) {
			System.out.println( i+1 + "-"+menu[i]);
		}
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Restuarant name:" + name + ", balance:" + String.format("%.2f", balance)+  ", revenue:"+ String.format("%.2f",revenue) ;
	}
	
	
}
