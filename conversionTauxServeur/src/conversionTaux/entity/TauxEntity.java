package conversionTaux.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

//classe java à transformer en classe entity JPA

@Entity
@Table(name="taux")
public class TauxEntity  implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idTaux;
    public int getIdTaux() {
        return this.idTaux;
    }
    
    public void setIdTaux(int idTaux) {
        this.idTaux = idTaux;
    }

    private String monnaieA;
    public String getMonnaieA() {
        return this.monnaieA;
    }
    
    public void setMonnaieA(String MonnaieA) {
        this.monnaieA = MonnaieA;
    }

    private String monnaieB;
    public String getMonnaieB() {
        return this.monnaieB;
    }

    public void setMonnaieB(String MonnaieB) {
        this.monnaieB = MonnaieB;
    }

    private double taux;
    public double getTaux() {
        return this.taux;
    }

     public void setTaux(double taux) {
        this.taux = taux;
    }

    public TauxEntity() {
    }

    public TauxEntity(int idTaux, String monnaieA, String monnaieB, double taux)
    {
        this.idTaux = idTaux;
        this.monnaieA = monnaieA;
        this.monnaieB = monnaieB;
        this.taux = taux;
    }
    public TauxEntity(String monnaieA, String monnaieB, double taux)
    {
        this.idTaux = idTaux;
        this.monnaieA = monnaieA;
        this.monnaieB = monnaieB;
        this.taux = taux;
    }
}
