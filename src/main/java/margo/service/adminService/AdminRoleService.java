package margo.service.adminService;


import jersey.repackaged.com.google.common.collect.Lists;
import margo.dao.UserRepository;
import margo.dao.UserRoleRepository;
import margo.exception.EmailCompareWithDBException;
import margo.exception.NickNameCompareWithDBException;
import margo.exception.UserHasMoreThatOneRoleException;
import margo.exception.UserNotAuthorizedException;
import margo.filter.CheckUserRegistration;
import margo.model.modelDTO.user.UserRightsDTO;
import margo.model.modelDTO.user.UserRoleDTO;
import margo.model.user.UserModel;
import margo.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AdminRoleService {

    @Autowired
    private CheckUserRegistration userDetails;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepositiry;

    public String userRole(){
        userDetails.checkAuthenticationUser();

        UserModel userNameFromUI = userRepository.findOne(userDetails.getUserName());
            if (userNameFromUI==null){
//                System.out.println("ONLY USER String userName=\"user\"");
                return "user";
            } else {
                String name = userNameFromUI.getName();
                List<UserRole> userRoles = userNameFromUI.getUserRoles();
                UserRole userRole = userRoles.get(0);
                String role = userRole.getRole();

//                System.out.println("Name=" + name+ " ROLE: "+role);

                return role;
        }


    }
    private UserRightsDTO getUserFromList(List<UserRightsDTO> user, String name) {

        for(UserRightsDTO u: user) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public List<UserRightsDTO> getAdminRights() {
        List<UserRightsDTO> users = new ArrayList<>();
        ArrayList<UserRole> availableUserRoles = Lists.newArrayList(userRoleRepositiry.findAll());

        //для каждого пользователя из userRepositiry.findAll() выполняется forEach
        //создаем переменную, в данном случае user имя не принципиально и в эту переменную передаем функцию
        //user наполняем данными для каждого пользователя с userRepositiry.findAll()
        userRepository.findAll().forEach(user -> {
            //этим уч кода мы набираем User(ов)
            UserRightsDTO dto = new UserRightsDTO();
            dto.setName(user.getName());

            //этим уч мы набираем роли для каждого конкретного User(а) из БД
            List<String> userRoles = new ArrayList<>();
            user.getUserRoles().forEach(role -> {
                userRoles.add(role.getRole());
            });
            dto.setRole(userRoles);

            //этот участок кода сверяет какие роли относятся к User(y) на выходе мы получаем
            // список ролей с true или false

            List<UserRoleDTO> userRoleDtos = new ArrayList<>();

//          ArrayList<UserRole> availableUserRoles = Lists.newArrayList(userRoleRepositiry.findAll());
            availableUserRoles.forEach(userRoleEntity -> {
                UserRoleDTO roleDto = new UserRoleDTO();
                String roleNameInDB = userRoleEntity.getRole();

                roleDto.setNameRole(roleNameInDB);
                roleDto.setApplied(userRoles.contains(roleNameInDB));

                //тут для каждого пользователя выдаестя ответ по существующим ролям true или false
                userRoleDtos.add(roleDto);
            });

            dto.setRoleDtos(userRoleDtos);
            //получаем ответ у какого пользователя какие роли в БД
            users.add(dto);
        });

        return users;
    }

    @Transactional
    public void addRightsAdmin(List<String> roles) {
        List<UserRightsDTO> user = new ArrayList<>();
        Set<String> twoOrMoreCheckBoxTrueUsers = new HashSet<>();

        for (String role:roles) {

            String[] split = role.split("_");
            String name = split[0];
            //этот метод находит пользователя из списка

            UserRightsDTO userFromList = getUserFromList(user, name);
            if (userFromList == null) {
                //это происходит если Имя и Роль одни
                UserRightsDTO userAdminRightsDTO = new UserRightsDTO();
                userAdminRightsDTO.setName(name);
                userAdminRightsDTO.setRole(new ArrayList<>());
                userAdminRightsDTO.getRole().add(split[1]);
                user.add(userAdminRightsDTO);
            } else {
                userFromList.getRole().add(split[1]);
            }
//            System.out.println(user);
        }

        checkRoleLengthForEachUserIsLessThan2(user, twoOrMoreCheckBoxTrueUsers);

        saveDataFromUserDto(user, twoOrMoreCheckBoxTrueUsers);

    if(twoOrMoreCheckBoxTrueUsers.size()>0) {

        throw new UserHasMoreThatOneRoleException(twoOrMoreCheckBoxTrueUsers);
    }
}

    private void checkRoleLengthForEachUserIsLessThan2(List<UserRightsDTO> user, Set<String> twoOrMoreCheckBoxTrueUsersHref) {
        for (UserRightsDTO dto:user) {
            if(dto.getRole().size()>1) {
                misstake(dto.getName(),twoOrMoreCheckBoxTrueUsersHref  );
            }
        }

        if (twoOrMoreCheckBoxTrueUsersHref.size() > 0) {
            throw new UserHasMoreThatOneRoleException(twoOrMoreCheckBoxTrueUsersHref);
        }
    }

    private void saveDataFromUserDto(List<UserRightsDTO> users, Set<String> twoOrMoreCheckBoxTrueUsersHref) {
//        misstake();
        for (UserModel userEntity: userRepository.findAll()) {
            UserRightsDTO roleDTO = getUserFromList(users, userEntity.getName());

            if (roleDTO != null) {
                if (roleDTO.getRole().size() > 0) {
                    if (roleDTO.getRole().size() == userEntity.getUserRoles().size()) {

                        if (roleDTO.getRole().get(0).equals(userEntity.getUserRoles().get(0).getRole())) {
                            //здесь все ок. Озн, что вносимая роль и роль в БД совпадают
                            //continue озн, что продолжаем цикл со след элемента
                            continue;
                        } else {
                            //Ошибка
                            misstake(userEntity.getName(),twoOrMoreCheckBoxTrueUsersHref );
                        }

                    } else {
                        //означает что в БД нет роли у этого юзера
                        if (roleDTO.getRole().size() == 1) {
                            //сохранение в БД
                            saveInDB(roleDTO.getName(), roleDTO.getRole().get(0));
                            continue;
                        } else {
                            //Ошибка, т.к. озн, что поставленно нe одна птичка
                            misstake(userEntity.getName(),twoOrMoreCheckBoxTrueUsersHref );
                        }
                    }
                }
            } else {
                saveInDB(userEntity.getName(), "");
            }
        }
        if (twoOrMoreCheckBoxTrueUsersHref.size() != 0) {
            throw new UserHasMoreThatOneRoleException(twoOrMoreCheckBoxTrueUsersHref);
        }
    }

    public void saveInDB(String name, String role) {
        UserModel readUserFromDB = userRepository.findOne(name);
        UserRole roleEntity = userRoleRepositiry.findFirstByRole(role);
        if (roleEntity != null) {
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(roleEntity);
            readUserFromDB.setUserRoles(userRoles);
        } else {
            readUserFromDB.setUserRoles(new ArrayList<>());
        }

        userRepository.save(readUserFromDB);
    }

    public void misstake (String nickName, Set<String> setErroredNickNames) {

       setErroredNickNames.add(nickName);
    }

    public void addNewUser(final String name, final String password, final String phone, final String email,
                           final Integer activity, final String verification, final String description) {
        List<UserModel> byName = userRepository.findByName(name);
        List<UserModel> byEmail = userRepository.findByEmail(email);

        if (byName.size() != 0 ) {

            throw new NickNameCompareWithDBException();
        }
        if (byEmail.size() != 0 ) {

            throw new EmailCompareWithDBException();
        }

        UserModel usersModel = new UserModel();
        usersModel.setName(name);
        usersModel.setPassword(password);
        usersModel.setEmail(email);
        usersModel.setPhone((phone));
        usersModel.setDescription(description);
        usersModel.setActiveTrue(activity);
        usersModel.setVerification(verification);
        userRepository.save(usersModel);
    }

    public void checkActivationUser(){
        throw new UserNotAuthorizedException();
    }

    public void activationUser(String verification){
        List<UserModel> byVerification = userRepository.findByVerification(verification);
        for(UserModel model:byVerification){
            String name = model.getName();
//            Add role_mapping User for new users
            String role = "user";
            UserRole userRole = userRoleRepositiry.findFirstByRole(role);

            System.out.println("userRole: "+userRole);

            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(userRole);
            model.setUserRoles(userRoles);
            int k =1;
            model.setActiveTrue(k);
            userRepository.save(byVerification);
        }
        }

    }

