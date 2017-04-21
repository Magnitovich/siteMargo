//package margo.service.finishedProduct;
//
//import margo.controller.add.AddPattern;
//import margo.dao.finishProduct.*;
//import margo.model.finishedProduct.AllFinishProductModel;
//import margo.model.finishedProduct.BedroomModel;
//import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
//import margo.service.exception.finishProduct.ExceptionAddFinishProduct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class AddEditService {
//
//    @Autowired
//    private MainFinishedService mainFinishedService;
//
//    @Value("${img.bedroom.path}")
//    private String realObjectsPathBedroom;
//    @Value("${img.bedroom.relative.path}")
//    private String relativeObjectsPathBedroom;
//    @Value("${img.cabinet.path}")
//    private String realObjectsPathCabinet;
//    @Value("${img.cabinet.relative.path}")
//    private String relativeObjectsPathCabinet;
//    @Value("${img.childroom.path}")
//    private String realObjectsPathChildroom;
//    @Value("${img.childroom.relative.path}")
//    private String relativeObjectsPathChildroom;
//    @Value("${img.curtainFinishProduct.path}")
//    private String realObjectsPathCurtainFinishProduct;
//    @Value("${img.curtainFinishProduct.relative.path}")
//    private String relativeObjectsPathCurtainFinishProduct;
//    @Value("${img.tulleFinishProduct.path}")
//    private String realObjectsPathTulleFinishProduct;
//    @Value("${img.tulleFinishProduct.relative.path}")
//    private String relativeObjectsPathTulleFinishProduct;
//    @Value("${img.guestroom.path}")
//    private String realObjectsPathGuestroom;
//    @Value("${img.guestroom.relative.path}")
//    private String relativeObjectsPathGuestroom;
//    @Value("${img.kitchen.path}")
//    private String realObjectsPathKitchen;
//    @Value("${img.kitchen.relative.path}")
//    private String relativeObjectsPathKitchen;
//    @Value("${img.lambr.path}")
//    private String realObjectsPathLamr;
//    @Value("${img.lambr.relative.path}")
//    private String relativeObjectsPathLambr;
//
//    @Autowired
//    private ExceptionAddFinishProduct exceptionAddFinishProduct;
//
//    private final String bedroom = "bedroom";
//    private final String cabinet = "cabinet";
//    private final String children = "children";
//    private final String guestroom = "guestroom";
//    private final String kitchen = "kitchen";
//    private final String lambr = "lambr";
//    private final String curtFinish = "curtFinish";
//    private final String tulleFinish = "tulleFinish";
//
//    private CrudRepository sortingRepository = null;
//    @Autowired
//    private BedroomRepository bedroomRepository;
//    @Autowired
//    private CabinetRepository cabinetRepository;
//    @Autowired
//    private ChildrenRepository childrenRepository;
//    @Autowired
//    private CurtainFinishRepository curtainFinishRepository;
//    @Autowired
//    private GuestroomRepository guestroomRepository;
//    @Autowired
//    private KitchenRepository kitchenRepository;
//    @Autowired
//    private LambrequinRepository lambrequinRepository;
//    @Autowired
//    private TulleFinishRepository tulleFinishRepository;
//
//    private List<AllFabricDTO> fabricDTOList = null;
//
//    public List<AllFabricDTO> addEditForm(String part, BindingResult result, AllFabricDTO dto)throws IOException {
//
//        AddPattern addPattern = new AddPattern();
//        switch (part) {
//            case bedroom:
//                addPattern.checkInformations(dto, realObjectsPathBedroom, relativeObjectsPathBedroom);
//                sortingRepository = bedroomRepository;
//                break;
//            case cabinet:
//                addPattern.checkInformations(dto, realObjectsPathCabinet, relativeObjectsPathCabinet);
//                sortingRepository = cabinetRepository;
//                break;
//            case children:
//                addPattern.checkInformations(dto, realObjectsPathChildroom, relativeObjectsPathChildroom);
//                sortingRepository = childrenRepository;
//                break;
//            case guestroom:
//                addPattern.checkInformations(dto, realObjectsPathGuestroom, relativeObjectsPathGuestroom);
//                sortingRepository = guestroomRepository;
//                break;
//            case kitchen:
//                addPattern.checkInformations(dto, realObjectsPathKitchen, relativeObjectsPathKitchen);
//                sortingRepository = kitchenRepository;
//                break;
//            case lambr:
//                addPattern.checkInformations(dto, realObjectsPathLamr, relativeObjectsPathLambr);
//                sortingRepository = lambrequinRepository;
//                break;
//            case curtFinish:
//                addPattern.checkInformations(dto, realObjectsPathCurtainFinishProduct, relativeObjectsPathCurtainFinishProduct);
//                sortingRepository = curtainFinishRepository;
//                break;
//            case tulleFinish:
//                addPattern.checkInformations(dto, realObjectsPathTulleFinishProduct, relativeObjectsPathTulleFinishProduct);
//                sortingRepository = tulleFinishRepository;
//                break;
//        }
//        if (dto.getIdForEditCurtain() != null) {
//            dto.setPhoto(addPattern.getNameFile());
//            dto.setPhoto01(addPattern.getNameFile01());
//            dto.setPhoto02(addPattern.getNameFile02());
//            dto.setPhoto03(addPattern.getNameFile03());
//            dto.setPhoto04(addPattern.getNameFile04());
//            dto.setPhoto05(addPattern.getNameFile05());
//            editCurtain(dto);
//            return fabricDTOList;
//        } else {
//            try {
//                List<AllFabricDTO> allFabricDTOs = exceptionAddFinishProduct.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
//                        addPattern.getNameFile02(), addPattern.getNameFile02(), addPattern.getNameFile03(),
//                        addPattern.getNameFile05(), dto.getName(), dto.getDescription(), dto.getStructure(),
//                        dto.getPaint(), dto.getHeight(), dto.getColor(), dto.getQuantity(), dto.getPrice(),
//                        dto.getItIsSewed(), part);
//                    return allFabricDTOs;
//            }  catch (RuntimeException r) {
//                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
//                return (List<AllFabricDTO>) viewException();
//            }
//            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
//            finally {
//                if (addPattern.getFileOutputStream() != null) {
//                    addPattern.getFileOutputStream().close();
//                }
//            }
//        }
//
//    }
//
//    @Transactional
//    public void editCurtain(AllFabricDTO curtainDTO) {
////        CurtainModel model = repository.findOne(curtainDTO.getId());
//        AllFinishProductModel model = (AllFinishProductModel) sortingRepository.findOne(curtainDTO.getId());
//
//        if (!StringUtils.isEmpty(curtainDTO.getPhoto())) {
//            model.setPhoto(curtainDTO.getPhoto());
//        }
//        if (!StringUtils.isEmpty(curtainDTO.getPhoto01())) {
//            model.setPhoto01(curtainDTO.getPhoto01());
//        }
//        if (!StringUtils.isEmpty(curtainDTO.getPhoto02())) {
//            model.setPhoto02(curtainDTO.getPhoto02());
//        }
//        if (!StringUtils.isEmpty(curtainDTO.getPhoto03())) {
//            model.setPhoto03(curtainDTO.getPhoto03());
//        }
//        if (!StringUtils.isEmpty(curtainDTO.getPhoto04())) {
//            model.setPhoto04(curtainDTO.getPhoto04());
//        }
//        if (!StringUtils.isEmpty(curtainDTO.getPhoto05())) {
//            model.setPhoto05(curtainDTO.getPhoto05());
//        }
//        model.setName(curtainDTO.getName());
//        model.setDescription(curtainDTO.getDescription());
//        model.setStructure(curtainDTO.getStructure());
//        model.setPaint(curtainDTO.getPaint());
//        model.setHeight(curtainDTO.getHeight());
//        model.setColor(curtainDTO.getColor());
//        model.setQuantity(curtainDTO.getQuantity());
//        model.setPrice(curtainDTO.getPrice());
//
//        sortingRepository.save(model);
//        Iterable<AllFinishProductModel> models =  sortingRepository.findAll();
//        fabricDTOList = mainFinishedService.convertListModelToDTO((List<AllFinishProductModel>) models);
//    }
//
//    public ModelAndView viewException() {
//
//        ModelAndView andView = new ModelAndView();
//        andView.setViewName("finishedProduct/add/addBedroom");
//        return andView;
//
//    }
//}
