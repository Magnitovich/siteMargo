package margo.service.accessories;

import margo.dao.accessories.*;
import margo.model.accessories.*;
import margo.model.finishedProduct.*;
import margo.model.modelDTO.accessories.AccessoriesDTO;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddEditAccessoriesService {
    @Autowired
    private BandRepository bandRepository;
    @Autowired
    private FringeRepository fringeRepository;
    @Autowired
    private LuversRepository luversRepository;
    @Autowired
    private PickupRepository pickupRepository;
    @Autowired
    private VariousRepository variousRepository;

    private final String band = "band";
    private final String fringe = "fringe";
    private final String luvers = "luvers";
    private final String pickup = "pickup";
    private final String various = "various";
    private List<AccessoriesDTO> allFabricDTOs = null;

    public List<AccessoriesDTO> compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                                      final String name, final String describe, final String color,
                                                      final Double quantity, final BigDecimal price,  String checkTo) {
        switch (checkTo) {
            case band:
                BandModel bandModel = new BandModel();
                if((bandRepository.findByPhoto(photo)).size() == 0 && (bandRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, name, describe,
                            color,quantity,price, bandRepository, bandModel);
                    break;
                } else { throw new RuntimeException("WOW"); }
            case fringe:
                FringeModel fringeModel = new FringeModel();
                if((fringeRepository.findByPhoto(photo)).size() == 0 && (fringeRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, name, describe, color,quantity,price, fringeRepository,
                            fringeModel);
                    break;
                } else { throw new RuntimeException("WOW"); }
            case luvers:
                LuversModel luversModel = new LuversModel();
                if((luversRepository.findByPhoto(photo)).size() == 0 && (luversRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, name, describe,
                            color,quantity,price, luversRepository, luversModel);
                    break;
                } else { throw new RuntimeException("WOW"); }
            case pickup:
                PickupModel pickupModel = new PickupModel();
                if((pickupRepository.findByPhoto(photo)).size() == 0 && (pickupRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, name, describe,
                            color,quantity,price, pickupRepository, pickupModel);
                    break;
                } else { throw new RuntimeException("WOW"); }

            case various:
                VariousModel variousModel = new VariousModel();
                if((variousRepository.findByPhoto(photo)).size() == 0 && (variousRepository.findByName(name)).size() == 0){
                    addNewInfoInDB(photo, photo01, photo02, name, describe,
                            color,quantity,price, variousRepository, variousModel);
                    break;
                } else { throw new RuntimeException("WOW"); }
        }
        return allFabricDTOs;
    }
    @Transactional
    public void addNewInfoInDB(final String photo, final String photo01, final String photo02,
                               final String name, final String describe,
                               final String color, final Double quantity, final BigDecimal price,
                               CrudRepository repository, AllFinishProductModel curtainModel){

        curtainModel.setPhoto(photo);
        curtainModel.setPhoto01(photo01);
        curtainModel.setPhoto02(photo02);
        curtainModel.setName(name);
        curtainModel.setDescription(describe);
        curtainModel.setColor(color);
        if(quantity == null){
            curtainModel.setQuantity(0.00);
        } else {
            curtainModel.setQuantity(quantity);
        }
        if(price == null){
            curtainModel.setPrice(new BigDecimal(0));
        } else {
            curtainModel.setPrice(price);
        }
        repository.save(curtainModel);
    }
    @Transactional
    public void editCurtain(AccessoriesDTO dto, CrudRepository repository) {
        AllFinishProductModel model = (AllFinishProductModel) repository.findOne(dto.getId());

        if (!StringUtils.isEmpty(dto.getPhoto())) {
            model.setPhoto(dto.getPhoto());
        }
        if (!StringUtils.isEmpty(dto.getPhoto01())) {
            model.setPhoto01(dto.getPhoto01());
        }
        if (!StringUtils.isEmpty(dto.getPhoto02())) {
            model.setPhoto02(dto.getPhoto02());
        }
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setColor(dto.getColor());
        if(dto.getQuantity() == null){
            model.setQuantity(0.00);
        } else {
            model.setQuantity(dto.getQuantity());
        }
        if(dto.getPrice() == null){
            model.setPrice(new BigDecimal(0));
        } else {
            model.setPrice(dto.getPrice());
        }

        repository.save(model);
    }

}
