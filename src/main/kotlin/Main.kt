package hngry

import io.ktor.application.Application
import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.*
import kotlinx.coroutines.*
import java.io.File

private var API_KEY = ""
private val HOME_LAT = 40.425140
private val HOME_LNG = -74.544760

suspend fun getPlaces(lat: Double, lng: Double): ResultList {


    var url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
    url += "location".makeRequestParam("$lat,$lng")
    url += "type".makeRequestParam("restaurant")
    url += "rankby".makeRequestParam("distance")
    url += "key".makeRequestParam(API_KEY)

    val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }
    val resultList: ResultList = client.get(url)

    return resultList
}

fun String.makeRequestParam(value: String): String {
    return "&$this=$value"
}

fun main() {
    val keyFile = File("src/main/resources/api-key.txt")
    API_KEY = keyFile.readText()
    val job = GlobalScope.launch {
        println("Starting request")
        println(getPlaces(HOME_LAT, HOME_LNG))
        println("End of Request")
    }

    runBlocking { job.join() }

    //val reqFile = File("src/main/resources/req-file.txt")
}

data class ResultList (val results: Array<PlaceRequest>)
data class PlaceRequest(
    val business_status: String,
    val geometry: Geometry,
    val id: String,
    val name: String,
    val opening_hours: OpeningHours,
    val price_level: Int,
    val rating: Double,
    val types: Array<String>,
    val vicinity: String
)
data class Geometry(
    val Location: Location
)
data class Location(val lat: Double, val lng: Double)
data class OpeningHours(val open_now: Boolean)