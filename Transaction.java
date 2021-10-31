package project0;

public class Transaction {
	private int id;
	private String fromUser;
	private int amount;
	
	Transaction(int id,String fromUser,int amount){
		this.id = id;
		this.fromUser = fromUser;
		this.amount = amount;
	}
		public int getId() {
			return this.id;
		}
	
	
		public String getUser() {
			return this.fromUser;
		}
		
		public int getAmount() {
			return this.amount;
		}
		
		public String toString() {
			String s = "The user "+fromUser+" wishes to transfer "+amount+" funds to your account";
			return s;
			
		}
	
}
