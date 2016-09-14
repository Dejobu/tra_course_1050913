package org.zkoss.training.shop.controller;

import java.text.SimpleDateFormat;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.training.shop.model.bean.Order;
import org.zkoss.training.shop.service.OrderService;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;

public class OrderVM {

    private WorkbenchContext context;
    private OrderService orderService;
    private ListModel<Order> orderModel;
    private Converter<String, Order, Label> orderStatusConverter;

    @Init
    public void init(@ContextParam(ContextType.DESKTOP) Desktop desktop) {
        context = WorkbenchContext.getInstance(desktop);
        orderService = context.getOrderService();
        orderModel = orderService.getOrderListModel();
    }

    public Converter<String, Order, Label> getOrderStatusConverter() {
        if (orderStatusConverter == null) {
            orderStatusConverter = new Converter<String, Order, Label>() {
                @Override
                public String coerceToUi(Order order, Label lbl, BindContext ctx) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
                    return order.getStatus() + " : " + sdf.format(order.getCreateDate());
                }

                @Override
                public Order coerceToBean(String compAttr, Label lbl, BindContext ctx) {
                    return null;
                }
            };
        }
        return orderStatusConverter;
    }

    public ListModel<Order> getOrderModel() {
        return orderModel;
    }
}
