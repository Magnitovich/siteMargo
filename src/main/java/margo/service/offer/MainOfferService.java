package margo.service.offer;

import margo.dao.offer.OfferRepository;
import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.offer.OfferModel;
import margo.service.accessories.CheckAccessoriesRepositoryService;
import margo.service.fabric.*;
import margo.service.interior.InteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MainOfferService {


    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private SelectRepositoryService repositoryService;

    private List<AllFabricDTO> mainList = new ArrayList<>();

    public List<AllFabricDTO> showAll(Double percent, Integer quantity){
        List<AllFabricDTO> forOffer = new ArrayList<>();

//        System.out.println("LIST before shuffle: "+listAccessories);
        Collections.shuffle(mainList);
        System.out.println();
//        System.out.println("LIST after: "+listAccessories);
//        System.out.println();
//        System.out.println("first get(0): "+mainList.get(0));
//        System.out.println();
//        System.out.println("get(1): "+mainList.get(1));

        for (int i = 0; i<quantity; i++){
            forOffer.add(mainList.get(i));
        }
//        System.out.println();
//        System.out.println("Size: "+forOffer);
        return forOffer;
    }

    public void receiveInfoAboutNewOffer(Long idCommodity, String partCommodity, BigDecimal priceFromDB, Date dateOffer,
                                         BigDecimal newPrice, CrudRepository repository, AllFabricDTO margoDTO) {

        if(offerRepository.findByPhoto(margoDTO.getPhoto()).size() == 0) {
            saveOffer(idCommodity, partCommodity, dateOffer, newPrice, priceFromDB, margoDTO);
        } else { throw new RuntimeException("WOW"); }
        addBooleanToSelectedCommodity(idCommodity, repository, newPrice);

    }
    @Transactional
    private void saveOffer(Long idCommodity, String partCommodity, Date dateOffer, BigDecimal newPrice,
                           BigDecimal priceFromDB, AllFabricDTO margoDTO){
        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        OfferModel model = new OfferModel();
        model.setIdFromCommodity(idCommodity);
        model.setPhoto(margoDTO.getPhoto());
        model.setName(margoDTO.getName());
        model.setPrice(newPrice);
        model.setPriceBefore(priceFromDB);
        model.setOderDate(date);
        model.setDateFinishOffer(dateOffer);
        model.setOffer(true);
        model.setPart(partCommodity);

        offerRepository.save(model);
    }
    @Transactional
    private void addBooleanToSelectedCommodity(Long id, CrudRepository repository, BigDecimal newPrice){

        AllFinishProductModel model = (AllFinishProductModel) repository.findOne(id);
        model.setOffer(true);
        model.setPrice(newPrice);
        repository.save(model);
    }
    public List<OfferModel> seeAllOffer(){
        Date date = new Date();
        List<OfferModel> all = (List<OfferModel>) offerRepository.findAll();
        List<OfferModel> forPage = new ArrayList<>();
        for(OfferModel model: all){
            if ((model.getDateFinishOffer()).getTime()>date.getTime()){
                forPage.add(model);
            } else {
                finishOffer(model.getIdFromCommodity(), model.getPart(), model.getId(), model.getPriceBefore());
            }
        }
        return forPage;
    }
    private void finishOffer(Long idFromCommodity, String part, Long id, BigDecimal priceBefore) {

        CrudRepository repository = repositoryService.selectRepository(part);
        AllFinishProductModel one = (AllFinishProductModel) repository.findOne(idFromCommodity);
        one.setOffer(false);
        one.setPrice(priceBefore);
        repository.save(one);
        offerRepository.delete(offerRepository.findOne(id));
    }
    public void delete(List<Long> idDeleted) {

        for(Long id:idDeleted){
            deleteFromRepository(id);
        }
    }
    private void deleteFromRepository(Long id) {
        OfferModel model = offerRepository.findOne(id);
        CrudRepository repository = repositoryService.selectRepository(model.getPart());
        AllFinishProductModel one = (AllFinishProductModel) repository.findOne(model.getIdFromCommodity());
        one.setPrice(model.getPriceBefore());
        repository.save(one);

        offerRepository.delete(id);
    }
}
