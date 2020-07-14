import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val dbConnect = DbConnect()
        dbConnect.open("questions.db")
        dbConnect.selectAllQuestions()
        val questions: List<Question>
        questions = dbConnect.selectAllQuestions()
        val playerClasses: List<PlayerClass>
        playerClasses = dbConnect.selectAllClasses()
        var i = 1
        var tmpAnswerCombat = 0
        var tmpAnswerMagic = 0
        var tmpAnswerStealth = 0
        val playerAnswer = PlayerAnswer()
        for (item in questions) {
            println(
                """
                    $i. ${item.Question}
                    ${item.Combat}
                    ${item.Magic}
                    ${item.Stealth}
                    
                    """.trimIndent()
            )
            while (true) {
                val scannerAnswer = readLine().toString().toInt()
                if (scannerAnswer in 1..3) {
                    if (scannerAnswer == 1 && tmpAnswerCombat < 7) {
                        tmpAnswerCombat++
                    } else if (scannerAnswer == 2 && tmpAnswerMagic < 7) {
                        tmpAnswerMagic++
                    } else if (scannerAnswer == 3 && tmpAnswerStealth < 7) {
                        tmpAnswerStealth++
                    }
                    break
                }
                println("Некорректное число")
            }
            i++
        }
        //i = 1
        playerAnswer.Combat = tmpAnswerCombat.toString()
        playerAnswer.Magic = tmpAnswerMagic.toString()
        playerAnswer.Stealth = tmpAnswerStealth.toString()
        if (playerAnswer.Combat == "7") {
            println("Ваш класс войн")
        }
        if (playerAnswer.Magic == "7") {
            println("Ваш класс Маг")
        }
        if (playerAnswer.Stealth == "7") {
            println("Ваш класс Вор")
        }
        if (tmpAnswerCombat == 4 && tmpAnswerMagic < 7 && tmpAnswerStealth < 7) {
            println("Ваш класс жулик")
        }
        for (item in playerClasses) {
            if (item.Combat == playerAnswer.Combat && item.Magic == playerAnswer.Magic && item.Stealth == playerAnswer.Stealth) {
                println("Ваш класс: " + item.Name)
            }
        }
        dbConnect.close()
    }
}