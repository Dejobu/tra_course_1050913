package org.zkoss.training.mvvm;


public class HelloVM {

	private String hello = "hello world";
	private String name = "enter your name";

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	public String getName() {
		return "hello "+name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
