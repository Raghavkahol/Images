package com.example.myapplication.data
data class Photo (

	val id : Long,
	val owner : String,
	val secret : String,
	val server : Int,
	val farm : Int,
	val title : String
)