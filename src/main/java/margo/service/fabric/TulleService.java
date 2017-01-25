package margo.service.fabric;

import margo.dao.fabric.TulleRepository;
import margo.model.allCurtains.CurtainModel;
import margo.model.allCurtains.TulleModel;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TulleService {
    @Autowired
    private TulleRepository repository;

    public TulleDTO convertModelToDto(TulleModel model){

        TulleDTO fabricDTO = new TulleDTO();
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
    public List<TulleDTO> convertListModelToDTO(List<TulleModel> models){

        List<TulleDTO> fabricDTOs = new ArrayList<>();
        for (TulleModel clothFabricModel: models){
            TulleDTO dto = convertModelToDto(clothFabricModel);
            fabricDTOs.add(dto);
        }
        return fabricDTOs;
    }
    public List<TulleDTO> seeAllModels(){
        Iterable<TulleModel> models = repository.findAll();
        List<TulleDTO>  fabricDTOs = convertListModelToDTO((List<TulleModel>) models);

        return fabricDTOs;
    }
}
