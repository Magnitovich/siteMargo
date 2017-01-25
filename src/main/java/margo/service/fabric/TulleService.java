package margo.service.fabric;

import margo.dao.fabric.TulleRepository;
import margo.model.allCurtains.CurtainModel;
import margo.model.allCurtains.TulleModel;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TulleService {
    @Autowired
    private TulleRepository repository;
    @Autowired
    private AdminRoleService adminRoleService;

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
        List<TulleDTO> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0

        String role = adminRoleService.userRole();
        System.out.println("ROLE="+role);
        if (role.equals("user")) {

//       Hiding of items which are equal to 0

            for (TulleDTO curtain : fabricDTOs) {
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
