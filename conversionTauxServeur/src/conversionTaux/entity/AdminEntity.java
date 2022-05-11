package conversionTaux.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="admin")
public class AdminEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idAdmin;
    private String login ;
    private String passwd;
 public AdminEntity(){}
    
    public void setId(int id) {
        this.idAdmin = id;
    }

    @Id
    public int getId() {
        return idAdmin;
    }
 
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
