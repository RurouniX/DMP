package com.dmp.application;

import com.dmp.application.api.ApiService;

import de.greenrobot.event.EventBus;

/**
 * Created by X-Dream on 13/12/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus bus = EventBus.getDefault();
        bus.register(new ApiService());
    }
}
