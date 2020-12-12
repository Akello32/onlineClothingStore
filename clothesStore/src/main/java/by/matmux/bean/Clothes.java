package by.matmux.bean;

/**
 * @author mustafarara
 * 
 */
public class Clothes extends Entity {
	private int price;
	private int numbers;
	private int brandId;
	private int typed;
	private String size;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getTyped() {
		return typed;
	}

	public void setTyped(int typed) {
		this.typed = typed;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}	
}
