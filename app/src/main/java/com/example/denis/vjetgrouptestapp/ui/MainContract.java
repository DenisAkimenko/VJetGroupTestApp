package com.example.denis.vjetgrouptestapp.ui;

import com.example.denis.vjetgrouptestapp.base.BasePresenter;
import com.example.denis.vjetgrouptestapp.base.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
