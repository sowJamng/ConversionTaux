package conversionTaux.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="favori")
public class FavoriEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavori;
    private  String libelleFavori;
    @ManyToOne
    private  TauxEntity leTaux;
    public FavoriEntity(){}

    public void setId(int id) {
        this.idFavori = id;
    }

    public int getId() {
        return idFavori;
    }

    public String getLibelleFavori() {
        return libelleFavori;
    }

    public void setLibelleFavori(String libelleFavori) {
        this.libelleFavori = libelleFavori;
    }

    public TauxEntity getTauxEntity() {
        return leTaux;
    }

    public void setTauxEntity(TauxEntity tauxEntity) {
        this.leTaux = tauxEntity;
    }
}
