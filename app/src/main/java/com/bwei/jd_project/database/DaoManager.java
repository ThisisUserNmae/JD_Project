package com.bwei.jd_project.database;

import android.content.Context;


public class DaoManager {

    private static DaoManager daoManager;

    private final DaoSession daoSession;

    public DaoManager(Context context) {

        daoSession = DaoMaster.newDevSession(context, "product.db");

    }

    public static  DaoManager getIntent(Context context){

        if (daoManager == null){

            synchronized (DaoManager.class){

                if (daoManager == null){

                    daoManager = new DaoManager(context);

                }

            }

        }

        return daoManager;
    }

    public DaoSession getDaoSession() {

        return daoSession;

    }
}
