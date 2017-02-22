package margo.service.fabric;

import margo.dao.fabric.TulleRepository;
import margo.model.allCurtains.ClothFabricModel;
import margo.model.allCurtains.CurtainModel;
import margo.model.allCurtains.TulleModel;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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
    public void addNewInformation(final String photo, final String photo01, final String photo02,
                                  final String photo03, final String photo04, final String photo05,
                                  final String name, final String describe, final String structure, final String paint,
                                  final String height,final String color, final Double quantity, final BigDecimal price) {
        TulleModel model = new TulleModel();
        System.out.println(photo + ", " + photo01 + ", " + photo02 + ", " + photo03 + ", " + photo04 + ", " + photo05 + ", " + name + " structure "
                + structure + " paint " + paint + " height " + height + "color: " + color + "/");
        model.setPhoto(photo);
        model.setPhoto01(photo01);
        model.setPhoto02(photo02);
        model.setPhoto03(photo03);
        model.setPhoto04(photo04);
        model.setPhoto05(photo05);

        model.setName(name);
        model.setDescription(describe);
        model.setStructure(structure);
        model.setPaint(paint);
        model.setHeight(height);
        model.setColor(color);
        model.setQuantity(quantity);
        model.setPrice(price);
        repository.save(model);
    }
    public void deleteCloth(List<Long> models){
        for(Long delete:models){
            repository.delete(delete);
        }
    }
    @Transactional
    public void editCurtain(TulleDTO dto) {
        TulleModel model = repository.findOne(dto.getId());

        if (!StringUtils.isEmpty(dto.getPhoto())) {
            model.setPhoto(dto.getPhoto());
        }
        if (!StringUtils.isEmpty(dto.getPhoto01())){
            model.setPhoto01(dto.getPhoto01());
        }
        if (!StringUtils.isEmpty(dto.getPhoto02())){
            model.setPhoto02(dto.getPhoto02());
        }
        if (!StringUtils.isEmpty(dto.getPhoto03())){
            model.setPhoto03(dto.getPhoto03());
        }
        if (!StringUtils.isEmpty(dto.getPhoto04())){
            model.setPhoto04(dto.getPhoto04());
        }
        if (!StringUtils.isEmpty(dto.getPhoto05())){
            model.setPhoto05(dto.getPhoto05());
        }

        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setStructure(dto.getStructure());
        model.setPaint(dto.getPaint());
        model.setHeight(dto.getHeight());
        model.setColor(dto.getColor());
        model.setQuantity(dto.getQuantity());
        model.setPrice(dto.getPrice());

        repository.save(model);
    }

    public List<TulleDTO> viewPhoto(String photo) {
        List<TulleModel> yachtsModels = repository.findByPhoto(photo);
        List<TulleDTO> dtos = convertListModelToDTO(yachtsModels);
        return dtos;
    }
    public List<TulleDTO> viewName(String name) {
        List<TulleModel> yachtsModels = repository.findByName(name);
        List<TulleDTO> dtos = convertListModelToDTO(yachtsModels);
        return dtos;
    }

    public TulleDTO viewSelectedCloth(Long id){
        TulleModel model = repository.findOne(id);
        TulleDTO dto = convertModelToDto(model);
        return dto;
    }
    public String convertIdToName(Long id){
        TulleModel one = repository.findOne(id);
        TulleDTO dto = convertModelToDto(one);
        return dto.getName();
    }

    public void delete(List<Long> models){
        for(Long delete:models){
            repository.delete(delete);
        }
    }
}
