
public class AppManager {
	private int numOfRest,numOfCust,numOfDriver;
	private Restaurant[] listOfRest;
	private Customer[] listOfCust;
	private Driver[] listOfDriver;
	public AppManager(int sizeR,int sizeC,int sizeD) {
		listOfRest = new Restaurant[sizeR];
		listOfCust = new Customer[sizeC];
		listOfDriver = new Driver[sizeD];
	}
	public boolean addRest(Restaurant r) {
		if (this.numOfRest < this.listOfRest.length) {
			this.listOfRest[this.numOfRest++] = new Restaurant(r);
			return true;
		}
		else {
			System.out.println("error colud add ");
			return false;
		}
	}
	
	// retrurn an index searched restaurant otherwise return -1 
	public int searchRest(String name) {
		for (int i = 0;i<this.numOfRest;i++) {
			if (name.equals( this.listOfRest[i].getName()) )
				return i;
		}
		return -1;
	}
	public boolean delRest(String name) {
		int restIndex = searchRest(name);
		if (restIndex != -1) {
			this.listOfRest[restIndex] = this.listOfRest[this.numOfRest-1];
			this.listOfRest[this.numOfRest-1] = null;
			this.numOfRest--;
			return true;
		}
		else return false;
		
	}
	public boolean addDriver(Driver d) {
		if (this.numOfDriver < this.listOfDriver.length) {
			this.listOfDriver[this.numOfDriver++] = d;
			return true;
		}
		else {
			System.out.println("error coludn't add ");
			return false;
		}
	
	}
	// return an available driver straing from the end of the array
	public Driver findDriver(int index){
		if (index == this.numOfDriver ) {
			System.out.println("there is no avilable driver");
			return null;
		}
		else {
			if (this.listOfDriver[index].isAvialable()) 
				return this.listOfDriver[index];
			else 
				return findDriver(index+1);
		}	
	}
	public boolean addCust(Customer c) {
		if (this.numOfCust < this.listOfCust.length) {
			this.listOfCust[this.numOfCust++] = c;
			return true;
		}
		else {
			System.out.println("error coludn't add ");
			return false;
			
		}
	}
	public boolean placeOrder(Order order) {
		double price = order.getTotalPrice();
		if (order.getCustomer().getBalance() >= price) {
			order.getCustomer().withdraw(price);
			order.getRestaurant().addMoney(price*.9);
			order.getDriver().addMoney(price*0.1);
			order.getRestaurant().addOrder(order);
			System.out.println("order is being sent to the restuarant");
			return true;
		}
		else {
			System.out.println("customer doesnt hava enogh money");
			return false;
		}
		
		
	}
	public void displayRest() {
		for (int i =0 ; i< this.numOfRest;i++) {
			System.out.println(i+1 +"-" +this.listOfRest[i].getName());
		}
	}
	public Restaurant getRest(int i) {
		return this.listOfRest[i];
	}
	public void displayCust() {
		for (int i =0 ; i< this.numOfCust;i++) {
			System.out.println(i+1 +"-" +this.listOfCust[i]);
		}
	}
	public Customer getCust(int i) {
		return this.listOfCust[i];
	}
	
}
