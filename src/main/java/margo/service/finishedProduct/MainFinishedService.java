package margo.service.finishedProduct;

import margo.controller.finishProduct.AllFinishProduct;
import margo.model.allCurtains.CurtainModel;
import margo.model.finishedProduct.AllFinishProductModel;
import margo.model.finishedProduct.BedroomModel;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.model.modelDTO.finishProductDTO.SewedDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MainFinishedService {

    @Autowired
    private AdminRoleService adminRoleService;

    private List<AllFabricDTO> forFilter = new ArrayList<>();

    public AllFabricDTO convertModelToDto(AllFinishProductModel model) {

        AllFabricDTO fabricDTO = new AllFabricDTO();
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
        fabricDTO.setItIsSewed(model.getItIsSewed());
        fabricDTO.setQuantity(model.getQuantity());
        fabricDTO.setPrice(model.getPrice());

        return fabricDTO;
    }

    public List<AllFabricDTO> convertListModelToDTO(List<AllFinishProductModel> models) {

        List<AllFabricDTO> finishDTO = new ArrayList<>();
        for (AllFinishProductModel finishProduct : models) {
            AllFabricDTO dto = convertModelToDto(finishProduct);
            finishDTO.add(dto);
        }
        return finishDTO;
    }


    public List<AllFabricDTO> checkOnAuthentication(List<AllFabricDTO> fabricDTOs){
        List<AllFabricDTO> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0
        String role = adminRoleService.userRole();
        if (role.equals("user")) {
//       Hiding of items which are equal to 0
            for (AllFabricDTO curtain : fabricDTOs) {
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
    public List<AllFabricDTO> seeAllModels(CrudRepository repository) {
        Iterable<AllFinishProductModel> models = repository.findAll();
        List<AllFabricDTO> fabricDTOs = convertListModelToDTO((List<AllFinishProductModel>) models);

        List<AllFabricDTO> allFabricDTOs = checkOnAuthentication(fabricDTOs);
        forFilter = allFabricDTOs;
        return allFabricDTOs;
    }

    ///---------------//-------------------//---------------------//-----------------------
    public AllFabricDTO viewSelectedFinishProduct(Long id, CrudRepository repository) {
        AllFinishProductModel model = (AllFinishProductModel) repository.findOne(id);
        AllFabricDTO finishDTO = convertModelToDto(model);
        return finishDTO;
    }

    public String convertIdToName(Long id, CrudRepository repository) {
        AllFinishProductModel one = (AllFinishProductModel) repository.findOne(id);
        AllFabricDTO curtainDTO = convertModelToDto(one);
        return curtainDTO.getName();
    }

    public ArrayList seeColor() {
        String[] res = null;
        List colorModel = new ArrayList();
        for (AllFabricDTO col : forFilter) {
            colorModel.add(col.getColor());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
    }
    public ArrayList seePaint() {
        String[] res = null;
        List colorModel = new ArrayList();
        for (AllFabricDTO col : forFilter) {
            colorModel.add(col.getPaint());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
    }

    public ArrayList seeStructure() {
        String[] res = null;
        List colorModel = new ArrayList();
        for (AllFabricDTO col : forFilter) {
            colorModel.add(col.getStructure());
        }
        String[] temp = (String[]) colorModel.toArray(new String[colorModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList colors = new ArrayList(Arrays.asList(res));
        return colors;
    }
    public ArrayList seeSewed() {
        String[] res = null;
        List sewedModel = new ArrayList();
        for (AllFabricDTO col : forFilter) {
            sewedModel.add(col.getItIsSewed());
        }
        String[] temp = (String[]) sewedModel.toArray(new String[sewedModel.size()]);
        Set<String> set = new HashSet<String>(Arrays.asList(temp));
        res = set.toArray(new String[set.size()]);

        ArrayList sewed = new ArrayList(Arrays.asList(res));
        return sewed;
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
            for (AllFabricDTO col : forFilter) {
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
    public AllFabricDTO findSelectedModel(Long id, CrudRepository repository){
        AllFinishProductModel product = (AllFinishProductModel) repository.findOne(id);
        AllFabricDTO allFabricDTO = convertModelToDto(product);
        return allFabricDTO;
    }
    public void delete(List<Long> models, CrudRepository repository) {
        for (Long deleteCurtain : models) {
            repository.delete(deleteCurtain);
        }
    }

}
