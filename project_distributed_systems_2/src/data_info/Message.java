package data_info;

public class Message {
	
	private String text;
	private String date;
	private String userId;
	
	public Message(String text, String date, String userId) {
		
		this.text = text;
		this.date = date;
		this.userId = userId;

		
	}

	public String getText() {
		
		return this.text;
	}
	
	public String getDate() {
		
		return this.date;
	}
	
	public String getUserId() {
		
		return this.userId;
	}
	
}
