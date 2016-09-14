package org.zkoss.training.performance;

import java.util.Arrays;

import org.zkoss.zul.event.TreeDataEvent;
import org.zkoss.zul.event.TreeDataListener;

public class DynamicHierarchyTreeModel extends HierarchyTreeModel{
	private static final long serialVersionUID = 1L;

	public DynamicHierarchyTreeModel(String data) {
		super(data);
		
		this.addTreeDataListener(new TreeDataListener() {
			@Override
			public void onChange(TreeDataEvent event) {
				if (event.getType() == TreeDataEvent.OPEN_CHANGED) {
					int[] path = event.getPath();
					boolean opened = isPathOpened(path);
					Hierarchy<String> node = getChild(event.getPath());
					node.setExpanded(opened);
					if(opened) {
						loadChildrenOnDemand(node);
						fireEvent(TreeDataEvent.INTERVAL_ADDED, path, 0, node.getChildren().size() - 1);
					} else {
						fireEvent(TreeDataEvent.INTERVAL_REMOVED, path, 0, node.getChildren().size() - 1);
					}
				}
			}
		});
		
		getRoot().setExpanded(true);
		loadChildrenOnDemand(getRoot());
	}

	private void loadChildrenOnDemand(Hierarchy<String> node) {
		if(node.getChildren() == null) {
			System.out.println("loading children of: " + node.getData());
			//alternatively load from DB here
			for(int i = 0; i < 5; i++) {
//			for(int i = 0; i < 100 / (int)Math.pow(4, node.getLevel()) ; i++) {
				String childData = node.getData() + "-" + (i + 1);
				node.addChildData(childData, node.getLevel() >= 2);
			}
		}
	}

	public void move(Hierarchy<String> node, Hierarchy<String> newParentNode) {
		Hierarchy<String> oldParentNode = node.getParent();
		int indexOfChild = super.getIndexOfChild(oldParentNode, node);
		fireEvent(TreeDataEvent.INTERVAL_REMOVED, getPath(oldParentNode), indexOfChild, indexOfChild, getPath(node));
		node.getParent().removeChild(node);
		
		
		if(!newParentNode.isExpanded()) {
			loadChildrenOnDemand(newParentNode);
		}
		
		newParentNode.addChild(node);

		if(newParentNode.isExpanded()) {
			fireEvent(TreeDataEvent.INTERVAL_ADDED, getPath(newParentNode), newParentNode.getChildren().size() -1, newParentNode.getChildren().size() -1, getPath(node));
		}
	}
	
//	@Override
//	public int[] getPath(Hierarchy<String> child) {
//		int[] path = new int[child.getLevel()];
//		int index = path.length - 1;
//		
//		Hierarchy<String> pathNode = child;
//		while(pathNode != getRoot()) {
//			Hierarchy<String> parentNode = pathNode.getParent();
//			path[index] = parentNode.getChildren().indexOf(pathNode);
//			index--;
//			pathNode = parentNode;
//		}
//		
//		System.out.println("path" + Arrays.toString(path));
//		return path;
//	}
}
