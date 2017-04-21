package margo.service.fabric;

import margo.dao.fabric.CurtainRepository;
import margo.model.allCurtains.CurtainModel;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CurtainService {

    @Autowired
    private CurtainRepository repository;
    @Autowired
    private AdminRoleService adminRoleService;


    private List<CurtainDTO> forFilter;

    public CurtainDTO convertModelToDto(CurtainModel model) {

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

    public List<CurtainDTO> convertListModelToDTO(List<CurtainModel> models) {

        List<CurtainDTO> fabricDTOs = new ArrayList<>();
        for (CurtainModel clothFabricModel : models) {
            CurtainDTO dto = convertModelToDto(clothFabricModel);
            fabricDTOs.add(dto);
        }
        return fabricDTOs;
    }

    public List<CurtainDTO> seeAllModels() {
        Iterable<CurtainModel> models = repository.findAll();
        List<CurtainDTO> fabricDTOs = convertListModelToDTO((List<CurtainModel>) models);
        List<CurtainDTO> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0
        forFilter = fabricDTOs;

        String role = adminRoleService.userRole();
//        System.out.println("ROLE="+role);
        if (role.equals("user")) {

//       Hiding of items which are equal to 0

            for (CurtainDTO curtain : fabricDTOs) {
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

    ///---------------//-------------------//---------------------//-----------------------
    public CurtainDTO viewSelectedCurtain(Long id) {
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
        if (!StringUtils.isEmpty(curtainDTO.getPhoto01())) {
            model.setPhoto01(curtainDTO.getPhoto01());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto02())) {
            model.setPhoto02(curtainDTO.getPhoto02());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto03())) {
            model.setPhoto03(curtainDTO.getPhoto03());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto04())) {
            model.setPhoto04(curtainDTO.getPhoto04());
        }
        if (!StringUtils.isEmpty(curtainDTO.getPhoto05())) {
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
//        boolean equals = repository.equals(name);
//        System.out.println("CurtainService viewName");
//        System.out.println(equals);
        List<CurtainModel> yachtsModels = repository.findByName(name);
        List<CurtainDTO> yachtDTOs = convertListModelToDTO(yachtsModels);
        return yachtDTOs;
    }


    public void addNewCurtain(final String photo, final String photo01, final String photo02,
                              final String photo03, final String photo04, final String photo05,
                              final String name, final String describe, final String structure, final String paint,
                              final String height, final String color, final Double quantity, final BigDecimal price) {

        CurtainModel curtainModel = new CurtainModel();
//        System.out.println("AddNewCURTAIN:");
//        System.out.println(photo + ", ");
//        System.out.println(photo01 + ", ");
//        System.out.println(photo02 + ", ");
//        System.out.println(photo03 + ", ");
//        System.out.println(photo04 + ", ");
//        System.out.println(photo05 + ", " + name + " structure "
//                + structure + " paint " + paint + " height " + height + "color: " + color + "/");
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

    public String convertIdToName(Long id) {
        CurtainModel one = repository.findOne(id);
        CurtainDTO curtainDTO = convertModelToDto(one);
        return curtainDTO.getName();
    }

    public void deleteCurtain(List<Long> models) {
        for (Long deleteCurtain : models) {
            repository.delete(deleteCurtain);
        }
    }

    public ArrayList seeColor() {
        String[] res = null;
        List colorModel = new ArrayList();
        for (CurtainDTO col : forFilter) {
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
        for (CurtainDTO col : forFilter) {
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
        for (CurtainDTO col : forFilter) {
            colorModel.add(col.getStructure());
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
            for (CurtainDTO col : forFilter) {
                colorModel.add(col.getPrice());
            }
//        BigDecimal min = colorModel.get(0);
//        BigDecimal max = colorModel.get(0);
//
//        for (BigDecimal b : colorModel) {
//            if (b.compareTo(min) == -1) min = b;
//            if (b.compareTo(max) == 1) max = b;
//        }
            maxValue = Collections.max(colorModel);
            minValue = Collections.min(colorModel);
            BigDecimal div = new BigDecimal("3");
            BigDecimal mult = new BigDecimal("2");
            BigDecimal first = maxValue.divide(div, -2, BigDecimal.ROUND_DOWN);
            String firstValue = first.toPlainString();
            String secondValue = (first.multiply(mult)).setScale(-2, BigDecimal.ROUND_HALF_UP).toPlainString();

            list.add(firstValue);
            list.add(secondValue);
//        System.out.println("divide: "+firstValue+ " mult: "+ secondValue);
//        System.out.println("max " + maxValue);
//        System.out.println("min " + minValue);
//        System.out.println(list);
            return list;
        }
    }

//    public Double findAllRows() {
//        int allRows = repository.findAllRows();
//        System.out.println("CurtainService");
//        System.out.println("Select count rows: "+allRows);
//        Double pages = 3.0;
//        double quantityPages = Math.ceil(allRows/pages);
//        System.out.println("Matn.ceil: "+quantityPages);
//        return quantityPages;
//    }
}
