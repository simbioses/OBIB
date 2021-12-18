package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.OrderStatus;
import ca.uvic.leadlab.obibconnector.facades.send.IOrder;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.Order;

public class OrderBuilder extends DocElement implements IOrder {

    private final Order order;

    OrderBuilder(ISubmitDoc submitDoc, Order order) {
        super(submitDoc);
        this.order = order;
    }

    @Override
    public IOrder id(String id) {
        order.setId(id);
        return this;
    }

    @Override
    public IOrder id(String id, String type) {
        order.setId(id, type);
        return this;
    }

    @Override
    public IOrder statusCode(OrderStatus orderStatus) {
        order.setStatusCode(orderStatus.name().toLowerCase());
        return this;
    }
}
