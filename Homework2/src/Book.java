import java.time.LocalDate;

public class Book{

	private String title;
	private String author;
	private LocalDate date;
	private int price;

	public Book(String title, String author, LocalDate date, int price) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "タイトル: " + title + ", 著者: " + author + ", 発売日: " + date + ", 値段: " + price;
	}
	
}
