import java.io.File

fun main() {
    val dictionary = loadDictionary()

    var userInput = "1"
    while (userInput != "") {
        println(
            """Меню: 
1 – Учить слова
2 – Статистика
0 – Выход
    """
        )
        userInput = readln()

        when (userInput) {
            "1" -> println("Учить слова")
            "2" -> println("Статистика")
            "0" -> return
            else -> println("Введите число 1, 2 или 0")

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