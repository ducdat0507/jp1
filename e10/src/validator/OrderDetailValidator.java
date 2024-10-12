package validator;

import entity.OrderDetail;
import exception.InvalidQuantityException;
import exception.ValidationException;

public class OrderDetailValidator {
    public static void validate(OrderDetail orderDetail) throws ValidationException {
        if (orderDetail.getQuantity() <= 0) 
            throw new InvalidQuantityException("Quantity must be greater than 0");
    }

}
