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
    private Integer mId;
    @DatabaseField
    private String mUserId;
    @DatabaseField
    private String mMailAddress;
    @DatabaseField
    private String mPassword;
    @DatabaseField
    private boolean mLoginFlag;

    public User() {
    }

    public User(String userId, String mailAddress, String password, boolean loginFlag) {
        this.mUserId = userId;
        this.mMailAddress = mailAddress;
        this.mPassword = password;
        this.mLoginFlag = loginFlag;
    }

    public User(String userId) {
        this.mUserId = userId;
    }

    public Integer getId() {
        return this.mId;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String userId) {
        this.mUserId = userId;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(" userId : " + mUserId);
        str.append(" mailAddress : " + mMailAddress);
        str.append(" password : " + mPassword);
        str.append(" loginFlag : " + mLoginFlag);

        return str.toString();
    }
}
