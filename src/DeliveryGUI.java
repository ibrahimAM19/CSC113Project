
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DeliveryGUI {
    private AppManager app;
    private JFrame inputFrame, displayFrame;
    private JTextField nameField, balanceField;
    private JRadioButton premiumRadio;
    private JTextArea displayArea;

    public DeliveryGUI(AppManager app) {
       // try {
            //ObjectInputStream file = new ObjectInputStream(new FileInputStream("info1.dat"));
            //app = (AppManager) file.readObject();
            this.app = app;
            /*
            
            file.close();
        } catch (Exception e) {
            app = new AppManager();
            Dish d1 = new Dish("burger", 25);
            Dish d2 = new Dish("pizza", 40);
            Restaurant r1 = new Restaurant("first burger");
            r1.addDish(d1); r1.addDish(d2);
            Customer c1 = new PremiumCustomer("ibra", 200);
            Driver v1 = new Driver("fahad");
            app.addRest(r1);
            app.addCust(c1);
            app.addDriver(v1);
        }
             */
        createInputFrame();
        createDisplayFrame();
       // ibra add -> 
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // ibra add -> 
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // inputFrame.setVisible(true);
    }
    // ibra add -> 
     public void showSignUp() {
        inputFrame.setVisible(true);
    }
    // ibra add -> 
    public void showDisplay() {
        displayFrame.setVisible(true);
    }

    private void createInputFrame() {
        inputFrame = new JFrame("Delivery App - Add Customer");
        inputFrame.setSize(400, 300);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setLayout(new GridLayout(5, 2, 10, 10));

        inputFrame.add(new JLabel(" Customer Name:"));
        nameField = new JTextField();
        inputFrame.add(nameField);

        inputFrame.add(new JLabel(" Balance:"));
        balanceField = new JTextField();
        inputFrame.add(balanceField);

        premiumRadio = new JRadioButton("Premium");
        JRadioButton regularRadio = new JRadioButton("Regular", true);
        ButtonGroup group = new ButtonGroup();
        group.add(premiumRadio); group.add(regularRadio);
        
        JPanel radioPanel = new JPanel();
        radioPanel.add(premiumRadio); radioPanel.add(regularRadio);
        inputFrame.add(new JLabel(" Account Type:"));
        inputFrame.add(radioPanel);

        JButton addButton = new JButton("Add Customer");
        JButton openDisplayBtn = new JButton("Open Display & Files");
        inputFrame.add(addButton); inputFrame.add(openDisplayBtn);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(inputFrame, "Name cannot be empty!");
                    return;
                }
                double balance = Double.parseDouble(balanceField.getText()); 
                
                if (premiumRadio.isSelected()) {
                    app.addCust(new PremiumCustomer(name, balance));
                } else {
                    app.addCust(new RegularCustomer(name, balance));
                }
                JOptionPane.showMessageDialog(inputFrame, "Customer Added Successfully!");
                nameField.setText("");
                balanceField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(inputFrame, "Error: Balance must be a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        openDisplayBtn.addActionListener(e -> displayFrame.setVisible(true));
    }

    private void createDisplayFrame() {
        displayFrame = new JFrame("Delivery App - Results & Files");
        displayFrame.setSize(500, 400);
        displayFrame.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayFrame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton showBtn = new JButton("Show Customers");
        JButton saveBtn = new JButton("Save Data (File)");
        JButton orderBtn = new JButton("Test Place Order");

        bottomPanel.add(showBtn); 
        bottomPanel.add(saveBtn); 
        bottomPanel.add(orderBtn);
        displayFrame.add(bottomPanel, BorderLayout.SOUTH);

        showBtn.addActionListener(e -> {
            displayArea.setText("--- All Customers ---\n");
            int i = 0;
            while (true) {
                try {
                    Customer c = app.getCust(i);
                    if (c == null) break;
                    displayArea.append(c.toString() + "\n");
                    i++;
                } catch (Exception ex) {
                    break;
                }
            }
        });

        saveBtn.addActionListener(e -> {
            try {
                FileOutputStream f = new FileOutputStream("info1.dat");
                ObjectOutputStream write = new ObjectOutputStream(f);
                write.writeObject(app);
                write.flush();
                write.close();
                JOptionPane.showMessageDialog(displayFrame, "Data Saved Successfully to info1.dat!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(displayFrame, "Error Saving File!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        orderBtn.addActionListener(e -> {
            try {
                Customer c = app.getCust(0);
                Restaurant r = app.getRest(0);
                Driver d = app.findDriver();
                
                if (c == null || r == null || d == null) {
                    JOptionPane.showMessageDialog(displayFrame, "Missing Data (No Customer/Driver)!");
                    return;
                }

                Order o = new Order(r, c, d);
                o.addDish(r.getDish(0));
                
                processOrder(o); 
                
                JOptionPane.showMessageDialog(displayFrame, "Order Placed Successfully! New Balance: " + c.getBalance());
                
            } catch (BalanceException ex) {
                JOptionPane.showMessageDialog(displayFrame, ex.getMessage(), "Order Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(displayFrame, "Error placing order!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void processOrder(Order o) throws BalanceException {
        if (o.getCustomer().getBalance() < o.getTotalPrice()) {
            throw new BalanceException("Error: Customer balance is less than the order total (" + o.getTotalPrice() + ").");
        }
        app.placeOrder(o);
    }

    /*public static void main(String[] args) {
        new DeliveryGUI();
    }*/
}