package by.matmux.bean;

public enum Role {
	ADMIN, MANAGER, BUYER;
		
	public Integer getIdentity() {
		return ordinal();
	}

	public static Role getByIdentity(Integer identity) {
		return Role.values()[identity];
	}
}
