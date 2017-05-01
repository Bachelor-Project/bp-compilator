/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author Dato
 */
public class CodeData {
    
    private String progLang;
    private String code;
    
    public CodeData(String lang, String code){
        progLang = lang;
        this.code = code;
    }
    
    public CodeData(){}

    public String getProgLang() {
        return progLang;
    }

    public String getCode() {
        return code;
    }

    public void setProgLang(String progLang) {
        this.progLang = progLang;
    }

    public void setCode(String code) {
        this.code = code;
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
    
    
    
}
