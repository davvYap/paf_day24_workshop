package sg.edu.nus.iss.workshop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop24.model.PO;
import sg.edu.nus.iss.workshop24.model.POResults;
import sg.edu.nus.iss.workshop24.repository.PORepositry;

@Service
public class POService {

    @Autowired
    PORepositry poRepository;

    public int insertIntoPurchaseOrder(PO po) {
        return poRepository.insertIntoPurchaseOrder(po);
    }

    public List<POResults> getAllPo() {
        return poRepository.getAllPo();
    }

    public List<POResults> getPo(int orderId) {
        return poRepository.getPo(orderId);
    }

}
