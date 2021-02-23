package by.matmux.bean;

public class Size extends Entity {
	private String name;
	private int clothesId;
	private int numbers;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClothesId() {
		return clothesId;
	}
	public void setClothesId(int clothesId) {
		this.clothesId = clothesId;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
}
