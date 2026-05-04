import java.io.Serializable;

public class AppManager  implements Serializable{
	
	private List listOfRest;
	private List listOfCust;
	private List listOfDriver;
	public AppManager() {
		listOfRest = new List();
		listOfCust = new List();
		listOfDriver = new List();
	}
	public boolean addRest(Restaurant r) {
		listOfRest.insertAtBack(r);
		return true;
		
	}
	
	// retrurn an index searched restaurant otherwise return -1 
	public int searchRest(String name) {
		Node current = listOfRest.getHead();
		int count = 0;
		while(current != null) {
			Restaurant r =(Restaurant) current.getData();
			if ( name.equals( r.getName() ))
				return count;
			count++;
			current = current.getNext();
		}
		return -1;
		
	}
	public boolean delRest(String name) {
		int restIndex = searchRest(name);
		if (restIndex != -1) {
			return listOfRest.removeByIndex(restIndex);
			
			}
		else
			return false;
		}
		
	
	public boolean addDriver(Driver d) {
		listOfDriver.insertAtBack(d);
		return true;
		
	
	}
	public Driver findDriver() {
		return findDriverLogic( listOfDriver.getHead());
	}
		
	// return an available driver straing from the end of the array
	public Driver findDriverLogic(Node current){
		// 1: base case
		
		if (current == null){
			System.out.println("there is no avilable driver");
			return null;
		}
		// driver is found
		Driver d = (Driver) current.getData();
		if( d.isAvialable()) 
			return d;
		// Recursive case	
		else 
			return findDriverLogic(current.getNext());
		}
	
	
	public boolean addCust(Customer c) {
		listOfCust.insertAtBack(c);
		return true;
	}
	public boolean placeOrder(Order order) throws BalanceException {
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
			throw new BalanceException("customer doesnt hava enogh money");
			
		}
		
		
	}
	public void displayRest() {
		
		List  l = new List();
		 l.insertAtBack(3);
		 l.insertAtBack("a");
		 l.insertAtBack(true);
		
		 
		Node current = listOfRest.getHead();
		int count =1;
		while (current != null) {
			// 
			Restaurant r = (Restaurant) current.getData();
			System.out.println( count + "-"+ r.getName());
			count++;
			//
			current = current.getNext();
		
		}
		
	}
	public Restaurant getRest(int i) {
		return (Restaurant)listOfRest.getElementAt(i);
	}
	public boolean displayCust() {
		return listOfCust.print();
		
	}
	public Customer getCust(int i) {
		return (Customer) listOfCust.getElementAt(i);
	}
	
}
