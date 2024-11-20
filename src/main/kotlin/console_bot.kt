import java.io.File

fun main() {
    val dictionary = loadDictionary()


    while (true) {
        println(
            """Меню: 
1 – Учить слова
2 – Статистика
0 – Выход
    """
        )
        val userInput = readln()

        when (userInput) {
            "1" -> {
                val notLearnedList = dictionary.filter { it.correctAnswersCount < 3 }
                if (notLearnedList.isEmpty()) {
                    println("Все слова в словаре выучены")
                    continue
                }

                val questionWords = notLearnedList.shuffled().take(4)
                val correctAnswer = questionWords.random()
                println()
                println("${correctAnswer.original}:")
                questionWords.forEachIndexed { i, element -> println("${i + 1} - ${element.translate}") }
                println(
                    """----------
0 - Меню
                """.trimIndent()
                )
                val userAnswerInput = readln().toInt()
                if (userAnswerInput == 0) continue
                val correctAnswerId = questionWords.indexOf(correctAnswer)
                if (correctAnswerId + 1 == userAnswerInput) {
                    println("Правильно!")
                    //correctAnswer.correctAnswersCount++
                    dictionary.forEach {if (it.original == correctAnswer.original) it.correctAnswersCount++}
                    saveDictionary(dictionary)

                } else {
                    println("Неправильно! ${correctAnswer.original} - это ${correctAnswer.translate}")
                }
            }

            "2" -> {
                val totalCount = dictionary.count()
                val learnedCount = dictionary.filter { it.correctAnswersCount >= 3 }.count()
                val percent = (100 * (learnedCount.toDouble() / totalCount)).toInt()
                println("Выучено $learnedCount из $totalCount | $percent%")
                println()
            }

            "0" -> {
                return
            }

            else -> {
                println("Введите число 1, 2 или 0")
            }

        }
    }

}

data class Word(
    val original: String,
    val translate: String,
    var correctAnswersCount: Int,
)

fun loadDictionary(): List<Word> {

    val wordsFile: File = File("word.txt")
    val dictionary = mutableListOf<Word>()

    val lines: List<String> = wordsFile.readLines()
    for (line in lines) {
        val line = line.split("|")
        val word = Word(original = line[0], translate = line[1], correctAnswersCount = (line[2]?.toIntOrNull() ?: 0))
        dictionary.add(word)
    }
    return dictionary
}

fun saveDictionary(dictionary: List<Word>) {
    val wordsFile: File = File("word.txt")
    wordsFile.delete()
    dictionary.forEach { it -> wordsFile.appendText("${it.original}|${it.translate}|${it.correctAnswersCount}\n") }
}