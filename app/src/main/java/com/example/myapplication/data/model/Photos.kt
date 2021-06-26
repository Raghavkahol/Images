package com.example.myapplication.data.model

import com.example.myapplication.data.model.Photo

data class Photos (
	val page : Int,
	val pages : Int,
	val perpage : Int,
	val total : Int,
	val photo : List<Photo>
)