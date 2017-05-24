package margo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSISTENT_LOGINS", schema = "public")
public class PersistentModel {
    @Id
    @Column(name = "series", columnDefinition="VARCHAR(64)")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String series;
    @Column(nullable = false, columnDefinition="VARCHAR(64)")
    private String username;
    @Column(name = "token", nullable = false, columnDefinition="VARCHAR(64)")
    private String token;
    @Column(name = "last_used", nullable = false, columnDefinition="VARCHAR(64)")
    private Date last_used;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_used() {
        return last_used;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }
}
