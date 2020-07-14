import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class DbConnect {
    private var _connection: Connection? = null
    //var exitWord = ".exit"
    fun open(dbName: String): Boolean {
        return try {
            Class.forName("org.sqlite.JDBC")
            _connection = DriverManager.getConnection("jdbc:sqlite:$dbName")
            println("Connected")
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }

    private fun open(): Boolean {
        return try {
            Class.forName("org.sqlite.JDBC")
            _connection =
                DriverManager.getConnection("jdbc:sqlite:/home/allen8kane/Programming/Kotlin/firstProject/questions.db")
            println("Connected")
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }

    fun selectAllQuestions(): ArrayList<Question> {
        return if (open()) {
            try {
                val statement = _connection!!.createStatement()
                val query = "SELECT * FROM Questions ORDER BY id"
                val resultSet = statement.executeQuery(query)
                val questions: ArrayList<Question> = ArrayList()
                var i = 0
                while (resultSet.next()) {

                    //int id = resultSet.getInt("Id");
                    val question = resultSet.getString("Question")
                    val combat = resultSet.getString("Combat")
                    val magic = resultSet.getString("Magic")
                    val stealth = resultSet.getString("Stealth")
                    questions.add(Question(question, combat, magic, stealth))
                    i++
                }
                resultSet.close()
                statement.close()
                questions
            } catch (e: Exception) {
                println(e.message)
                ArrayList<Question>()
            }
        } else {
            ArrayList()
        }
    }

    fun selectAllClasses(): ArrayList<PlayerClass> {
        return if (open()) {
            try {
                val statement = _connection!!.createStatement()
                val query = "SELECT * FROM Classes ORDER BY id"
                val resultSet = statement.executeQuery(query)
                val playerClasses: ArrayList<PlayerClass> = ArrayList()
                var i = 0
                while (resultSet.next()) {

                    //int id = resultSet.getInt("Id");
                    val name = resultSet.getString("Classes")
                    val combat = resultSet.getString("Combat")
                    val magic = resultSet.getString("Magic")
                    val stealth = resultSet.getString("Stealth")
                    playerClasses.add(PlayerClass(name, combat, magic, stealth))
                    i++
                }
                resultSet.close()
                statement.close()
                playerClasses
            } catch (e: Exception) {
                println(e.message)
                ArrayList<PlayerClass>()
            }
        } else {
            ArrayList()
        }
    }

    fun close() {
        try {
            _connection!!.close()
            println("Closed")
        } catch (e: Exception) {
            println(e.message)
        }
    }
}