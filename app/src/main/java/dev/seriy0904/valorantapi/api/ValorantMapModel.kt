package dev.seriy0904.valorantapi.api

import java.io.Serializable

data class ValorantMapModel(
    val `data`: List<MapInfo>,
    val status: Int
)

data class MapInfo(
    val assetPath: String,
    val callouts: List<Callouts>,
    val coordinates: String,
    val displayIcon: String?,
    val displayName: String,
    val listViewIcon: String,
    val mapUrl: String,
    val splash: String,
    val uuid: String,
    val xMultiplier: Double,
    val xScalarToAdd: Double,
    val yMultiplier: Double,
    val yScalarToAdd: Double
):Serializable

data class Callouts(
    val location: Location,
    val regionName: String,
    var superRegionName: String
):Serializable

data class Location(
    val x: Double,
    val y: Double
):Serializable