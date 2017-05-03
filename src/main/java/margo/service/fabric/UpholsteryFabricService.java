package margo.service.fabric;

import margo.dao.fabric.UpholsteryFabricRepository;
import margo.model.allCurtains.OrderCurtainModel;
import margo.model.allCurtains.TulleModel;
import margo.model.allCurtains.UpholsteryFabricModel;
import margo.model.modelDTO.allCurtainsDTO.*;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UpholsteryFabricService {
    @Autowired
    private UpholsteryFabricRepository repository;
    @Autowired
    private AdminRoleService adminRoleService;

    private List<UpholsteryFabricDTO> forFilter;

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
        forFilter = fabricDTOs;

        String role = adminRoleService.userRole();
//        System.out.println("UpholsteryService ROLE="+role);
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
    public void addNewInformation(final String photo, final String photo01, final String photo02,
                                  final String photo03, final String photo04, final String photo05,
                                  final String name, final String describe, final String structure, final String paint,
                                  final String height,final String color, final Double quantity, final BigDecimal price) {
        UpholsteryFabricModel model = new UpholsteryFabricModel();
//        System.out.println(photo + ", " + photo01 + ", " + photo02 + ", " + photo03 + ", " + photo04 + ", " + photo05 + ", " + name + " structure "
//                + structure + " paint " + paint + " height " + height + "color: " + color + "/");
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

    @Transactional
    public void editCurtain(UpholsteryFabricDTO dto) {
        UpholsteryFabricModel model = repository.findOne(dto.getId());

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

    public List<UpholsteryFabricDTO> viewPhoto(String photo) {
        List<UpholsteryFabricModel> yachtsModels = repository.findByPhoto(photo);
        List<UpholsteryFabricDTO> dtos = convertListModelToDTO(yachtsModels);
        return dtos;
    }
    public List<UpholsteryFabricDTO> viewName(String name) {
        List<UpholsteryFabricModel> yachtsModels = repository.findByName(name);
        List<UpholsteryFabricDTO> dtos = convertListModelToDTO(yachtsModels);
        return dtos;
    }

    public UpholsteryFabricDTO viewSelected(Long id){
        UpholsteryFabricModel model = repository.findOne(id);
        UpholsteryFabricDTO dto = convertModelToDto(model);
        return dto;
    }
    public String convertIdToName(Long id){
        UpholsteryFabricModel one = repository.findOne(id);
        UpholsteryFabricDTO dto = convertModelToDto(one);
        return dto.getName();
    }

    public void delete(List<Long> models){
        for(Long delete:models){
            System.out.println(delete);
            repository.delete(delete);
        }
    }
    public ArrayList seeColor(){
        String[] res = null;
        List colorModel = new ArrayList();
        for (UpholsteryFabricDTO col: forFilter){
            colorModel.add(col.getColor());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
    }
    public ArrayList seePaint(){
        String[] res = null;
        List colorModel = new ArrayList();
        for (UpholsteryFabricDTO col: forFilter){
            colorModel.add(col.getPaint());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
    }
    public ArrayList seeStructure(){
        String[] res = null;
        List colorModel = new ArrayList();
        for (UpholsteryFabricDTO col: forFilter){
            colorModel.add(col.getStructure());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
    }
    public ArrayList seePrice() {
        ArrayList list = new ArrayList();
        String[] res = null;
        List<BigDecimal> colorModel = new ArrayList();
        BigDecimal maxValue = null;
        BigDecimal minValue = null;
        System.out.println("FORfilterSIZE: " + forFilter.size());
        if (forFilter.size() == 0) {
            maxValue = new BigDecimal("0");
            minValue = new BigDecimal("0");
            list.add(maxValue);
            list.add(minValue);
            return list;
        } else {
            for (UpholsteryFabricDTO col : forFilter) {
                colorModel.add(col.getPrice());
            }
            maxValue = Collections.max(colorModel);
            minValue = Collections.min(colorModel);
            BigDecimal div = new BigDecimal("3");
            BigDecimal mult = new BigDecimal("2");
            BigDecimal first = maxValue.divide(div, 0, BigDecimal.ROUND_DOWN);
            String firstValue = first.toPlainString();
            String secondValue = (first.multiply(mult)).setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();

            list.add(firstValue);
            list.add(secondValue);
            return list;
        }
    }
}

