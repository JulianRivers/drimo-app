package com.drimo_app.model.app

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Register : Routes("Register")
    object AddDream : Routes("AddDream")
    object EditDream : Routes("EditDream")
    object Dreams : Routes("Dreams")
    object Patterns : Routes("Patterns")
    object Cycles : Routes("Cycles")

}