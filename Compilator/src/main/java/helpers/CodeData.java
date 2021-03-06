/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dato
 */
@XmlRootElement
public class CodeData {
    
    private String user;
    private String taskName;
    private String progLang;
    private String code;
    
    public CodeData(String lang, String code){
        progLang = lang;
        this.code = code;
    }
    
    public CodeData(){}

    public String getLang() {
        return progLang;
    }

    public String getCode() {
        return code;
    }

    public String getUser() {
        return user;
    }

    public String getTaskName() {
        return taskName;
    }
    
    
    public void setLang(String progLang) {
        this.progLang = progLang;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTaskName(String name) {
        this.taskName = name;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CodeData other = (CodeData) obj;
        if (!progLang.equals(other.progLang)) {
            return false;
        }
        return code.equals(other.getCode());
    }

    @Override
    public String toString() {
        return "CodeData:\n" + "user=" + user + ", taskName=" + taskName + ", progLang=" + progLang + ", code=" + code;
    }
    
    
}
