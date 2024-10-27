package validator;

import entity.Order;
import exception.InvalidIdException;
import exception.ValidationException;

public class OrderValidator {
    public static void validate(Order order) throws ValidationException {
        if (!order.getId().matches("^(ORDPM)[0-9]{8}$")) 
            throw new InvalidIdException("Id must match ^(ORDPM)[0-9]{8}$");
    }
}
