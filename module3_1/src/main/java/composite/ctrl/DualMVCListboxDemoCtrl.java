package composite.ctrl;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import bean.PersonDAO;
import composite.mvc.ChooseEvent;
import composite.mvc.DualMVCListbox;

public class DualMVCListboxDemoCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = -7073226760328040872L;

	@Wire
	private DualMVCListbox dualLBox;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		dualLBox.setModel(new ListModelList<Object>(PersonDAO.getAll()));
	}

	@Listen("onChoose = #dualLBox")
	public void onChoose(ChooseEvent event) {
		System.out.println("onChoose Event invoked");
	}

}
