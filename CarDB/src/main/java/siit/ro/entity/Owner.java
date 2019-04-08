package siit.ro.entity;

import java.util.UUID;

public class Owner {
    private UUID idOwner;
    private String name;
    private String telephone;
    private String email;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
