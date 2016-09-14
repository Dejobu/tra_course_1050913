package org.zkoss.training.shadow;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

public class HierarchyViewModel {
	
	private Node<String> root;
	
	@Init
	public void init() {
		root = new Node<String>("root");
		Node<String> item1 = addChild(root, "Item 1");
		Node<String> item2 = addChild(root, "Item 2");
		Node<String> item3 = addChild(root, "Item 3");
		addChild(item1, "Item 1.1");
		addChild(item1, "Item 1.2");
		addChild(item1, "Item 1.3");
		addChild(item2, "Item 2.1");
		addChild(item2, "Item 2.2");
		Node<String> item31 = addChild(item3, "Item 3.1");
//		Stream.of("Item 3.1.1", "Item 3.1.2", "Item 3.1.3")
//			.forEach(data -> addChild(item31, data));
		addChild(item31, "Item 3.1.1");
		addChild(item31, "Item 3.1.2");
		addChild(item31, "Item 3.1.3");
	}

	@Command
	public void toggleOpen(@BindingParam("node") Node<?> node) {
		node.toggleOpen();
		BindUtils.postNotifyChange(null, null, node, "open");
	}

	@Command
	public void removeNode(@BindingParam("node") Node<?> node) {
		node.detach();
	}
	
	@Command
	public void addChildNode(@BindingParam("parent") Node<String> parent) {
		addChild(parent, "NEW-" + System.currentTimeMillis());
		if(!parent.isOpen()) {
			toggleOpen(parent);
		}
	}
	
	public Node<String> getRoot() {
		return root;
	}
	
	private Node<String> addChild(Node<String> parent, String data) {
		Node<String> node = new Node<String>(data);
		node.attach(parent);
		return node;
	}
}

