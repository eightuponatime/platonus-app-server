package com.example.plugins

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.net.http.HttpResponse

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.util.*
import io.ktor.util.Identity.decode
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json


fun Application.configureRouting() {
    routing {


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

        post("/changeUsername") {
            try {
                val user = call.receive<UpdateUsername>()
                val isUpdated = updateUsername(user)
                if(isUpdated) {
                    call.respond(true)
                } else {
                    call.respond(false)
                }
            } catch(e: Exception) {
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

        post("/getPlan") {
            try {
                val username = call.receive<Username>()
                val plan = selectPlan(username)
                call.respond(plan)
            } catch(e: Exception) {
                call.respond("")
            }
        }

        post("/getSchedule") {
            try {
                val username = call.receive<Username>()
                val schedule = selectSchedule(username)
                call.respond(schedule)
            } catch(e: Exception) {
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

        val json = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }

        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(json)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 120000
            }

        }

        get("/") {
            val response: HelloResponse = withContext(Dispatchers.IO) {
                val responser = client.get("http://localhost:3000/hello")
                val kek = responser.body<String>()
                json.decodeFromString(kek)
            }
            call.respond(HttpStatusCode.OK, response.message)
        }


        post("/parse-schedule") {
            try {
                val user = call.receive<User>()
                println("Sending request with user: $user")

                runBlocking {
                    val response = client.post("http://localhost:3000/parse") {
                        contentType(ContentType.Application.Json)
                        setBody(user)
                        timeout {
                            connectTimeoutMillis = 120_000
                            socketTimeoutMillis = 120_000 }
                    }
                    //delay(120_000)
                    val result = response.body<String>()
                    println("Received response: $result")

                    call.respond(result)
                }
            } catch (e: Exception) {
                println("Exception caught: ${e.message}")
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("/parse-plan") {
            try {
                val user = call.receive<User>()
                println("Sending request with user: $user")

                runBlocking {
                    val response = client.post("http://localhost:3000/parse-plan") {
                        contentType(ContentType.Application.Json)
                        setBody(user)
                        timeout {
                            connectTimeoutMillis = 120_000
                            socketTimeoutMillis = 120_000
                        }
                    }
                    val result = response.body<String>()
                    println("Received response: $result")

                    call.respond(result)
                }
            } catch(e: Exception) {
                println("Exception caught: ${e.message}")
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("/insertSchedule" ) {
            try {
                val schedule = call.receive<Schedule>()
                insertScheduleInDatabase(schedule)
                call.respond(true)
            } catch(e: Exception) {
                call.respond(false)
            }
        }

        post("/insertPlan") {
            try {
                val plan = call.receive<Plan>()
                insertPlanInDatabase(plan)
                call.respond(true)
            } catch(e: Exception) {
                call.respond(false)
            }
        }

    }
}