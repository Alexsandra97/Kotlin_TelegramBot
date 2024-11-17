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
                }

                val questionWords = notLearnedList.shuffled().take(4)
                val correctAnswer = questionWords[(0..3).random()].original
                println()
                println("$correctAnswer:")
                questionWords.forEachIndexed { i, element -> println("${i + 1} - ${element.translate}") }
                val responseNumber = readln()
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