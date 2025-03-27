package com.priya.mapapp4;

import android.content.res.AssetManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try{
            AssetManager am=getAssets();
            InputStream is=am.open("asideaairceltr.xls");
            Workbook wb=Workbook.getWorkbook(is);
            Sheet s=wb.getSheet(0);
            //int row=s.getRows();
            //int col=s.getColumns();
            double lati=0;
            double longi=0;
            String str1=" ";
            String str2=" ";
            for(int i=0;i<1692;i++)
            {
                for(int c=0;c<4;c++)
                {
                    if(c==0)
                    {
                        Cell z=s.getCell(c,i);
                        lati=Double.parseDouble(z.getContents());
                    }
                    else if (c==1)
                    {
                        Cell y=s.getCell(c,i);
                        longi=Double.parseDouble(y.getContents());
                    }
                    else if (c==2)
                    {
                        Cell x=s.getCell(c,i);
                        str1=x.getContents();

                    }
                    else
                    {
                        Cell w=s.getCell(c,i);
                        str2=w.getContents();
                    }
                }

                mMap.addMarker(new MarkerOptions().position(new LatLng(lati,longi)).title(str1+": "+str2).snippet("Latitude: "+lati+" Longitude: "+longi).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lati,longi)));
            }
            for(int i=1693;i<=4038;i++)
            {
                for(int c=0;c<4;c++)
                {
                    if(c==0)
                    {
                        Cell z=s.getCell(c,i);
                        lati=Double.parseDouble(z.getContents());
                    }
                    else if (c==1)
                    {
                        Cell y=s.getCell(c,i);
                        longi=Double.parseDouble(y.getContents());
                    }
                    else if (c==2)
                    {
                        Cell x=s.getCell(c,i);
                        str1=x.getContents();

                    }
                    else
                    {
                        Cell w=s.getCell(c,i);
                        str2=w.getContents();
                    }
                }

                mMap.addMarker(new MarkerOptions().position(new LatLng(lati,longi)).title(str1+": "+str2).snippet("Latitude: "+lati+" Longitude: "+longi).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lati,longi)));
            }

        }
        catch(Exception e)
        {

        }

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
