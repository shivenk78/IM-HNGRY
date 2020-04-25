package hngry

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.*

private val API_KEY = "AIzaSyAkJ3TBOV2UlqNqmXgZNH6AbrIhWA_6w9Q"
private val HOME_LAT = 40.425140
private val HOME_LNG = -74.544760

suspend fun getPlaces(lat: Double, lng: Double) : String {

    var url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
    url += "location".makeRequestParam("$lat,$lng")
    url += "type".makeRequestParam("restaurant")
    url += "rankby".makeRequestParam("distance")
    url += "key".makeRequestParam(API_KEY);

    val client = HttpClient()

    val request = client.get<String>(url)
    return request
}

fun String.makeRequestParam(value: String) : String{
    return "&$this=$value"
}

fun main() = runBlocking {
    println ("Starting request")

    println(getPlaces(HOME_LAT, HOME_LNG))

    println ("End of Request")
}