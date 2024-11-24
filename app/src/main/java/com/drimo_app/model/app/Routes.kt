package com.drimo_app.model.app

sealed class Routes(val route: String) {
    object Splash : Routes("Splash")
    object Login : Routes("Login")
    object Register : Routes("Register")
    object AddDream : Routes("AddDream")
    object EditDream : Routes("EditDream/{id}") {
        fun createRoute(id: Int): String = "EditDream/$id"
    }
    object Dreams : Routes("Dreams")
    object Patterns : Routes("Patterns")
    object Cycles : Routes("Cycles")
    object CyclesResult : Routes("CyclesResult")

}