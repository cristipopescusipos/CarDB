package siit.ro.entity;

import java.sql.Date;
import java.util.UUID;

/**
 *  The Alert class , similar with the Car class, created to have a less confusing code in the DatabaseManager
 */


public class Alert {

    private UUID idCar;
    private UUID idOwner;
    private String name;
    private String plates;
    private Date rca;
    private Date itp;
    private Date rovinieta;
    private Date fireExt;
    private Date firstAid;



    public UUID getIdCar() {
        return idCar;
    }

    public void setIdCar(UUID idCar) {
        this.idCar = idCar;
    }

    public UUID getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(UUID idOwner) {
        this.idOwner = idOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public Date getRca() {
        return rca;
    }

    public void setRca(Date rca) {
        this.rca = rca;
    }

    public Date getItp() {
        return itp;
    }

    public void setItp(Date itp) {
        this.itp = itp;
    }

    public Date getRovinieta() {
        return rovinieta;
    }

    public void setRovinieta(Date rovinieta) {
        this.rovinieta = rovinieta;
    }

    public Date getFireExt() {
        return fireExt;
    }

    public void setFireExt(Date fireExt) {
        this.fireExt = fireExt;
    }

    public Date getFirstAid() {
        return firstAid;
    }

    public void setFirstAid(Date firstAid) {
        this.firstAid = firstAid;
    }
}
