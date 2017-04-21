package margo.model.modelDTO.finishProductDTO;


public class SewedDTO {

    private String id;
    private String sewed;

    @Override
    public String toString() {
        return "SewedDTO{" +
                "id='" + id + '\'' +
                ", sewed='" + sewed + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSewed() {
        return sewed;
    }

    public void setSewed(String sewed) {
        this.sewed = sewed;
    }
}
