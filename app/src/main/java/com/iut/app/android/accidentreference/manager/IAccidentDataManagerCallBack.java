package com.iut.app.android.accidentreference.manager;

import com.iut.app.android.accidentreference.model.Accidents;

public interface IAccidentDataManagerCallBack {

    void getAccidentsResponseSuccess(Accidents accidents);

    void getAccidentsError(String message);

}
