package margo.service.fabric;

import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
public class CurtainService {


    @Transactional
    public void editCurtain(CurtainDTO curtainDTO, CrudRepository repository) {
        AllFinishProductModel model = (AllFinishProductModel) repository.findOne(curtainDTO.getId());

        if (!StringUtils.isEmpty(curtainDTO.getPhoto())) {
            model.setPhoto(curtainDTO.getPhoto());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto01())) {
            model.setPhoto01(curtainDTO.getPhoto01());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto02())) {
            model.setPhoto02(curtainDTO.getPhoto02());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto03())) {
            model.setPhoto03(curtainDTO.getPhoto03());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto04())) {
            model.setPhoto04(curtainDTO.getPhoto04());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto05())) {
            model.setPhoto05(curtainDTO.getPhoto05());
        }
        model.setName(curtainDTO.getName());
        model.setDescription(curtainDTO.getDescription());
        model.setStructure(curtainDTO.getStructure());
        model.setPaint(curtainDTO.getPaint());
        model.setHeight(curtainDTO.getHeight());
        model.setColor(curtainDTO.getColor());
        if(curtainDTO.getQuantity() == null){
            model.setQuantity(0.00);
        } else {
            model.setQuantity(curtainDTO.getQuantity());
        }
        if(curtainDTO.getPrice() == null){
            model.setPrice(new BigDecimal(0));
        } else {
            model.setPrice(curtainDTO.getPrice());
        }
//        model.setQuantity(curtainDTO.getQuantity());
//        model.setPrice(curtainDTO.getPrice());

        repository.save(model);
    }

@Transactional
    public void addNewCurtain(final String photo, final String photo01, final String photo02,
                              final String photo03, final String photo04, final String photo05,
                              final String name, final String describe, final String structure, final String paint,
                              final String height, final String color, final Double quantity, final BigDecimal price,
                              CrudRepository repository, AllFinishProductModel curtainModel) {

        curtainModel.setPhoto(photo);
        curtainModel.setPhoto01(photo01);
        curtainModel.setPhoto02(photo02);
        curtainModel.setPhoto03(photo03);
        curtainModel.setPhoto04(photo04);
        curtainModel.setPhoto05(photo05);

        curtainModel.setName(name);
        curtainModel.setDescription(describe);
        curtainModel.setStructure(structure);
        curtainModel.setPaint(paint);
        curtainModel.setHeight(height);
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
}
