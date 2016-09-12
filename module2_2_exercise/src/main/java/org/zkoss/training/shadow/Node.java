package org.zkoss.training.shadow;

import java.util.List;

import org.zkoss.zul.ListModelList;

public class Node<T> {
	private T data;
	private Node<T> parent;
	private ListModelList<Node<T>> children = new ListModelList<>();
	private boolean open = false;
	
	public Node(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void toggleOpen() {
		this.open = !this.open;
	}
	
	public boolean isOpen() {
		return open;
	}

	public void detach() {
		this.getParent().getChildren().remove(this);
		this.parent = null;
	}

	public void attach(Node<T> parent) {
		this.parent = parent;
		this.getParent().getChildren().add(this);
	}
}
