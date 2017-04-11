package margo.dao.fabric;

import margo.model.allCurtains.CurtainModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurtainRepository extends CrudRepository<CurtainModel, Long> {
//public interface CurtainRepository extends PagingAndSortingRepository<CurtainModel, Long> {

    List<CurtainModel> findByPhoto(String photo);

    List<CurtainModel> findById(Long id);

    List<CurtainModel> findByName(String name);

//    @Query("SELECT count(e) FROM CurtainModel e")
//    Page<CurtainModel> findAllRows(Pageable pageable);
}
