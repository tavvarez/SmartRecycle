package com.fiap.smartrecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fiap.smartrecycle.ui.theme.SmartRecycleTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

val brasilState = LatLng(-15.793889, -47.882778)

val defaultCameraPosition = CameraPosition.fromLatLngZoom(brasilState, 4f)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartRecycleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val cameraPositionState = rememberCameraPositionState {
                        position = defaultCameraPosition
                    }
                    GoogleMapView(
                        modifier = Modifier.height(350.dp),
                        cameraPositionState = cameraPositionState,
                        onMapLoaded = {

                        }
                    )
                    
                }
            }
        }
    }
}

@Composable
fun GoogleMapView(modifier: Modifier = Modifier, cameraPositionState: CameraPositionState, onMapLoaded: () -> Unit) {

    val locationState = rememberMarkerState()

    val mapUiSettings by remember {
        mutableStateOf(MapUiSettings(compassEnabled = false))
    }

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    GoogleMap(
        modifier = modifier,
        onMapLoaded = onMapLoaded,
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        properties =mapProperties) {

        MarkerInfoWindow {

        }

        Marker(
           state = locationState,
            draggable = true,
            onClick = {
                return@Marker false
            },
            title = "SmartRecycle"
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartRecycleTheme {

    }
}