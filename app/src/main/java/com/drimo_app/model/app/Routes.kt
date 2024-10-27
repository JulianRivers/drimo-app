package com.drimo_app.model.app

sealed class Routes(val route:String) {
    object Login:Routes("Login")
    object Register:Routes("Register")

    object AddDream:Routes("AddDream")

}