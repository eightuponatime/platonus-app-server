package com.example.plugins

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.mindrot.jbcrypt.BCrypt
import java.sql.*
import java.io.File

fun logInDatabase(user: User): Boolean {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val query = "SELECT * FROM [users] where username = '${user.username}'"
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(query)

        while (resultSet.next()) {
            val storedPassword = resultSet.getString("password")

            /*if(storedPassword == user.password) {
                return true
            }*/
            if(BCrypt.checkpw(user.password, storedPassword)) {
                return true
            }
        }
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    return false
}

fun checkIfUserExists(username: Username): Boolean {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(url)

        val query = "select * from [users] where username = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, username.username)

        val resultSet = preparedStatement.executeQuery()

        if(resultSet.next()) {
            return true
        }
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    return false
}

fun selectSchedule(username: Username): String {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    var storedSchedule = ""
    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(url)

        val selectQuery = """select schedule 
            |from [users] 
            |inner join [schedule] on [users].user_id = [schedule].user_id 
            |where [users].username = ?""".trimMargin()

        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setString(1, username.username)

        val resultSet = preparedStatement.executeQuery()

        if(resultSet.next()) {
            storedSchedule = resultSet.getString("schedule")
        }

        return storedSchedule
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    return storedSchedule
}

fun selectPlan(username: Username): String {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    var storedPlan = ""
    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(url)

        val selectQuery = """select individual_plan 
            |from [users] 
            |inner join individual_plan ON [users].user_id = [individual_plan].user_id
            |where [users].username = ?""".trimMargin()

        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setString(1, username.username)

        val resultSet = preparedStatement.executeQuery()

        if(resultSet.next()) {
            storedPlan  = resultSet.getString("individual_plan")
        }

        return storedPlan
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    return storedPlan
}

fun selectLastName(username: Username): String {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    var storedLastName =  ""
    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val selectQuery = "select * from [users] where username = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setString(1, username.username)

        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            storedLastName = resultSet.getString("last_name")
        }
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    return storedLastName
}

fun selectFirstName(username: Username): String {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    var storedFirstName =  ""
    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val selectQuery = "select * from [users] where username = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setString(1, username.username)

        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            storedFirstName = resultSet.getString("first_name")
        }
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    return storedFirstName
}

fun selectGroup(username: Username): String {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    var storedGroup =  ""
    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val selectQuery = "select * from [users] where username = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setString(1, username.username)

        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            storedGroup = resultSet.getString("study_group")
        }
    } catch(e: Exception) {
        e.printStackTrace()
    } finally {
        connection?.close()
    }
    return storedGroup
}

fun selectPassword(user: Username):String {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    var storedPassword = ""
    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val selectQuery = "select * from [users] where username = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        preparedStatement.setString(1, user.username)

        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            storedPassword = resultSet.getString("password")
        }
    } catch(e: Exception) {
        e.printStackTrace()
    } finally {
        connection?.close()
    }
    return storedPassword
}

fun updateUsername(updateUsername: UpdateUsername): Boolean {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val query = "update [users] set username = '${updateUsername.newUsername}' where username = '${updateUsername.username}'"
        val statement = connection.createStatement()
        val rowsAffected = statement.executeUpdate(query)

        return rowsAffected > 0
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    return false
}

fun updatePassword(user: User): Boolean {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())

        val query = "update [users] set password = '$hashedPassword' where username = '${user.username}'"
        val statement = connection.createStatement()
        val rowsAffected = statement.executeUpdate(query)

        return rowsAffected > 0
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    return false
}

