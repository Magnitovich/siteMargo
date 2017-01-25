package margo.service.fabric;

import margo.dao.fabric.CurtainRepository;
import margo.model.allCurtains.ClothFabricModel;
import margo.model.allCurtains.CurtainModel;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurtainService {

    @Autowired
    private CurtainRepository repository;
    @Autowired
    private AdminRoleService adminRoleService;

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
        List<CurtainDTO> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0

        String role = adminRoleService.userRole();
        System.out.println("ROLE="+role);
        if (role.equals("user")) {

//       Hiding of items which are equal to 0

            for (CurtainDTO curtain : fabricDTOs) {
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
///---------------//-------------------//---------------------//-----------------------
    public CurtainDTO viewSelectedCurtain(Long id){
        CurtainModel curtainModel = repository.findOne(id);
        CurtainDTO curtainDTO = convertModelToDto(curtainModel);
        return curtainDTO;
    }

    @Transactional
    public void editCurtain(CurtainDTO curtainDTO) {
        CurtainModel model = repository.findOne(curtainDTO.getId());

        if (!StringUtils.isEmpty(curtainDTO.getPhoto())) {
            model.setPhoto(curtainDTO.getPhoto());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto01())){
            model.setPhoto01(curtainDTO.getPhoto01());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto02())){
            model.setPhoto02(curtainDTO.getPhoto02());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto03())){
            model.setPhoto03(curtainDTO.getPhoto03());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto04())){
            model.setPhoto04(curtainDTO.getPhoto04());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto05())){
            model.setPhoto05(curtainDTO.getPhoto05());
        }

        model.setName(curtainDTO.getName());
        model.setDescription(curtainDTO.getDescription());
        model.setStructure(curtainDTO.getStructure());
        model.setPaint(curtainDTO.getPaint());
        model.setHeight(curtainDTO.getHeight());
        model.setColor(curtainDTO.getColor());
        model.setQuantity(curtainDTO.getQuantity());
        model.setPrice(curtainDTO.getPrice());

        repository.save(model);
    }

    public List<CurtainDTO> viewPhoto(String photo) {
        List<CurtainModel> yachtsModels = repository.findByPhoto(photo);
        List<CurtainDTO> yachtDTOs = convertListModelToDTO(yachtsModels);
        return yachtDTOs;
    }
        public List<CurtainDTO> viewName(String name) {
        List<CurtainModel> yachtsModels = repository.findByName(name);
        List<CurtainDTO> yachtDTOs = convertListModelToDTO(yachtsModels);
        return yachtDTOs;
    }


    public void addNewCurtain(final String photo, final String photo01, final String photo02,
                              final String photo03, final String photo04, final String photo05,
                              final String name, final String describe, final String structure, final String paint,
                              final String height,final String color, final Double quantity, final BigDecimal price) {
        CurtainModel curtainModel = new CurtainModel();
        System.out.println(photo+", "+photo01+", "+photo02+", "+photo03+", "+photo04+", "+photo05+", "+name+" structure "
        +structure+" paint " + paint + " height "+height+ "color: " +color+"/");
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
        curtainModel.setQuantity(quantity);
        curtainModel.setPrice(price);
        repository.save(curtainModel);
    }




}
