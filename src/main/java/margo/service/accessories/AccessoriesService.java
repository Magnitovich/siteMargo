package margo.service.accessories;

import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.modelDTO.accessories.AccessoriesDTO;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AccessoriesService {

    @Autowired
    private AdminRoleService adminRoleService;

    private List<AccessoriesDTO> forFilter = new ArrayList<>();

    public AccessoriesDTO convertModelToDTO(AllFinishProductModel model){

        AccessoriesDTO fabricDTO = new AccessoriesDTO();
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
    public AllFabricDTO convertModelToAllFabricDTO(AllFinishProductModel model){

        AllFabricDTO fabricDTO = new AllFabricDTO();
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
    public List<AllFabricDTO> convertToListAllFabricDTO(List<AllFinishProductModel> models){

        List<AllFabricDTO> finishDTO = new ArrayList<>();
        for (AllFinishProductModel finishProduct : models) {
            AllFabricDTO dto = convertModelToAllFabricDTO(finishProduct);
            finishDTO.add(dto);
        }
        return finishDTO;
    }
    public List<AccessoriesDTO> convertToListDTO(List<AllFinishProductModel> models){

        List<AccessoriesDTO> finishDTO = new ArrayList<>();
        for (AllFinishProductModel finishProduct : models) {
            AccessoriesDTO dto = convertModelToDTO(finishProduct);
            finishDTO.add(dto);
        }
        return finishDTO;
    }

    public List<AccessoriesDTO> checkOnAuthentication(List<AccessoriesDTO> fabricDTOs){
        List<AccessoriesDTO> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0
        String role = adminRoleService.userRole();
        if (role.equals("user")) {
//       Hiding of items which are equal to 0
            for (AccessoriesDTO curtain : fabricDTOs) {
                if (curtain.getQuantity() > 0) {
                    curtainDTOsWithZERO.add(curtain);
                } else {
                    System.out.println("Name: " + curtain.getName() + " Quantity " + curtain.getQuantity());
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
    public List<AccessoriesDTO> seeAllModels(CrudRepository repository) {
        Iterable<AllFinishProductModel> models = repository.findAll();
        List<AccessoriesDTO> fabricDTOs = convertToListDTO((List<AllFinishProductModel>) models);

        List<AccessoriesDTO> allFabricDTOs = checkOnAuthentication(fabricDTOs);
        forFilter = allFabricDTOs;
        return allFabricDTOs;
    }
    public List<AllFabricDTO> seeAllModelsToSearch(CrudRepository repository) {
        Iterable<AllFinishProductModel> models = repository.findAll();
        List<AllFabricDTO> fabricDTOs = convertToListAllFabricDTO((List<AllFinishProductModel>) models);

        return fabricDTOs;
    }
    ///---------------//-------------------//---------------------//-----------------------
    public AccessoriesDTO viewSelectedFinishProduct(Long id, CrudRepository repository) {
        AllFinishProductModel model = (AllFinishProductModel) repository.findOne(id);
        AccessoriesDTO finishDTO = convertModelToDTO(model);
        return finishDTO;
    }
    public String convertIdToName(Long id, CrudRepository repository) {
        AllFinishProductModel one = (AllFinishProductModel) repository.findOne(id);
        AccessoriesDTO curtainDTO = convertModelToDTO(one);
        return curtainDTO.getName();
    }
    public ArrayList seeColor() {
        String[] res = null;
        List colorModel = new ArrayList();
        for (AccessoriesDTO col : forFilter) {
            colorModel.add(col.getColor());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
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
            for (AccessoriesDTO col : forFilter) {
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
    public AccessoriesDTO findSelectedModel(Long id, CrudRepository repository){
        AllFinishProductModel product = (AllFinishProductModel) repository.findOne(id);
        AccessoriesDTO allFabricDTO = convertModelToDTO(product);
        return allFabricDTO;
    }
    public void delete(List<Long> models, CrudRepository repository) {
        for (Long deleteCurtain : models) {
            repository.delete(deleteCurtain);
        }
    }


}
