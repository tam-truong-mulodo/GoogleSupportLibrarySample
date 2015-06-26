package com.sample.mysamples.model.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sample.mysamples.R;
import com.sample.mysamples.model.app.AppController;
import com.sample.mysamples.model.db.entity.User;

import java.sql.SQLException;

/**
 * Created by okadaakihito on 2015/06/24.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ormExample.db";
    private static final int DATABASE_VERSION = 1;
    private Resources mResources;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mResources = AppController.getInstance().getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            // エンティティを指定してcreate tableします
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), mResources.getString(R.string.cant_create_db_message), e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        // DBのアップグレード処理（今回は割愛）
    }
}