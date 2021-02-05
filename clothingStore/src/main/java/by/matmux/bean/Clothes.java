package by.matmux.bean;

import java.math.BigDecimal;

/**
 * @author mustafarara
 * 
 */
public class Clothes extends Entity {
	private BigDecimal price;
	private int numbers;
	private Brand brand;
	private Type type;
	private String size;
	private String color;
	private String imgPath;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgB64) {
		this.imgPath = imgB64;
	}	
}
