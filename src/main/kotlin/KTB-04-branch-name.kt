import java.io.File

fun main() {

    val wordsFile: File = File("word.txt")
    val dictionary = mutableListOf<Word1>()

    val lines: List<String> = wordsFile.readLines()
    for (line in lines) {
        val line = line.split("|")
        val word = Word1(original = line[0], translate = line[1], correctAnswersCount = (line[2]?.toIntOrNull() ?: 0))
        dictionary.add(word)

    }

    println(dictionary)
}

data class Word1(
    val original: String,
    val translate: String,
    var correctAnswersCount: Int,
)