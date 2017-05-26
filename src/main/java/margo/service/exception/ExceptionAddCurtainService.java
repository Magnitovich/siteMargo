package margo.service.exception;



import margo.dao.fabric.*;
import margo.model.allCurtains.*;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExceptionAddCurtainService {

    @Autowired
    private CurtainService curtainService;

    @Autowired
    private ClothFabricRepository clothFabricRepository;
    @Autowired
    private CurtainRepository curtainRepository;
    @Autowired
    private OrderCurtainRepository orderCurtainRepository;
    @Autowired
    private TulleRepository tulleRepository;
    @Autowired
    private UpholsteryFabricRepository upholsteryFabricRepository;

    final String clothFabric = "clothFabric";
    final String curtainFabric = "curtain";
    final String orderFabric = "orderCurtain";
    final String tulleFabric = "tulle";
    final String upholsteryFabric = "upholsteryFabric";

    public void compareEnterInfoAndInDB(final String photo, final String photo01, final String photo02,
                                                      final String photo03, final String photo04, final String photo05,
                                                      final String name, final String describe, final String structure,
                                                      final String paint, final String height, final String color,
                                                      final Double quantity, final BigDecimal price, String part) {
        switch (part) {
            case upholsteryFabric:
                UpholsteryFabricModel upholsteryFabricModel = new UpholsteryFabricModel();
                if ((upholsteryFabricRepository.findByPhoto(photo)).size() == 0 && (upholsteryFabricRepository.findByName(name)).size() == 0) {
                    curtainService.addNewCurtain(photo, photo01, photo02, photo03, photo04, photo05, name, describe,
                            structure, paint, height, color, quantity, price, upholsteryFabricRepository, upholsteryFabricModel);
                    break;
                } else {
                    throw new RuntimeException("WOW");
                }

            case tulleFabric:
                TulleModel tulleModel = new TulleModel();
                if ((tulleRepository.findByPhoto(photo)).size() == 0 && (tulleRepository.findByName(name)).size() == 0) {
                    curtainService.addNewCurtain(photo, photo01, photo02, photo03, photo04, photo05, name, describe,
                            structure, paint, height, color, quantity, price, tulleRepository, tulleModel);
                    break;
                } else {
                    throw new RuntimeException("WOW");
                }

            case orderFabric:
                OrderCurtainModel orderCurtainModel = new OrderCurtainModel();
                if ((orderCurtainRepository.findByPhoto(photo)).size() == 0 && (orderCurtainRepository.findByName(name)).size() == 0) {
                    curtainService.addNewCurtain(photo, photo01, photo02, photo03, photo04, photo05, name, describe,
                            structure, paint, height, color, quantity, price, orderCurtainRepository, orderCurtainModel);
                    break;
                } else {
                    throw new RuntimeException("WOW");
                }

            case curtainFabric:
                CurtainModel curtainModel = new CurtainModel();
                if ((curtainRepository.findByPhoto(photo)).size() == 0 && (curtainRepository.findByName(name)).size() == 0) {
                    curtainService.addNewCurtain(photo, photo01, photo02, photo03, photo04, photo05, name, describe,
                            structure, paint, height, color, quantity, price, curtainRepository, curtainModel);
                    break;
                } else {
                    throw new RuntimeException("WOW");
                }
            case clothFabric:
                ClothFabricModel clothFabricModel = new ClothFabricModel();
                if ((clothFabricRepository.findByPhoto(photo)).size() == 0 && (clothFabricRepository.findByName(name)).size() == 0) {
                    curtainService.addNewCurtain(photo, photo01, photo02, photo03, photo04, photo05, name, describe,
                            structure, paint, height, color, quantity, price, clothFabricRepository, clothFabricModel);
                    break;
                } else {
                    throw new RuntimeException("WOW");
                }

        }
    }

}
