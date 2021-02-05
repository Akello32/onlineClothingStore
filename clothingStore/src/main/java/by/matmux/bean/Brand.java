package by.matmux.bean;

public class Brand extends Entity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	 * public Brand getTypebyId(int id) { Brand brand = null; List<Brand> list =
	 * Arrays.stream(values()).filter(x -> x.getId() ==
	 * id).collect(Collectors.toList()); return list.get(0); }
	 */
}
