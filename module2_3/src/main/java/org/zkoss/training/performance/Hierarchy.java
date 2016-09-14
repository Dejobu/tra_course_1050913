package org.zkoss.training.performance;

import java.util.ArrayList;
import java.util.List;

public class Hierarchy<T> {
	private Hierarchy<T> parent;
	private List<Hierarchy<T>> children;
	private T data;
	private boolean leaf;
	
	private boolean expanded = false;
	
//	private int level = 0;

	public Hierarchy(T childData, boolean leaf) {
		this.data = childData;
		this.leaf = leaf;
	}
	public Hierarchy<T> getParent() {
		return parent;
	}
	public void setParent(Hierarchy<T> parent) {
		this.parent = parent;
//		level = parent.getLevel()+1;
	}
	public List<Hierarchy<T>> getChildren() {
		return children;
	}
	
	public void addChild(Hierarchy<T> child) {
		if(children == null) {
			children = new ArrayList<>();
		}
		child.setParent(this);
		children.add(child);
	}
	
	public void removeChild(Hierarchy<String> node) {
		children.remove(node);
		node.setParent(null);
	}
	
	public T getData() {
		return data;
	}
	public void addChildData(T childData, boolean leaf) {
		addChild(new Hierarchy<T>(childData, leaf));
	}
	
	public int getLevel() {
		if(this.parent == null) {
			return 0;
		} else {
			return this.getParent().getLevel() + 1;
		}
//		return level;
	}
	public boolean isLeaf() {
		return leaf;
	}
	
	
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(data);
		builder.append("(");
		builder.append(getLevel());
		builder.append(")");
		return builder.toString();
	}
}
