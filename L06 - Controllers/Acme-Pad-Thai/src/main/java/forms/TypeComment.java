package forms;

public class TypeComment {

	public TypeComment(){
		super();
	}
	

	private String title;
	private String text;
	private int starts;
	private boolean rate;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getStarts() {
		return starts;
	}
	public void setStarts(int starts) {
		this.starts = starts;
	}
	public boolean isRate() {
		return rate;
	}
	public void setRate(boolean rate) {
		this.rate = rate;
	}

	
	
}
