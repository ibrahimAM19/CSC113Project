public class RegularCustomer  extends Customer{  



public RegularCustomer(String name, double balance) {
	super(name, balance);
}
public RegularCustomer(String name) {
	super(name);
}
public double getDiscount() {
	return 1;
}
@Override
public String toString() {
	return "Regular Customer, " + super.toString();
}

}
