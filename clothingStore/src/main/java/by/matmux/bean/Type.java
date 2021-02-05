package by.matmux.bean;

public class Type  extends Entity  {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	/*
	 * public Type getTypebyId(int id) { Type type = null; List<Type> list =
	 * Arrays.stream(values()).filter(x -> x.getId() ==
	 * id).collect(Collectors.toList()); return list.get(0); }
	 */
}
