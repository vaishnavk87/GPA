package inMemoryDB.entities;

import entities.account.Account;
import entities.account.Archive;
import entities.account.Semester;

import java.io.Serializable;

public class AccountImpl extends Account implements Serializable {
    private String username;
    private String password;
    private Semester semester;
    private Archive archive;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Semester getSemester() {
        return semester;
    }

    @Override
    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public Archive getArchive() {
        return archive;
    }

    @Override
    public void setArchive(Archive archive) {
        this.archive = archive;
    }
}
