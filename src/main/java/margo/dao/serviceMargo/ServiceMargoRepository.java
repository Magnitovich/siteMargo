package margo.dao.serviceMargo;

import margo.model.interior.InteriorModel;
import margo.model.serviceMargo.ServiceMargoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceMargoRepository extends CrudRepository<ServiceMargoModel, Long> {

    List<ServiceMargoModel> findByPhoto(String photo);

    List<ServiceMargoModel> findByName(String name);
}
