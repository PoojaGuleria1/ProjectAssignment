package com.example.projectassignment.addressDetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectassignment.R;
import com.example.projectassignment.data.models.DeliveryAddress;
import com.example.projectassignment.databinding.ActivityAddressDetailBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddressDetailActivity extends AppCompatActivity {

    private ActivityAddressDetailBinding activityAddressDetailBinding;
    private GoogleMap googleMap;
    private DeliveryAddress deliveryAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViewBindings();

        Intent intent = getIntent();
        if (null != intent && null != intent.getSerializableExtra("deliveryAddress")) {
            deliveryAddress = (DeliveryAddress) intent.getSerializableExtra("deliveryAddress");
        }
        activityAddressDetailBinding.address.setText(deliveryAddress.getDescription() + " at " + "\n"+
                deliveryAddress.getLocation().getAddress());
        Glide.with(this)
                .load(deliveryAddress.getImageUrl())
                .into(activityAddressDetailBinding.image);

        initilizeMap();
        drawMarker();
    }

    //
    private void setViewBindings() {
        activityAddressDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_address_detail);
        AddressDetailViewModel addressDetailViewModel = ViewModelProviders.of(this).get(AddressDetailViewModel.class);
        activityAddressDetailBinding.setModel(addressDetailViewModel);
        activityAddressDetailBinding.setLifecycleOwner(this);
        activityAddressDetailBinding.executePendingBindings();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delivery Details");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void drawMarker() {
        double lat = deliveryAddress.getLocation().getLat();
        double lng = deliveryAddress.getLocation().getLng();
        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lng)).title("Hello Maps ");
        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(lat, lng)).zoom(12).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

}