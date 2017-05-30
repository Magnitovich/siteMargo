package margo.dao.offer;

import margo.model.finishedProduct.BedroomModel;
import margo.model.offer.OfferModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<OfferModel, Long> {

    List<OfferModel> findByPhoto(String photo);

    List<OfferModel> findByName(String name);

//    @Query(value = "select cs from OfferModel cs")
//    List<OfferModel> findAllOffer();
//
//    @Query(value = "select cs from OfferModel cs where id=?")
//    List<OfferModel> findOneOffer(Long id);
//
}
