package org.zkoss.training.shop.controller;

import org.zkoss.training.shop.service.OrderService;
import org.zkoss.zk.ui.Desktop;

/**
 * desktop scoped object to provide access to the service layer
 * @author hawk
 */
public class WorkbenchContext {

	private static final String KEY_WORKBENCH_CONTEXT = "KEY_WORKBENCH_CONTEXT";

	private OrderService orderService;

	private WorkbenchContext(Desktop desktop) {
		super();
		this.orderService = new OrderService(desktop.getSession());
	}

	public static WorkbenchContext getInstance(Desktop desktop) {
		WorkbenchContext context = (WorkbenchContext) desktop.getAttribute(KEY_WORKBENCH_CONTEXT);

		if (context == null)
			desktop.setAttribute(KEY_WORKBENCH_CONTEXT, context = new WorkbenchContext(desktop));

		return context;
	}


	public OrderService getOrderService() {
		return orderService;
	}
}
