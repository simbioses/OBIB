package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.OrderStatus;

public interface IOrder extends IAnd {

    IOrder id(String id);

    IOrder id(String id, String type);

    IOrder statusCode(OrderStatus orderStatus);
}
