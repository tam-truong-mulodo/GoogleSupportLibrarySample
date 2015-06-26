package com.sample.mysamples.model.db.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by okadaakihito on 2015/06/24.
 */
@DatabaseTable(tableName = "user")
public class User {

    private static final String TAG = User.class.getSimpleName();

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String userId;
    @DatabaseField
    private String mailAddress;
    @DatabaseField
    private String password;
    @DatabaseField
    private boolean loginFlag;

    public User() {
    }

    public User(String userId, String mailAddress, String password, boolean loginFlag) {
        this.userId = userId;
        this.mailAddress = mailAddress;
        this.password = password;
        this.loginFlag = loginFlag;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(" userId : " + userId);
        str.append(" mailAddress : " + mailAddress);
        str.append(" password : " + password);
        str.append(" loginFlag : " + loginFlag);

        return str.toString();
    }
}
