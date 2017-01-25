package margo.service.fabric;

import margo.dao.fabric.CurtainRepository;
import margo.model.allCurtains.ClothFabricModel;
import margo.model.allCurtains.CurtainModel;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurtainService {

    @Autowired
    private CurtainRepository repository;

    public CurtainDTO convertModelToDto(CurtainModel model){

        CurtainDTO fabricDTO = new CurtainDTO();
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
    public List<CurtainDTO> convertListModelToDTO(List<CurtainModel> models){

        List<CurtainDTO> fabricDTOs = new ArrayList<>();
        for (CurtainModel clothFabricModel: models){
            CurtainDTO dto = convertModelToDto(clothFabricModel);
            fabricDTOs.add(dto);
        }
        return fabricDTOs;
    }
    public List<CurtainDTO> seeAllModels(){
        Iterable<CurtainModel> models = repository.findAll();
        List<CurtainDTO>  fabricDTOs = convertListModelToDTO((List<CurtainModel>) models);

        return fabricDTOs;
    }


}
