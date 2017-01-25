package margo.model.modelDTO.user;


import java.util.List;

public class UserRightsDTO {
    private String name;
    private List<String> role;
    private List<UserRoleDTO> roleDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public List<UserRoleDTO> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(List<UserRoleDTO> roleDtos) {
        this.roleDtos = roleDtos;
    }
}
