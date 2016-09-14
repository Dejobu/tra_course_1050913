package org.zkoss.training.lifecycle;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Textbox;

public class TrainingComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;


	@Override
	public void doAfterCompose(Component comp) throws Exception {
		System.out.format("TrainingComposer doAfterCompose: %s " + comp);
		super.doAfterCompose(comp);
		
	    Textbox textbox = new Textbox();
	    textbox.setId(comp.getId() + "-textbox");
		comp.appendChild(textbox);
	}

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		System.out.format("TrainingComposer doBeforeCompose: %s in %s " + compInfo + parent);
		return super.doBeforeCompose(page, parent, compInfo);
	}
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		System.out.format("TrainingComposer doBeforeComposeChildren: %s", comp);
		super.doBeforeComposeChildren(comp);
	}
}
