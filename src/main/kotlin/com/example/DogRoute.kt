package com.example

import io.ktor.http.*
import io.ktor.http.cio.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.BASE
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.img

const val BASE_URL = "https://raw.githubusercontent.com/encorex32268/com.example.takmingclassmate/master/src/main/resources/static/dog/"

val dogPhotos = (1..22).map {
    String.format("%02d",it) + ".jpg"
}


fun Route.getDog(){
    get("/dog/{index}") {
        try {
            val index = call.parameters["index"]?.toInt()
            index?.let {
                val result = dogPhotos[index]
                call.respondHtml {
                    body {
                        img(
                            src = BASE_URL + result
                        ) {
                            width = "480"
                            height = "600"
                        }
                    }
                }
            }
        }catch (e : IndexOutOfBoundsException){
            call.respond(
                HttpStatusCode.ExpectationFailed,
                "Error:IndexOutOfBoundsException"
            )
        }catch (e : NumberFormatException){
            call.respond(
                HttpStatusCode.ExpectationFailed,
                "Error:NumberFormatException"
            )
        }
    }
}

fun Route.randomDog(){
    get("/dog"){
        try {
            val result = dogPhotos.random()
            call.respondHtml {
                body {
                    img(
                        src = BASE_URL + result
                    ) {
                        width = "480"
                        height = "600"
                    }
                }
            }
        }catch (e : IndexOutOfBoundsException){
            call.respond(
                HttpStatusCode.ExpectationFailed,
                "Error:IndexOutOfBoundsException"
            )
        }
    }
}

fun Route.allDog(){
    get("/all"){
        try {
            call.respondHtml {
                body {
                    div {
                        dogPhotos.forEach {
                            div {
                                img(
                                    src = BASE_URL + it
                                ) {
                                    width = "480"
                                    height = "600"
                                }
                            }

                        }
                    }

                }
            }
        }catch (e : IndexOutOfBoundsException){
            call.respond(
                HttpStatusCode.ExpectationFailed,
                "Error:IndexOutOfBoundsException"
            )
        }
    }
}