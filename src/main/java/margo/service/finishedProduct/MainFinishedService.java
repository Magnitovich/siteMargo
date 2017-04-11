package margo.service.finishedProduct;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MainFinishedService {

    @Autowired
    private AdminRoleService adminRoleService;


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
    public ArrayList seeColor(List<AllFabricDTO> forFilter) {
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

    public ArrayList seePaint(List<AllFabricDTO> forFilter) {
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

    public ArrayList seeStructure(List<AllFabricDTO> forFilter) {
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

    public ArrayList seePrice(List<AllFabricDTO> forFilter) {
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
            BigDecimal first = maxValue.divide(div, -2, BigDecimal.ROUND_DOWN);
            String firstValue = first.toPlainString();
            String secondValue = (first.multiply(mult)).setScale(-2, BigDecimal.ROUND_HALF_UP).toPlainString();

            list.add(firstValue);
            list.add(secondValue);
            return list;
        }
    }

}
