package com.coffeeshop.core.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationServiceImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : LocationService {

    private val client: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<UserLocation?> = callbackFlow {
        client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    launch {
                        val address = getAddress(location.latitude, location.longitude)
                        trySend(
                            UserLocation(
                                latitude = location.latitude,
                                longitude = location.longitude,
                                address = address
                            )
                        )
                        close()
                    }
                } else {
                    trySend(null)
                    close()
                }
            }
            .addOnFailureListener {
                trySend(null)
                close()
            }
        awaitClose { }
    }

    private suspend fun getAddress(lat: Double, lng: Double): String = withContext(Dispatchers.IO) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = suspendGeocode(geocoder, lat, lng)
            parseAddress(addresses)
        } catch (e: Exception) {
            "Location found ($lat, $lng)"
        }
    }

    private suspend fun suspendGeocode(geocoder: Geocoder, lat: Double, lng: Double): List<Address>? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            suspendCancellableCoroutine { continuation ->
                geocoder.getFromLocation(lat, lng, 1) { addresses ->
                    continuation.resume(addresses)
                }
            }
        } else {
            @Suppress("DEPRECATION")
            geocoder.getFromLocation(lat, lng, 1)
        }
    }

    private fun parseAddress(addresses: List<Address>?): String {
        if (addresses.isNullOrEmpty()) return "Unknown Location"
        val address = addresses[0]
        val city = address.locality ?: address.subAdminArea ?: ""
        val state = address.adminArea ?: ""
        return if (city.isNotEmpty()) "$city, $state" else state
    }
}
