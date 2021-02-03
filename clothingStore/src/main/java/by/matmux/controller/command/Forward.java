package by.matmux.controller.command;

import java.util.HashMap;
import java.util.Map;

public class Forward {
	private String path;
	private boolean redirect;
	private Map<String, Object> attributes = new HashMap<>();
	
	public Forward(String path) {
		super();
		this.path = path;
	}

	public Forward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

}
