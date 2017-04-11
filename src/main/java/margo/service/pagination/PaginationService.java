package margo.service.pagination;
import margo.dao.cart.CustomerRepository;
import margo.dao.fabric.CurtainRepository;
import margo.model.allCurtains.CurtainModel;
import margo.model.cartOder.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaginationService {

    @Autowired
    private CustomerRepository repository;

    @Transactional
    public Page<CustomerModel> findAllCurtainPageable(Pageable pageable) {
        Page<CustomerModel> all = repository.findAll(pageable);

        return all;
    }
}

