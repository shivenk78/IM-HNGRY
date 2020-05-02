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
import java.lang.NumberFormatException

private var API_KEY = ""

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

    var lat = 40.1092
    var lng = -88.2272
    try {
        println("Welcome to IM HNGRY! You must be HNGRY.")
        println("Enter search location latitude, or leave empty for Illini Union:")
        print("LATITUDE> ")
        val latStr = readLine()
        if (latStr != "") lat = latStr!!.toDouble()

        println("Enter search location longitude, or leave empty for Illini Union:")
        print("LONGITUDE> ")
        val lngStr = readLine()
        if (lngStr != "") lng = lngStr!!.toDouble()
    } catch (exception: NumberFormatException) {
        println("Invalid input, please relaunch IM HUNGRY to try again.")
    }

    var resultList: ResultList? = null

    val job = GlobalScope.launch {
        println("Starting request")
        resultList = getPlaces(lat, lng)
        println("Request fetched!")
    }

    runBlocking { job.join() }

    resultList?.results?.forEach { println(it.toString()) }
}

data class PlaceRequest(
    val business_status: String,
    val geometry: Geometry,
    val id: String,
    val name: String,
    val opening_hours: OpeningHours?,
    val price_level: Int,
    val rating: Double,
    val types: Array<String>,
    val vicinity: String
) {
    override fun toString(): String {
        val dollarStr = "$".repeat(price_level)
        val isOpen = if (opening_hours != null && opening_hours.open_now) "OPEN" else "CLOSED"
        return "$name - $rating/5 stars - $dollarStr - $isOpen - $vicinity"
    }
}

data class Geometry(
    val Location: Location
)
data class Location(val lat: Double, val lng: Double)
data class OpeningHours(val open_now: Boolean)
data class ResultList (val results: Array<PlaceRequest>)
