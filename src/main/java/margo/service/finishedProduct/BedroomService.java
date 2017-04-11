package margo.service.finishedProduct;

import margo.dao.finishProduct.BedroomRepository;
import margo.model.allCurtains.CurtainModel;
import margo.model.finishedProduct.BedroomModel;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    private List<AllFabricDTO> forFilter;

    public AllFabricDTO convertModelToDto(BedroomModel model) {

        AllFabricDTO fabricDTO = new CurtainDTO();
        fabricDTO.setPhoto(model.getPhoto());
        fabricDTO.setPhoto01(model.getPhoto01());
        fabricDTO.setPhoto02(model.getPhoto02());
        fabricDTO.setPhoto03(model.getPhoto03());
        fabricDTO.setPhoto04(model.getPhoto04());
        fabricDTO.setPhoto05(model.getPhoto05());
        fabricDTO.setId(model.getId());
        fabricDTO.setName(model.getName());
        fabricDTO.setDescription(model.getDescription());
        fabricDTO.setStructure(model.getStructure());
        fabricDTO.setPaint(model.getPaint());
        fabricDTO.setHeight(model.getHeight());
        fabricDTO.setColor(model.getColor());
        fabricDTO.setQuantity(model.getQuantity());
        fabricDTO.setPrice(model.getPrice());

        return fabricDTO;
    }
    public List<AllFabricDTO> convertListModelToDTO(List<BedroomModel> models) {

        List<AllFabricDTO> finishDTO = new ArrayList<>();
        for (BedroomModel finishProduct : models) {
            AllFabricDTO dto = convertModelToDto(finishProduct);
            finishDTO.add(dto);
        }
        return finishDTO;
    }

    public List<AllFabricDTO> seeAllModels() {
        Iterable<BedroomModel> models = bedroomRepository.findAll();
        List<AllFabricDTO> fabricDTOs = convertListModelToDTO((List<BedroomModel>) models);

        forFilter = fabricDTOs;
        List<AllFabricDTO> allFabricDTOs = mainFinishedService.checkOnAuthentication(fabricDTOs);
        return allFabricDTOs;
    }

    ///---------------//-------------------//---------------------//-----------------------
    public AllFabricDTO viewSelectedFinishProduct(Long id) {
        BedroomModel curtainModel = bedroomRepository.findOne(id);
        AllFabricDTO finishDTO = convertModelToDto(curtainModel);
        return finishDTO;
    }
    public ArrayList seeColor() {  return  mainFinishedService.seeColor(forFilter);    }

    public ArrayList seePaint() {return mainFinishedService.seePaint(forFilter);   }

    public ArrayList seeStructure() { return mainFinishedService.seeStructure(forFilter);    }

    public ArrayList seePrice() { return mainFinishedService.seePrice(forFilter);     }

    public String convertIdToName(Long id) {
        BedroomModel one = bedroomRepository.findOne(id);
        AllFabricDTO curtainDTO = convertModelToDto(one);
        return curtainDTO.getName();
    }
    public void deleteCurtain(List<Long> models) {
        for (Long deleteCurtain : models) {
            bedroomRepository.delete(deleteCurtain);
        }
    }


}
