package evosoft.example.com.androidplay.model;

/**
 * Created by evosoft-C01 on 2016/4/14.
 */
public class UserBean {


    /**
     * id : xxxxx
     * registerType : phone
     */

    private String id;
    private String registerType;

    public UserBean(String id, String registerType) {
        this.id = id;
        this.registerType = registerType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }
}
