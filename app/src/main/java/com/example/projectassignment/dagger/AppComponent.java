package com.example.projectassignment.dagger;



import com.example.projectassignment.App;
import com.example.projectassignment.adapter.AddressItemViewModel;
import com.example.projectassignment.addresslist.AddressRepo;
import com.example.projectassignment.addresslist.AddressDataSource;

import javax.inject.Singleton;

import dagger.Component;

//This class is used for injecting Fragment, View Models, Use cases using Dagger
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {

    void inject(App assignmentApplication);
    void inject(AddressItemViewModel addressItemViewModel);
   // void inject(AddressViewModel addressViewModel);
    void inject(AddressRepo addressRepo);
    //void inject(AddressUsecase addressUsecase);
   // void inject(ItemDataSourceFactory itemDataSourceFactory);
    void inject(AddressDataSource addressDataSource);

}
