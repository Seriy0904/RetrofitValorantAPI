package dev.seriy0904.valorantapi.Api

data class ValorantModel(
    val `data`: List<Data>,
    val status: Int
)

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
    val killfeedPortrait: String,
    val role: Role,
    val uuid: String,
    val voiceLine: VoiceLine
)

data class Ability(
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val slot: String
)

data class Role(
    val assetPath: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val uuid: String
)

data class VoiceLine(
    val maxDuration: Double,
    val mediaList: List<Media>,
    val minDuration: Double
)

data class Media(
    val id: Int,
    val wave: String,
    val wwise: String
)