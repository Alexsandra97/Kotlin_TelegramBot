import java.io.File

fun main() {

    val wordsFile: File = File("word.txt")
    wordsFile.createNewFile()
    (wordsFile.readLines()).forEach { println(it) }

}