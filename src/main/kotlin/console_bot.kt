import java.io.File

fun main() {

    val wordsFile: File = File("word.txt")

    val lines: List<String> = wordsFile.readLines()
    for (line in lines) {
        val line = line.split("|")
        dictionary.add(word)

    }

    println(dictionary)
}

    val original: String,
    val translate: String,
    var correctAnswersCount: Int,
)