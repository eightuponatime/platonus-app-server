package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/login") {
            try {
                val user = call.receive<User>()
                val isAuthenticated = logInDatabase(user)
                if (isAuthenticated) {
                    call.respond(true)
                } else {
                    call.respond(false)
                }
            } catch (e: Exception) {
                call.respond(false)
            }
        }

        post("/changePassword") {
            try {
                val user = call.receive<User>()
                val isUpdated = updatePassword(user)
                if(isUpdated) {
                    call.respond(true)
                } else {
                    call.respond(false)
                }
            } catch(e: Exception) {
                call.respond(false)
            }
        }

        post("/addUser") {
            try {
                val student = call.receive<Student>()
                insertInDatabase(student)
                call.respond(true)
            } catch(e: Exception) {
                call.respond(false)
            }
        }

        post("/checkIfUserExists") {
            try {
                val username = call.receive<Username>()
                val existance = checkIfUserExists(username)
                if(existance) {
                    call.respond(true)
                } else {
                    call.respond(false)
                }
            } catch(e: Exception) {
                call.respond(false)
            }
        }

        get("/getAllUsersFromDatabase") {
            call.respond(getAllUsersFromDatabase())
        }

        post("/getFirstName") {
            try {
                val username = call.receive<Username>()
                val firstName = selectFirstName(username)
                call.respond(firstName)
            } catch (e: Exception) {
                call.respond("")
            }
        }

        post("/getLastName") {
            try {
                val username = call.receive<Username>()
                val lastName = selectLastName(username)
                call.respond(lastName)
            } catch (e: Exception) {
                call.respond("")
            }
        }

        post("/getPassword") {
            try {
                val username = call.receive<Username>()
                val password = selectPassword(username)
                call.respond(password)
            } catch (e : Exception) {
                call.respond("")
            }
        }

        post("/getGroup") {
            try {
                val username = call.receive<Username>()
                val group = selectGroup(username)
                call.respond(group)
            } catch(e: Exception) {
                call.respond("")
            }
        }
    }
}