import java.io.Serializable;

public class AppManager  implements Serializable{
	// old array -> private int numOfRest,numOfCust,numOfDriver;
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
		 /* old array ->
		  if (this.numOfRest < this.listOfRest.length) {
		 
			this.listOfRest[this.numOfRest++] = new Restaurant(r);
			return true;
		}
		else {
			System.out.println("error colud add ");
			return false;
		}
		 */
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
		/* old array ->
		 * for (int i = 0;i<this.numOfRest;i++) {
		 
			if (name.equals( this.listOfRest[i].getName()) )
				return i;
		}
		return -1;
		*/
	}
	public boolean delRest(String name) {
		int restIndex = searchRest(name);
		if (restIndex != -1) {
			return listOfRest.removeByIndex(restIndex);
			
			}
		else
			return false;
		}
		/* old array ->
		 int restIndex = searchRest(name);
		
		if (restIndex != -1) {
			this.listOfRest[restIndex] = this.listOfRest[this.numOfRest-1];
			this.listOfRest[this.numOfRest-1] = null;
			this.numOfRest--;
			return true;
		}
		else return false;
		 */
	
	public boolean addDriver(Driver d) {
		listOfDriver.insertAtBack(d);
		return true;
		/* old array
		 * 
		 
		if (this.numOfDriver < this.listOfDriver.length) {
			this.listOfDriver[this.numOfDriver++] = d;
			return true;
		}
		else {
			System.out.println("error coludn't add ");
			return false;
		}
		*/
	
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
		/* old array
		 * if (index == this.numOfDriver ) {
		
			System.out.println("there is no avilable driver");
			return null;
		}
		else {
			if (this.listOfDriver[index].isAvialable()) 
				return this.listOfDriver[index];
			else 
				return findDriver(index+1);
		}	
		 */
	
	public boolean addCust(Customer c) {
		listOfCust.insertAtBack(c);
		return true;
		/* old array ->
		 * 
		 
		if (this.numOfCust < this.listOfCust.length) {
			this.listOfCust[this.numOfCust++] = c;
			return true;
		}
		else {
			System.out.println("error coludn't add ");
			return false;
			
		}
		*/
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
		/* old arrya ->
		 * 
		 
		for (int i =0 ; i< this.numOfRest;i++) {
			System.out.println(i+1 +"-" +this.listOfRest[i].getName());
		}
		*/
	}
	public Restaurant getRest(int i) {
		return (Restaurant)listOfRest.getElementAt(i);
	}
	public boolean displayCust() {
		return listOfCust.print();
		
		/* old array ->
		 * 
		 
		for (int i =0 ; i< this.numOfCust;i++) {
			System.out.println(i+1 +"-" +this.listOfCust[i]);
		}
		*/
	}
	public Customer getCust(int i) {
		return (Customer) listOfCust.getElementAt(i);
	}
	
}
