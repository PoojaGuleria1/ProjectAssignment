package com.example.projectassignment.dagger;


import android.provider.Telephony;

import com.example.projectassignment.App;
import com.example.projectassignment.adapter.AddressItemViewModel;
import com.example.projectassignment.addresslist.AddressActivity;
import com.example.projectassignment.addresslist.AddressRepo;
import com.example.projectassignment.addresslist.AddressUsecase;
import com.example.projectassignment.addresslist.AddressViewModel;

import javax.inject.Singleton;

import dagger.Component;

//This class is used for injecting Fragment, View Models, Use cases using Dagger
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {

    void inject(App assignmentApplication);
    void inject(AddressItemViewModel addressItemViewModel);
    void inject(AddressViewModel addressViewModel);
    void inject(AddressRepo addressRepo);
    void inject(AddressUsecase addressUsecase);
    void inject(AddressActivity addressActivity);

}
