package margo.service.interior;

import margo.dao.interior.InteriorRepository;
import margo.model.interior.InteriorModel;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.interior.InteriorDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class InteriorService {

    @Autowired
    private InteriorRepository repository;
    @Autowired
    private AdminRoleService adminRoleService;

    private List<InteriorModel> forFilter = new ArrayList<>();

    public InteriorDTO convertModelToDto(InteriorModel model) {

        InteriorDTO fabricDTO = new InteriorDTO();
        fabricDTO.setPhoto(model.getPhoto());
        fabricDTO.setPhoto01(model.getPhoto01());
        fabricDTO.setPhoto02(model.getPhoto02());
        fabricDTO.setId(model.getId());
        fabricDTO.setName(model.getName());
        fabricDTO.setDescription(model.getDescription());
        fabricDTO.setColor(model.getColor());
        fabricDTO.setQuantity(model.getQuantity());
        fabricDTO.setPrice(model.getPrice());

        return fabricDTO;
    }
//    public AllFabricDTO convertModelToAllFabricDto(InteriorModel model) {
//
//        AllFabricDTO fabricDTO = new AllFabricDTO();
//        fabricDTO.setPhoto(model.getPhoto());
//        fabricDTO.setPhoto01(model.getPhoto01());
//        fabricDTO.setPhoto02(model.getPhoto02());
//        fabricDTO.setId(model.getId());
//        fabricDTO.setName(model.getName());
//        fabricDTO.setDescription(model.getDescription());
//        fabricDTO.setColor(model.getColor());
//        fabricDTO.setQuantity(model.getQuantity());
//        fabricDTO.setPrice(model.getPrice());
//
//        return fabricDTO;
//    }

    public List<AllFabricDTO> convertListModelToAllDTO(List<InteriorModel> models) {

        List<AllFabricDTO> interiorDTOs = new ArrayList<>();
        for (InteriorModel interiorModel : models) {
            InteriorDTO dto = convertModelToDto(interiorModel);
            interiorDTOs.add(dto);
        }
        return interiorDTOs;
    }
    public List<InteriorDTO> convertListModelToDTO(List<InteriorModel> models) {

        List<InteriorDTO> interiorDTOs = new ArrayList<>();
        for (InteriorModel interiorModel : models) {
            InteriorDTO dto = convertModelToDto(interiorModel);
            interiorDTOs.add(dto);
        }
        return interiorDTOs;
    }


    public Iterable<InteriorModel> seeAllInterior(){
        List<InteriorModel> interiorModels = checkOnAuthentication(repository.findAll());
        forFilter = interiorModels;
        return interiorModels;
    }
    public List<AllFabricDTO> seeAllInteriorForSearch(){
        List<InteriorModel> interiorModels = checkOnAuthentication(repository.findAll());
        List<AllFabricDTO> list = convertListModelToAllDTO(interiorModels);
        return list;
    }

    public List<InteriorModel> checkOnAuthentication(Iterable<InteriorModel> fabricDTOs){
        List<InteriorModel> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0
        String role = adminRoleService.userRole();
        if (role.equals("user")) {
            for (InteriorModel curtain : fabricDTOs) {
                if (curtain.getQuantity() > 0) {
                    curtainDTOsWithZERO.add(curtain);
                } else {
                    System.out.println("Name: " + curtain.getName() + " Quantity " + curtain.getQuantity());
                }
            }
            return curtainDTOsWithZERO;

        } else if (role.equals("admin")) {

            return (List<InteriorModel>) fabricDTOs;
        } else {
            //moderator
            return null;
        }
    }
    public ArrayList seeColor() {
        String[] res = null;
        List colorModel = new ArrayList();
        for (InteriorModel col : forFilter) {
            colorModel.add(col.getColor());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
    }
    public InteriorDTO viewSelectedFinishProduct(Long id) {
        InteriorModel model = repository.findOne(id);
        InteriorDTO interiorDTO = convertModelToDto(model);
;
        return interiorDTO;
    }

    public ArrayList seePrice() {
        String[] res = null;
        ArrayList list = new ArrayList();
        List<BigDecimal> colorModel = new ArrayList();
        BigDecimal maxValue;
        BigDecimal minValue = null;
        if (forFilter.size() == 0) {
            maxValue = new BigDecimal("0");
            minValue = new BigDecimal("0");
            list.add(maxValue);
            list.add(minValue);
            return list;
        } else {
            for (InteriorModel col : forFilter) {
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
    public InteriorModel findSelectedModel(Long id){
        InteriorModel product =  repository.findOne(id);
        return product;
    }
    public void delete(List<Long> models) {
        for (Long deleteCurtain : models) {
            repository.delete(deleteCurtain);
        }
    }

    public List<InteriorDTO> viewPhoto(String photo) {
        return convertListModelToDTO(repository.findByPhoto(photo));
    }

    public List<InteriorDTO> viewName(String name) {
        return convertListModelToDTO(repository.findByName(name));
    }
    @Transactional
    public void addNewInformation(final String photo, final String photo01, final String photo02,
                                  final String name, final String describe,
                                  final String color, final Double quantity, final BigDecimal price) {
        InteriorModel model = new InteriorModel();

        model.setPhoto(photo);
        model.setPhoto01(photo01);
        model.setPhoto02(photo02);

        model.setName(name);
        model.setDescription(describe);
        model.setColor(color);
        model.setQuantity(quantity);
        model.setPrice(price);
        repository.save(model);
    }

    @Transactional
    public void editCurtain(InteriorDTO dto) {
        InteriorModel model = repository.findOne(dto.getId());

        if (!StringUtils.isEmpty(dto.getPhoto())) {
            model.setPhoto(dto.getPhoto());
        }
        if (!StringUtils.isEmpty(dto.getPhoto01())){
            model.setPhoto01(dto.getPhoto01());
        }
        if (!StringUtils.isEmpty(dto.getPhoto02())){
            model.setPhoto02(dto.getPhoto02());
        }
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setColor(dto.getColor());
        model.setQuantity(dto.getQuantity());
        model.setPrice(dto.getPrice());

        repository.save(model);
    }
}
