import java.util.*;
public class Test {
	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);	
	
	Dish d1 = new Dish("burger",25);
	Dish d2 = new Dish("pizza",40);
	Dish d3 = new Dish("shawarama",10);
	Dish d4 = new Dish("fries",7);
	Dish d5 = new Dish("cola",4);
	Restaurant r1 = new Restaurant("first burger",20);
	Restaurant r2 = new Restaurant("the golden shawarma",30);
	r1.addDish(d1);r1.addDish(d2);r1.addDish(d5);
	r2.addDish(d3);r2.addDish(d4);r2.addDish(d3);
	Customer c1 = new PremiumCustomer("ibra",200);
	Customer c2= new RegularCustomer("Ahmed",150);
	Customer c3 = new PremiumCustomer("yasser",100);
	Driver v1 = new Driver("fahad");
	Driver v2 = new Driver("ali");
	AppManager app = new AppManager(10,20,5);
	app.addRest(r1);app.addRest(r2);
	app.addCust(c1);app.addCust(c2);app.addCust(c3);
	app.addDriver(v1);app.addDriver(v2);
	
	int option ;
	System.out.println("Welocome to The delivery app  ");
	while(true) {
		System.out.println("choose a number ");
		System.out.println("1- Make a New Order ");
		System.out.println("2- Log in or sign in as restaurant manger");
		System.out.println("3- Sing in as a new  customer");
		System.out.println("4- Add money to cutomer a cccount");
		System.out.println("5- Exit");
		option = input.nextInt();
		 
		switch (option) {
		case 1:
			System.out.println("--------------------------");
			System.out.println("Chosse an acouunt");
			app.displayCust();
			System.out.println("--------------------------");
			Customer c = app.getCust(input.nextInt()-1);
			System.out.println("Choose a restaurant");
			app.displayRest();
			Restaurant r= app.getRest(input.nextInt() -1);
			Driver d = app.findDriver(0);
			if (d == null) {
				System.out.println("sorry there is no free driver");
				break;
			}
			Order o = new Order(r,c,d);
			r.displayMenu();
			
			System.out.println("Keep adding dishes. Enter -1 when you are done.");
			option = input.nextInt() -1;
			while(option != -2) {
				o.addDish(o.getRestaurant().getDish(option));
				System.out.println("Keep adding dishes. Enter -1 when you are done.");
				option = input.nextInt() -1;
			}
			if(o.getNumOfDishes() !=0) {
				System.out.println("This is your order\n" + o);
				if (app.placeOrder(o)) {
					o.getDriver().orderIsDelevired();
					System.out.println("the order has arrived,\nThank YOU!");
					System.out.println("--------------------------");
				}}
			System.out.println(c);
			break;
		case 2:
			//rest
			boolean key = true;
			while(key) {
				System.out.println("--------------------------");
				System.out.println("1-Whithdraw moeny form restaurant acount");
				System.out.println("2- Add a new restaurant");
				System.out.println("3-Exit");
				option = input.nextInt();
				input.nextLine();
				switch(option) {
				case 1:
					System.out.println("--------------------------");
					System.out.println("Enter the name of your restaurant");
					String name = input.nextLine();
					int index = app.searchRest(name);
					if (index != -1) {
						Restaurant rest = app.getRest(index);
						System.out.println(rest);
						System.out.println("Enter the amount you what to withdraw");
						rest.withdraw(input.nextDouble());
						System.out.println(rest);
					}
					else {
						System.out.println("Restautant is not found");
					}
					break;
				case 2:
					System.out.println("--------------------------");
					System.out.println("Enter the restaurant name:");
					System.out.println("Enter the maximum number of orders the restaurant can handle:");
					Restaurant rest2 = new Restaurant(input.nextLine(),input.nextInt());
					System.out.println("Creating The Menu\nEnter the name and the price of your dish. Enter None and -1 when you are done.");
					String n = input.next();int p = input.nextInt();
					while(p != -1) {
						rest2.addDish(new Dish(n,p));
						System.out.println("A dish has been added");
						n = input.next();p = input.nextInt();
					}
					rest2.displayMenu();
					app.addRest(rest2);				
					break;
				case 3:
					key= false;
					break;
				default:
					System.out.println("Enter a number bettwen 1 to 3");
				}
			}
			break;
		case 3:
			System.out.println("--------------------------");
			System.out.println("Choose an account type:");
			System.out.println("1 - Preumiun account [15% discount on each order]");
			System.out.println("2 - Regular account");
			System.out.println("Enter name:");
			System.out.println("Enter the amount of money to add to your account:");
			System.out.println("Note: Premium accounts have a 50 fee will be took out from the balance.");
			int a = input.nextInt();
			if(a == 1 ) 
				app.addCust(new PremiumCustomer(input.next(),input.nextDouble() -50 ));
			else if( a ==2) 
				app.addCust(new RegularCustomer(input.next(),input.nextDouble()));
			break;
		case 4:
			System.out.println("--------------------------");
			app.displayCust();
			System.out.println("Chosse an acouunt");
			Customer e = app.getCust(input.nextInt()-1);
			System.out.println("Enter the amout of money you want to add");
			e.addMoney(input.nextDouble());
			break;
		case 5:
			//exit
			System.exit(0);
			break;
		default:
			System.out.println("enter number bettween 1 to 4");
			break;
		}
		
	}
	
	
	
	
	/*
	
	app.displayRest();
	//chech that find driver is not null
	Restuarant choosenRest = app.getRest(1-1);
	Order o1 = new Order(choosenRest,c1,app.findDriver(0));
	choosenRest.displayMenu();
	o1.addDish(o1.getRestaurant().getDish(1));
	System.out.println(o1);
	c1.addMoney(15);
	app.placeOrder(o1);
	
	Order o2 = new Order(app.getRest(2-1),c2,app.findDriver(0));
	r2.displayMenu();
	o2.addDish(o2.getRestaurant().getDish(2)) ;o2.addDish(o2.getRestaurant().getDish(2));o2.addDish(o2.getRestaurant().getDish(2));
	System.out.println(o2);
	c2.addMoney(46);
	app.placeOrder(o2);
	
	System.out.println(c1 + "\n"+ c2);
	System.out.println(v1);
	System.out.println(v2);
	//Order o3 = new Order(r1,c1,app.findDriver(0));
	//System.out.println(o3);
	int a= app.searchRest("First");
	app.displayRest();
	
	
	/*
	Customer c = new PremiumCustomer("ibra");
	Driver d= new Driver("fahad");
	Order o =  new Order(r,c,d);
	Order v = new Order(o); 
	r.addDish(d1);r.addDish(d2);
	
	*/
}
	}
