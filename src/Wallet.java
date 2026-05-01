import java.io.Serializable;

public interface Wallet  extends Serializable{
	public boolean withdraw(double m);
	public void addMoney(double m);
}
