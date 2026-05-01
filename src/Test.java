import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
public class Test {
	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);	
	
	AppManager app = null;
	try {
		ObjectInputStream file = new ObjectInputStream(new FileInputStream("info1.dat"));
		app = (AppManager) file.readObject();
		file.close();
		
	}
	catch (IOException e) {
		    e.printStackTrace();
			app =  new AppManager();
			Dish d1 = new Dish("burger",25);
			Dish d2 = new Dish("pizza",40);
			Dish d5 = new Dish("cola",4);
			
			Restaurant r1 = new Restaurant("first burger");
			r1.addDish(d1);r1.addDish(d2);r1.addDish(d5);
		
			Customer c1 = new PremiumCustomer("ibra",200);
			Driver v1 = new Driver("fahad");
		
			app.addRest(r1);
			app.addCust(c1);
			app.addDriver(v1);
			
	}
	catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	
	int option ;
	System.out.println("Welocome to The delivery app  ");
	while(true) {
		System.out.println("choose a number ");
		System.out.println("1- Make a New Order ");
		System.out.println("2- Log in or sign in as restaurant manger");
		System.out.println("3- Sing in as a new  customer");
		System.out.println("4- Add money to cutomer a cccount");
		System.out.println("5- Add a driver");
		System.out.println("6- Exit");
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
			Driver d = app.findDriver();
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
					Restaurant rest2 = new Restaurant(input.nextLine());
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
			System.out.print("name:");
			Driver driver = new Driver(input.next());
			app.addDriver(driver);
			break;
		case 6:
			//exit
			try {
				FileOutputStream f = new FileOutputStream("info1.dat");
				ObjectOutputStream write = new ObjectOutputStream(f);
				write.writeObject(app);
				write.flush();
				write.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();	
			} 
			System.exit(0);
			break;
		default:
			System.out.println("enter number bettween 1 to 4");
			break;
		}
		
	}
	
	
	
	

}
	}
