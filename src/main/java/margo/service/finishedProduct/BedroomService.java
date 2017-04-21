package margo.service.finishedProduct;

import margo.dao.finishProduct.BedroomRepository;
import margo.model.allCurtains.CurtainModel;
import margo.model.finishedProduct.BedroomModel;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BedroomService {

    @Autowired
    private BedroomRepository bedroomRepository;

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private MainFinishedService mainFinishedService;


//    private List<AllFabricDTO> forFilter;
//
//    public AllFabricDTO convertModelToDto(BedroomModel model) {
//
//        AllFabricDTO fabricDTO = new AllFabricDTO();
//        fabricDTO.setPhoto(model.getPhoto());
//        fabricDTO.setPhoto01(model.getPhoto01());
//        fabricDTO.setPhoto02(model.getPhoto02());
//        fabricDTO.setPhoto03(model.getPhoto03());
//        fabricDTO.setPhoto04(model.getPhoto04());
//        fabricDTO.setPhoto05(model.getPhoto05());
//        fabricDTO.setId(model.getId());
//        fabricDTO.setName(model.getName());
//        fabricDTO.setDescription(model.getDescription());
//        fabricDTO.setStructure(model.getStructure());
//        fabricDTO.setPaint(model.getPaint());
//        fabricDTO.setHeight(model.getHeight());
//        fabricDTO.setColor(model.getColor());
//        fabricDTO.setQuantity(model.getQuantity());
//        fabricDTO.setPrice(model.getPrice());
//
//        return fabricDTO;
//    }
//    public List<AllFabricDTO> convertListModelToDTO(List<BedroomModel> models) {
//
//        List<AllFabricDTO> finishDTO = new ArrayList<>();
//        for (BedroomModel finishProduct : models) {
//            AllFabricDTO dto = convertModelToDto(finishProduct);
//            finishDTO.add(dto);
//        }
//        return finishDTO;
//    }



    @Transactional
    public void editCurtain(AllFabricDTO dto) {
        BedroomModel model = bedroomRepository.findOne(dto.getId());

        if (!StringUtils.isEmpty(dto.getPhoto())) {
            model.setPhoto(dto.getPhoto());
        }
        if (!StringUtils.isEmpty(dto.getPhoto01())) {
            model.setPhoto01(dto.getPhoto01());
        }
        if (!StringUtils.isEmpty(dto.getPhoto02())) {
            model.setPhoto02(dto.getPhoto02());
        }
        if (!StringUtils.isEmpty(dto.getPhoto03())) {
            model.setPhoto03(dto.getPhoto03());
        }
        if (!StringUtils.isEmpty(dto.getPhoto04())) {
            model.setPhoto04(dto.getPhoto04());
        }
        if (!StringUtils.isEmpty(dto.getPhoto05())) {
            model.setPhoto05(dto.getPhoto05());
        }
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setStructure(dto.getStructure());
        model.setPaint(dto.getPaint());
        model.setHeight(dto.getHeight());
        model.setColor(dto.getColor());
        model.setItIsSewed(dto.getItIsSewed());
        model.setQuantity(dto.getQuantity());
        model.setPrice(dto.getPrice());

        bedroomRepository.save(model);
    }
}
