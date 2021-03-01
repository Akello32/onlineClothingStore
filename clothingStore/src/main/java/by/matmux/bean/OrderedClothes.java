	package by.matmux.bean;

public class OrderedClothes extends Entity {
	private Clothes clothes;
	private Order order;
	private Size size;
	
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Clothes getClothes() {
		return clothes;
	}
	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
