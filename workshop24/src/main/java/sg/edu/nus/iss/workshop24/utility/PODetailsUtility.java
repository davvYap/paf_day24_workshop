package sg.edu.nus.iss.workshop24.utility;

import org.springframework.stereotype.Component;

import sg.edu.nus.iss.workshop24.repository.PODetailsRepository;

@Component
public class PODetailsUtility {

    public double getUnitPrice(double price, double productDiscount) {
        return price * productDiscount * (1.0 + PODetailsRepository.tax);
    }
}
