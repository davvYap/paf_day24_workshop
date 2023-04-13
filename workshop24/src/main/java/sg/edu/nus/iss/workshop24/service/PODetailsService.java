package sg.edu.nus.iss.workshop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop24.model.Product;
import sg.edu.nus.iss.workshop24.repository.PODetailsRepository;

@Service
public class PODetailsService {
    @Autowired
    PODetailsRepository poDetailsRepository;

    public List<Product> getAllProducts() {
        return poDetailsRepository.getAllProducts();
    }

    // preventing dupicate product
    public List<Product> addToList(List<Product> oldProducts, Product newProduct) {

        for (Product product : oldProducts) {
            if (product.getName().equalsIgnoreCase(newProduct.getName())) {
                int totalQty = product.getQuantity() + newProduct.getQuantity();
                product.setQuantity(totalQty);
                return oldProducts;
            } else {
                continue;
            }
        }

        oldProducts.add(newProduct);
        return oldProducts;
    }

    public int[] insertPurchaseOrderDetails(List<Product> products, int orderId) {
        return poDetailsRepository.insertPurchaseOrderDetails(products, orderId);
    }

}
