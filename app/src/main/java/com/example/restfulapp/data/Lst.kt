package com.example.restfulapp.data

import com.google.gson.annotations.SerializedName

class Lst {
    @SerializedName("gif_count")
    var gifCount   : Int = 0
    @SerializedName("gifs")
    var gifs       : ArrayList<String> = arrayListOf()
    @SerializedName("http")
    var http       : ArrayList<String> = arrayListOf()
    @SerializedName("image_count")
    var imageCount : Int = 0
    @SerializedName("images")
    var images     : ArrayList<String> = arrayListOf()
}