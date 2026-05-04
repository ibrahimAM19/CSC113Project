import java.io.Serializable;
import java.util.Arrays;

public class Restaurant implements Wallet ,Serializable{
	private String name;
	
	private double revenue,balance;
	
	private List menu;
	private List listOfOrder;
	public Restaurant(String name) {
		
		this.name = name;
		this.revenue = 0;
		this.balance = 0;
		menu = new List();
		listOfOrder = new List();
	}
	public Restaurant(Restaurant  r) {
		
		this.name = r.name;
		this.revenue = r.revenue;
		this.balance = r.balance;
		
		listOfOrder = new List();
		Node current = r.listOfOrder.getHead();
		while(current != null) {
			listOfOrder.insertAtBack(current.getData());
			current = current.getNext();
		}
		
		menu = new List();
		 current = r.menu.getHead();
		while(current != null) {
			menu.insertAtBack(current.getData());
			current = current.getNext();
		}
			
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
		menu.insertAtBack(d);
		return true;
		
	}
	// return a dish by indexing
	public Dish getDish(int i) {
		return (Dish)menu.getElementAt(i);
	}
	public boolean addOrder(Order o) {
		listOfOrder.insertAtFront(o);
		return true;
	}
	
	public void displayMenu() {
		System.out.println(this.name + " menu");
		menu.print();
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Restuarant name:" + name + ", balance:" + String.format("%.2f", balance)+  ", revenue:"+ String.format("%.2f",revenue) ;
	}
	
	
}
