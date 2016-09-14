package org.zkoss.training.performance;

import org.zkoss.zul.AbstractTreeModel;

public class HierarchyTreeModel extends AbstractTreeModel<Hierarchy<String>> {

	private static final long serialVersionUID = 1L;

	public HierarchyTreeModel(String data) {
		super(new Hierarchy<String>(data, false));
	}

	@Override
	public boolean isLeaf(Hierarchy<String> node) {
		return node.isLeaf();
	}

	@Override
	public Hierarchy<String> getChild(Hierarchy<String> parent, int index) {
		System.out.println("getChild for: " + parent.getData() + " " + index);
		return parent.getChildren().get(index);
	}

	@Override
	public int getChildCount(Hierarchy<String> parent) {
		System.out.println("getChildCount for: " + parent.getData());
		return parent.isExpanded() ? parent.getChildren().size() : 0;
	}
}