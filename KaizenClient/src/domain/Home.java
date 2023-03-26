package domain;

public class Home {
	private String widget1;
	private String widget2;
	private String widget3;
	private String widget4;
	
	public Home() {
		super();
		this.widget1 = "";
		this.widget2 = "";
		this.widget3 = "";
		this.widget4 = "";
	}
	
	public Home(String widget1, String widget2, String widget3, String widget4) {
		super();
		this.widget1 = widget1;
		this.widget2 = widget2;
		this.widget3 = widget3;
		this.widget4 = widget4;
	}

	public String getWidget1() {
		return widget1;
	}
	
	public void setWidget1(String widget1) {
		this.widget1 = widget1;
	}
	
	public String getWidget2() {
		return widget2;
	}
	
	public void setWidget2(String widget2) {
		this.widget2 = widget2;
	}
	
	public String getWidget3() {
		return widget3;
	}
	
	public void setWidget3(String widget3) {
		this.widget3 = widget3;
	}
	
	public String getWidget4() {
		return widget4;
	}
	
	public void setWidget4(String widget4) {
		this.widget4 = widget4;
	}
	
}
