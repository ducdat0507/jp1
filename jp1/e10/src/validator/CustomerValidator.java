package validator;

import entity.Customer;
import exception.InvalidNameException;
import exception.ValidationException;

public class CustomerValidator {
    public static void validate(Customer customer) throws ValidationException {
        if (!customer.getName().matches("^[a-zA-Z\\s]{3,50}$")) 
            throw new InvalidNameException("Name must match ^[a-zA-Z\\s]{3,50}$");
    }
}
