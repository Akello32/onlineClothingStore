package by.matmux.bean;

public class Review extends Entity {
	private String text;
	private int clothesId;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getClothesId() {
		return clothesId;
	}

	public void setClothesId(int clothesId) {
		this.clothesId = clothesId;
	}

}
