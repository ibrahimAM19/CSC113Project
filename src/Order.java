import java.io.Serializable;

public class Order implements Serializable {
	private Restaurant restaurant;
	private Customer customer;
	private Driver driver;
	private double totalPrice;
	private List listOfDishes;
	
	private int numOfDishes;
	public Order(Restaurant r,Customer c,Driver driver) {
		this.restaurant = r;
		this.customer = c;
		this.driver = driver;
		this.totalPrice = 0;
		this.listOfDishes = new List();
		this.numOfDishes =0;
		driver.setOrderToDelver(this); 
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public Driver getDriver() {
		return driver;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public int getNumOfDishes() {
		return numOfDishes;
	}
	
	// add dish to the order and discout by 15% if the customer was premium 
	public boolean addDish(Dish d) {

		listOfDishes.insertAtBack(d);
		totalPrice += d.getPrice() * this.customer.getDiscount();
		numOfDishes++;
		return true;
	}
	
	public String toString() {
		String s = "----------------------\n";
		 s += "Order, restuarant name: "+ this.restaurant.getName() +", deleverd to: "+ this.customer.getName();
		
		 Node current = listOfDishes.getHead();
		 while(current !=  null) {
			 s += "\n" + current.getData().toString();
			 current  = current.getNext();
		 }
		s += "\ntotal price: " + this.totalPrice;
		s += "\n----------------------";
		return s;
	}
	
	

}
