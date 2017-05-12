package margo.service.serviceMargo;

import margo.dao.serviceMargo.ServiceMargoRepository;
import margo.model.modelDTO.serviceMargo.ServiceMargoDTO;
import margo.model.serviceMargo.ServiceMargoModel;
import margo.service.adminService.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMargo {

    @Autowired
    private ServiceMargoRepository serviceMargoRepository;
    @Autowired
    private AdminRoleService adminRoleService;

    private List<ServiceMargoModel> forFilter = new ArrayList<>();

    public ServiceMargoDTO convertModelToDTO(ServiceMargoModel margoModel){
        ServiceMargoDTO dto = new ServiceMargoDTO();
        dto.setPhoto(margoModel.getPhoto());
        dto.setName(margoModel.getName());
        dto.setId(margoModel.getId());
        dto.setDescription(margoModel.getDescription());
        dto.setQuantity(margoModel.getQuantity());
        dto.setPrice(margoModel.getPrice());
        return dto;
    }
    public List<ServiceMargoDTO> convertListModelToDTO(List<ServiceMargoModel> list){
        List<ServiceMargoDTO> dtos = new ArrayList<>();
        for (ServiceMargoModel margoModel : list ){
            ServiceMargoDTO serviceMargoDTO = convertModelToDTO(margoModel);
            dtos.add(serviceMargoDTO);
        }
        return dtos;
    }
    public Iterable<ServiceMargoDTO> seeAll(){
        List<ServiceMargoDTO> iterable = convertListModelToDTO((List<ServiceMargoModel>) serviceMargoRepository.findAll());
        return checkOnAuthentication(iterable);
    }
    public List<ServiceMargoDTO> checkOnAuthentication(List<ServiceMargoDTO> fabricDTOs){
        List<ServiceMargoDTO> curtainDTOsWithZERO = new ArrayList<>(); //if commodity =0
        String role = adminRoleService.userRole();
        if (role.equals("user")) {
//       Hiding of items which are equal to 0
            for (ServiceMargoDTO curtain : fabricDTOs) {
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
    public ServiceMargoDTO viewSelectedFinishProduct(Long id) {
        ServiceMargoModel model = serviceMargoRepository.findOne(id);
        ServiceMargoDTO finishDTO = convertModelToDTO(model);
        return finishDTO;
    }
    public String convertIdToName(Long id) {
        ServiceMargoModel one = serviceMargoRepository.findOne(id);
        ServiceMargoDTO dto = convertModelToDTO(one);
        return dto.getName();
    }
    public ServiceMargoDTO findSelectedModel(Long id){
        ServiceMargoModel product = serviceMargoRepository.findOne(id);
        ServiceMargoDTO allFabricDTO = convertModelToDTO(product);
        return allFabricDTO;
    }
    public void delete(List<Long> models) {
        for (Long deleteCurtain : models) {
            serviceMargoRepository.delete(deleteCurtain);
        }
    }
    public List<ServiceMargoDTO> viewPhoto(String photo) {
        return convertListModelToDTO(serviceMargoRepository.findByPhoto(photo));
    }

    public List<ServiceMargoDTO> viewName(String name) {
        return convertListModelToDTO(serviceMargoRepository.findByName(name));
    }
    @Transactional
    public void addNewInformation(final String photo, final String name, final String describe,
                                  final Double quantity, final BigDecimal price) {
        ServiceMargoModel model = new ServiceMargoModel();

        model.setPhoto(photo);
        model.setName(name);
        model.setDescription(describe);
        model.setQuantity(quantity);
        model.setPrice(price);
        serviceMargoRepository.save(model);
    }
    @Transactional
    public void editCurtain(ServiceMargoDTO dto) {
        ServiceMargoModel model = serviceMargoRepository.findOne(dto.getId());

        if (!StringUtils.isEmpty(dto.getPhoto())) {
            model.setPhoto(dto.getPhoto());
        }
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setQuantity(dto.getQuantity());
        model.setPrice(dto.getPrice());

        serviceMargoRepository.save(model);
    }
}
