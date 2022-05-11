package conversionTaux.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
//classe java à transformer en classe entity JPA

@Entity
@Table(name="abonne")
public class AbonneEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAbonne;
    @OneToMany
    private List<FavoriEntity> lesFavoris;
    public int getIdAbonne ()  {
        return this.idAbonne;
    }

    public void setIdAbonne (int value)  {
        this.idAbonne = value; 
    }

    private String login;
    public String getLogin ()  {
        return this.login;
    }

    public void setLogin (String value)  {
        this.login = value;
    }

    private String passwd;
    public String getPasswd ()  {
        return this.passwd;
    }

    public void setPasswd (String value)  {
        this.passwd = value; 
    }

    public AbonneEntity()
    {
    }
    
    public AbonneEntity(String login, String passwd)
    {
        this.login = login;
        this.passwd = passwd;
    }

    public List<FavoriEntity> getFavoris() {
        return lesFavoris;
    }

    public void setFavoris(List<FavoriEntity> favoriEntity) {
        this.lesFavoris = favoriEntity;
    }
}
