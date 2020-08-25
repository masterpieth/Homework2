import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Homework2 {

	private Scanner scanner = new Scanner(System.in);
	private String input;
	private List<Book> bookList;
	private BufferedReader bufferedReader;
	
	public Homework2() {
		readList();
		exec();
	}
	
	public void exec() {
		while(true) {
			printMenu();
			input = scanner.nextLine();
			input = input.trim();
			int choice = 0;
			try {
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				if(input.equals("exit")) {
					System.out.println("プログラムを終了します。");
					break;
				}
			}
			switch(choice) {
			case 1:
				searchBook();
				break;
			case 2:
				printBookList();
				break;
			default:
				break;
			}
		}
	}
	public void readList() {
		try {
			bookList = new ArrayList<>();
			bufferedReader = Files.newBufferedReader(Paths.get("C:\\KICO\\図書リスト.csv"));
			String line = "";
			
			while((line = bufferedReader.readLine()) != null) {
				List<String> tempList = new ArrayList<>();
				String[] array = line.split(",");
				tempList = Arrays.asList(array);
				try {
					int price = Integer.parseInt(tempList.get(3));
					
					String[] dateStr = tempList.get(2).split("/");
					List<Integer> dateInt = new ArrayList<>();
					for(String str: dateStr) {
						int temp  = Integer.parseInt(str);
						dateInt.add(temp);
					}
					LocalDate date = LocalDate.of(dateInt.get(0), dateInt.get(1), dateInt.get(2));
					
					Book book = new Book(tempList.get(0), tempList.get(1), date, price);
					bookList.add(book);
				} catch(NumberFormatException e) {
					continue;
				} catch(DateTimeParseException e) {
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printMenu() {
		System.out.println("１．検索");
		System.out.println("２．出力");
		System.out.print("input:");
	}
	
	public void searchBook() {
		while(true) {
			System.out.println("検索(exit:戻る)");
			System.out.print("input(タイトル又は著者を入力):");
			input = scanner.nextLine();
			input = input.trim();
			if(input.equals("exit")) break;
			bookList.stream()
			.filter(book -> book.getTitle().contains(input) || book.getAuthor().contains(input))
			.forEach(System.out::println);
		}
	}
	public void printBookList() {
		while(true) {
			System.out.println("出力(exit:戻る)");
			System.out.println("1.タイトル順");
			System.out.println("2.発売日順");
			System.out.println("3.値段順");
			System.out.print("input:");
			input = scanner.nextLine();
			input = input.trim();
			int choice = 0;
			try {
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				if(input.equals("exit")) break;
				System.out.println("正しい番号を入力してください。");
				continue;
			}
			switch (choice) {
			case 1:
				bookList.stream()
				.sorted(Comparator.comparing(Book::getTitle))
				.forEach(System.out::println);
				break;
			case 2:
				bookList.stream()
				.sorted(Comparator.comparing(Book::getDate))
				.forEach(System.out::println);
				break;
			case 3:
				bookList.stream()
				.sorted(Comparator.comparing(Book::getPrice))
				.forEach(System.out::println);
				break;
			}
		}
	}
}
