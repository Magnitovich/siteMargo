package margo.service.fabric;

import margo.dao.fabric.ClothFabricRepository;
import margo.model.allCurtains.ClothFabricModel;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothFabricService {

    @Autowired
    private ClothFabricRepository repository;

    public ClothFabricDTO convertModelToDto(ClothFabricModel model){

        ClothFabricDTO clothFabricDTO = new ClothFabricDTO();
        clothFabricDTO.setPhoto(model.getPhoto());
        clothFabricDTO.setPhoto01(model.getPhoto01());
        clothFabricDTO.setPhoto02(model.getPhoto02());
        clothFabricDTO.setPhoto03(model.getPhoto03());
        clothFabricDTO.setPhoto04(model.getPhoto04());
        clothFabricDTO.setPhoto05(model.getPhoto05());
        clothFabricDTO.setId(model.getId());
        clothFabricDTO.setName(model.getName());
        clothFabricDTO.setDescription(model.getDescription());
        clothFabricDTO.setStructure(model.getStructure());
        clothFabricDTO.setPaint(model.getPaint());
        clothFabricDTO.setHeight(model.getHeight());
        clothFabricDTO.setColor(model.getColor());
        clothFabricDTO.setQuantity(model.getQuantity());
        clothFabricDTO.setPrice(model.getPrice());

        return clothFabricDTO;
    }
    public List<ClothFabricDTO> convertListModelToDTO(List<ClothFabricModel> models){

        List<ClothFabricDTO> clothFabricDTOs = new ArrayList<>();
        for (ClothFabricModel clothFabricModel: models){
            ClothFabricDTO dto = convertModelToDto(clothFabricModel);
            clothFabricDTOs.add(dto);
        }
        return clothFabricDTOs;
    }
    public List<ClothFabricDTO> seeAllCloth(){
        Iterable<ClothFabricModel> models = repository.findAll();
        List<ClothFabricDTO>  clothFabricDTOs = convertListModelToDTO((List<ClothFabricModel>) models);

        return clothFabricDTOs;
    }

}
