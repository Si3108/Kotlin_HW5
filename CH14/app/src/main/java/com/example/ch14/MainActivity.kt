package com.example.ch14

import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {

        if (grantResults.isNotEmpty() && requestCode == 0) {
            for (result in grantResults)
                if (result != PackageManager.PERMISSION_GRANTED)
                    finish()
            loadMap()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMap()
    }
    override fun onMapReady(map: GoogleMap) {

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }
        else
        {
            map.isMyLocationEnabled = true
            val marker = MarkerOptions()
            marker.position(LatLng(24.9534952, 121.2261036))
            marker.title("勞壢市")
            marker.draggable(true)
            map.addMarker(marker)
            marker.position(LatLng(24.1682462740724, 120.64720841943311))
            marker.title("台灣洛聖都")
            marker.draggable(true)
            map.addMarker(marker)
            val polylineOpt = PolylineOptions()
            polylineOpt.add(LatLng(24.9534952, 121.2261036))
            //polylineOpt.add(LatLng(25.032435, 121.534905))
            polylineOpt.add(LatLng(24.1682462740724, 120.64720841943311))
            polylineOpt.color(Color.BLUE)
            val polyline = map.addPolyline(polylineOpt)
            polyline.width = 10f
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(24.1682462740724, 120.64720841943311), 13f))
        }
    }
    private fun loadMap() {
        val map = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment
        map.getMapAsync(this)
    }
}