
public class Driver extends Person {
	private boolean avialable;
	private Order orderToDelver;
	public Driver(String name,double balance) {
		super(name,balance);
		avialable = true;
		this.orderToDelver = null;
	}
	public Driver(String name) {
		super(name,0);
		avialable = true;
		this.orderToDelver = null;
	}
	public boolean isAvialable() {
		return avialable;
	}
	public Order getOrderToDelver() {
		return orderToDelver;
	}
	public void setOrderToDelver(Order orderToDelver) {
		this.orderToDelver = orderToDelver;
		this.avialable = false;
	}
	public void orderIsDelevired() {
		this.avialable = true;
		this.orderToDelver = null;
	}
	@Override
	public String toString() {
		return "Driver " +super.toString() +  " avialable:" + avialable ;
	}

}
