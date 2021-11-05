package dev.seriy0904.valorantapi.api

import java.io.Serializable

data class ValorantModel(
    val `data`: List<Data>,
    val status: Int
):Serializable

data class Data(
    val abilities: List<Ability>,
    val assetPath: String,
    val bustPortrait: String,
    val characterTags: Any,
    val description: String,
    val developerName: String,
    val displayIcon: String,
    val displayIconSmall: String,
    val displayName: String,
    val fullPortrait: String,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val killFeedPortrait: String,
    val role: Role?,
    val uuid: String,
    val voiceLine: VoiceLine
):Serializable

data class Ability(
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val slot: String
):Serializable

data class Role(
    val assetPath: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val uuid: String
):Serializable

data class VoiceLine(
    val maxDuration: Double,
    val mediaList: List<Media>,
    val minDuration: Double
):Serializable

data class Media(
    val id: Int,
    val wave: String,
    val wwise: String
):Serializable