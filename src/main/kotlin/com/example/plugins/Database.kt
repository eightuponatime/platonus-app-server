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
