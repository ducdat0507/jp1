package validator;

import entity.Product;
import exception.InvalidIdException;
import exception.InvalidNameException;
import exception.InvalidQuantityException;
import exception.ValidationException;

public class ProductValidator {
    public static void validate(Product product) throws ValidationException {
        if (!product.getId().matches("^(MS|NE|SE)[0-9]{6}$"))
            throw new InvalidIdException("Id must match ^(MS|NE|SE)[0-9]{6}$");
        if (!product.getName().matches("^[a-zA-Z\\s]{3,}$")) 
            throw new InvalidNameException("Name must match ^[a-zA-Z\\s]{3,}$");
        if (product.getQuantity() < 0) 
            throw new InvalidQuantityException("Quantity can not be less than 0");
    }
}
