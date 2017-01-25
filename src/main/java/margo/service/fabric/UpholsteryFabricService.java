package margo.service.fabric;

import margo.dao.fabric.UpholsteryFabricRepository;
import margo.model.allCurtains.TulleModel;
import margo.model.allCurtains.UpholsteryFabricModel;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.model.modelDTO.allCurtainsDTO.UpholsteryFabricDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpholsteryFabricService {
    @Autowired
    private UpholsteryFabricRepository repository;
    @Autowired
    private AdminRoleService adminRoleService;

    public UpholsteryFabricDTO convertModelToDto(UpholsteryFabricModel model){

        UpholsteryFabricDTO fabricDTO = new UpholsteryFabricDTO();
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
    public List<UpholsteryFabricDTO> convertListModelToDTO(List<UpholsteryFabricModel> models){

        List<UpholsteryFabricDTO> fabricDTOs = new ArrayList<>();
        for (UpholsteryFabricModel clothFabricModel: models){
            UpholsteryFabricDTO dto = convertModelToDto(clothFabricModel);
            fabricDTOs.add(dto);
        }
        return fabricDTOs;
    }
    public List<UpholsteryFabricDTO> seeAllModels(){
        Iterable<UpholsteryFabricModel> models = repository.findAll();
        List<UpholsteryFabricDTO>  fabricDTOs = convertListModelToDTO((List<UpholsteryFabricModel>) models);
        List<UpholsteryFabricDTO> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0

        String role = adminRoleService.userRole();
        System.out.println("ROLE="+role);
        if (role.equals("user")) {

//       Hiding of items which are equal to 0

            for (UpholsteryFabricDTO curtain : fabricDTOs) {
                if (curtain.getQuantity() > 0) {
                    curtainDTOsWithZERO.add(curtain);
                } else {
                    System.out.println("Name: "+curtain.getName()+" Quantity "+curtain.getQuantity());
                }
            }
            return curtainDTOsWithZERO;

        } else if (role.equals("admin")) {

            return fabricDTOs;
        } else {
            //moderator
            return null;
        }
    }
}
