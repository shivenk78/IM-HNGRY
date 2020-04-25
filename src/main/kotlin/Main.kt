package hngry

import io.ktor.client.*
import io.ktor.client.request.*

private val API_KEY = "AIzaSyAkJ3TBOV2UlqNqmXgZNH6AbrIhWA_6w9Q"

suspend fun getPlaces(lat: Double, lng: Double, keyword: String) {

    var url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"

    url += makeRequestParam("key", API_KEY);

    val client = HttpClient()

    val firstBytes = client.get<ByteArray>()
}

fun makeRequestParam(name: String, value: String) : String{
    return "&$name=$value"
}

fun main() {

}