package com.example.biirr.model

import com.google.gson.annotations.SerializedName

data class BeerAPIResponse (
    val id: Long,
    val name: String,
    val tagline: String,

    @SerializedName( "first_brewed")
    val firstBrewed: String,

    val description: String,

    @SerializedName(  "image_url")
    val imageURL: String,

    val abv: Double,
    val ibu: Double,

    @SerializedName( "target_fg")
    val targetFg: Double,

    @SerializedName( "target_og")
    val targetOg: Double,

    val ebc: Double,
    val srm: Double,
    val ph: Double,

    @SerializedName( "attenuation_level")
    val attenuationLevel: Double,

    val volume: BoilVolume,

    @SerializedName( "boil_volume")
    val boilVolume: BoilVolume,

    val method: Method,
    val ingredients: Ingredients,

    @SerializedName( "food_pairing")
    val foodPairing: List<String>,

    @SerializedName( "brewers_tips")
    val brewersTips: String,

    @SerializedName("contributed_by")
    val contributedBy: String
)

data class BoilVolume (
    val value: Double,
    val unit: String
)

data class Ingredients (
    val malt: List<Malt>,
    val hops: List<Hop>,
    val yeast: String
)

data class Hop (
    val name: String,
    val amount: BoilVolume,
    val add: String,
    val attribute: String
)

data class Malt (
    val name: String,
    val amount: BoilVolume
)

data class Method (
    @SerializedName( "mash_temp")
    val mashTemp: List<MashTemp>,

    val fermentation: Fermentation,
    val twist: Any? = null
)

data class Fermentation (
    val temp: BoilVolume
)

data class MashTemp (
    val temp: BoilVolume,
    val duration: Long
)