fun insertPlanInDatabase(plan: Plan) {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234;")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(url)

        val selectQuery ="""
            |select plan_id
            |from [individual_plan]
            |inner join [users] on [individual_plan].user_id = [users].user_id
            |where [users].username = ?
        """.trimMargin()

        val selectStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        selectStatement.setString(1, plan.username)
        val resultSet = selectStatement.executeQuery()

        if (resultSet.next()) {
            val updateQuery ="""
                |update [individual_plan]
                |set individual_plan = ?
                |from [individual_plan]
                |inner join [users] on [individual_plan].user_id = [users].user_id
                |where [users].username = ?
            """.trimMargin()

            val updateStatement: PreparedStatement = connection.prepareStatement(updateQuery)
            updateStatement.setString(1, plan.plan)
            updateStatement.setString(2, plan.username)

            val affectedLines = updateStatement.executeUpdate()

            if (affectedLines > 0) {
                println("Plan updated")
            } else {
                println("Couldn't update plan")
            }
        } else {
            val insertQuery ="""
                |insert into [individual_plan](user_id, individual_plan)
                |select [users].user_id, ?
                |from [users]
                |where [users].username = ?
            """.trimMargin()

            val insertStatement: PreparedStatement = connection.prepareStatement(insertQuery)
            insertStatement.setString(1, plan.plan)
            insertStatement.setString(2, plan.username)

            val affectedLines = insertStatement.executeUpdate()

            if (affectedLines > 0) {
                println("Plan added")
            } else {
                println("Couldn't add plan")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


fun insertScheduleInDatabase(schedule: Schedule) {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(url)

        val selectQuery ="""
            |select schedule_id
            |from [schedule]
            |inner join [users] on [schedule].user_id = [users].user_id
            |where [users].username = ?
        """.trimMargin()

        val selectStatement: PreparedStatement = connection.prepareStatement(selectQuery)
        selectStatement.setString(1, schedule.username)
        val resultSet = selectStatement.executeQuery()

        if (resultSet.next()) {
            val updateQuery ="""
                |update [schedule]
                |set schedule = ?
                |from [schedule]
                |inner join [users] on [schedule].user_id = [users].user_id
                |where [users].username = ?
            """.trimMargin()

            val updateStatement: PreparedStatement = connection.prepareStatement(updateQuery)
            updateStatement.setString(1, schedule.schedule)
            updateStatement.setString(2, schedule.username)

            val affectedLines = updateStatement.executeUpdate()

            if (affectedLines > 0) {
                println("Schedule updated")
            } else {
                println("Couldn't update schedule")
            }
        } else {
            val insertQuery ="""
                |insert into [schedule](user_id, schedule)
                |select [users].user_id, ?
                |from [users]
                |where [users].username = ?
            """.trimMargin()

            val insertStatement: PreparedStatement = connection.prepareStatement(insertQuery)
            insertStatement.setString(1, schedule.schedule)
            insertStatement.setString(2, schedule.username)

            val affectedLines = insertStatement.executeUpdate()

            if (affectedLines > 0) {
                println("Schedule added")
            } else {
                println("Couldn't add schedule")
            }
        }
    } catch (e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}


fun insertInDatabase(student: Student) {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(url)

        val hashedPassword = BCrypt.hashpw(student.password, BCrypt.gensalt())

        val query = "insert into [users](username, password, first_name, last_name, study_group) values (?, ?, ?, ?, ?)"
        val preparedStatement: PreparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, student.username)
        preparedStatement.setString(2, student.password)
        preparedStatement.setString(3, student.first_name)
        preparedStatement.setString(4, student.last_name)
        preparedStatement.setString(5, student.study_group)

        val affectedLines = preparedStatement.executeUpdate()

        if(affectedLines > 0) {
            println("User added")
        } else {
            println("Couldn't add user")
        }

    } catch(e: SQLException) {
        e.printStackTrace()
    } finally {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}

fun getAllUsersFromDatabase(): List<Student> {
    val url = StringBuilder()
        .append("jdbc:sqlserver://localhost:1433;")
        .append("databaseName=platonus_database;")
        .append("user=eightuponatime;")
        .append("password=1234")
        .toString()

    val users = mutableListOf<Student>()
    DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)

        val selectQuery = "SELECT * FROM [users]"
        val preparedStatement: PreparedStatement = connection.prepareStatement(selectQuery)

        val resultSet = preparedStatement.executeQuery()
        while (resultSet.next()) {
            val username = resultSet.getString("username")
            val password = resultSet.getString("password")
            val firstName = resultSet.getString("first_name")
            val lastName = resultSet.getString("last_name")
            val studyGroup = resultSet.getString("study_group")

            val user = Student(username, password, firstName, lastName, studyGroup)
            users.add(user)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        connection?.close()
    }

    return users
}
