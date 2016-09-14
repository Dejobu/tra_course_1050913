package org.zkoss.training.performance;

import org.zkoss.zul.AbstractTreeModel;

/**
 * https://www.zkoss.org/wiki/ZK_Developer's_Reference/MVC/Model/Tree_Model
 */
public class LoadOnDemandTreeModel extends AbstractTreeModel<Hierarchy<String>> {
	private static final long serialVersionUID = 1L;

	public LoadOnDemandTreeModel(String data) {
		super(new Hierarchy<>(data, false));
	}

	private static final int MAX_DEPTH = 8;
	private static final int CHILD_COUNT = 6;

	@Override
	public boolean isLeaf(Hierarchy<String> node) {
		return node.isLeaf();
	}

	@Override
	public Hierarchy<String> getChild(Hierarchy<String> parent, int index) {
		//System.out.println("getChild for: " + parent.getData() + " " + index);
		if(parent.getLevel() < MAX_DEPTH) {
			loadChildNodes(parent);
		}
		return parent.getChildren().get(index);
	}

	@Override
	public int getChildCount(Hierarchy<String> parent) {
		//System.out.println("getChildCount for: " + parent.getData());
		return loadChildCount(parent);
	}

	private int loadChildCount(Hierarchy<String> parent) {
		return parent.isLeaf() ? 0 : CHILD_COUNT;
	}

	private void loadChildNodes(Hierarchy<String> parent) {
		if(parent.getChildren() == null) {
			//Threads.sleep(50);
			//System.out.println("Loading Children for: " + parent.getData());
			for(int i = 0; i < CHILD_COUNT; i++) {
				parent.addChildData(parent.getData() + "-" + (i + 1), parent.getLevel() == MAX_DEPTH - 1);
			}
		}
	}
}
