
public class Order {
	private Restaurant restaurant;
	private Customer customer;
	private Driver driver;
	private double totalPrice;
	private Dish[] listOfDishes;
	private int numOfDishes;
	public Order(Restaurant r,Customer c,Driver driver) {
		this.restaurant = r;
		this.customer = c;
		this.driver = driver;
		this.totalPrice = 0;
		this.listOfDishes = new Dish[10];
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
		if (numOfDishes < listOfDishes.length) {
			listOfDishes[numOfDishes++] = d;
			this.totalPrice += d.getPrice() *this.customer.getDiscount();
			return true;
		}
		else {
			System.out.println("you can`t add anymore dishs");
			return false;
		}
	}
	
	public String toString() {
		String s = "----------------------\n";
		 s += "Order, restuarant name: "+ this.restaurant.getName() +", deleverd to: "+ this.customer.getName();
		for (int i =0; i< this.numOfDishes;i++) {
			s += "\n" + this.listOfDishes[i];
		}
		s += "\ntotal price: " + this.totalPrice;
		s += "\n----------------------";
		return s;
	}
	
	

}
