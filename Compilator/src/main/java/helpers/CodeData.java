/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Dato
 */
public class CodeData {
    
    private String user;
    private int taskId;
    private String progLang;
    private String code;
    
    public CodeData(String lang, String code){
        progLang = lang;
        this.code = code;
    }
    
    public CodeData(){}

    @JsonProperty("program_lang")
    public String getProgLang() {
        return progLang;
    }

    public String getCode() {
        return code;
    }

    public String getUser() {
        return user;
    }

    @JsonProperty("task_id")
    public int getTaskId() {
        return taskId;
    }
    
    
    @JsonProperty("program_lang")
    public void setProgLang(String progLang) {
        this.progLang = progLang;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("task_id")
    public void setTaskName(int taskId) {
        this.taskId = taskId;
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
        return "CodeData:\n" + "user=" + user + ", taskId=" + taskId + ", progLang=" + progLang + ", code=" + code;
    }
    
    
}
