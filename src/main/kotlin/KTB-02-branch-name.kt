import java.io.File

fun main() {

    val wordsFile: File = File("word.txt")

    val lines: List<String> = wordsFile.readLines()
    for (line in lines) {
        val line = line.split("|")
        val word = Word(original = line[0], translate = line[1])
        println(word)

    }

}

data class Word(
    val original: String,
    val translate: String,
)