package com.example.projectassignment.addresslist;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.projectassignment.App;
import com.example.projectassignment.dagger.AppComponent;
import com.example.projectassignment.data.models.DeliveryAddress;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AddressViewModel extends AndroidViewModel {

    @Inject
    AddressUsecase addressUsecase;

    private List<DeliveryAddress> deliveryAddressList;
    public MutableLiveData<Boolean> addressDataChange = new MutableLiveData<>();


    public AddressViewModel(Application application) {
        super(application);
        ((App) getApplication()).getAppComponent().inject(this);
        deliveryAddressList = new ArrayList<>();
    }


    /**
     * Method to get movies list from repository via use case (local or remote)
     *
     * @param : context of the calling activity or fragment, required to register observe callback
     */
    public void getAddressList(Context context) {
        //calling desire use case method to invoke repository
        MutableLiveData<List<DeliveryAddress>> addressListMutableLiveData = addressUsecase.getAddressList();
        addressListMutableLiveData.observe((LifecycleOwner) context, new Observer<List<DeliveryAddress>>() {
            @Override
            public void onChanged(@Nullable List<DeliveryAddress> addresses) {
                addressDataChange.setValue(true);
                if (null != addresses && !addresses.isEmpty()) {
                    deliveryAddressList.addAll(addresses);

                }
            }
        });
    }

    //getter method for movies list
    public List<DeliveryAddress> getDeliveryAddressList() {
        return deliveryAddressList;
    }


}
