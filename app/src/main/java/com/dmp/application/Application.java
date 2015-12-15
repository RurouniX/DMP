package com.dmp.application;

import com.dmp.api.ApiService;
import com.dmp.dao.DAOService;
import com.orm.SugarApp;
import com.orm.SugarContext;

import de.greenrobot.event.EventBus;

/**
 * Created by X-Dream on 13/12/15.
 */
public class Application extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus bus = EventBus.getDefault();
        SugarContext.init(this);
        bus.register(new ApiService());
        bus.register(new DAOService());
    }
}
