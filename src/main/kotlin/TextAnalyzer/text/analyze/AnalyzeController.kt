package TextAnalyzer.text.analyze

import com.google.gson.Gson
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.json.tree.JsonObject

data class Result (
    val word_count: Int,
    val character_count_with_spaces: Int,
    val character_count_without_spaces: Int,
    val line_count: Int,
    val unique_words: Int
)

@Controller("/text/analyze")
class AnalyzeController {
    @Post()
    fun textanalyzer(text:JsonObject) : String? {
        var content: String = text.get("content").coerceStringValue()
        val word_count = getWordCount(content)
        val character_count_with_spaces = getCharacterCountWithSpaces(content)
        val character_count_without_spaces = getCharacterCountWithoutSpaces(content)
        val line_count = getLineCount(content)
        val unique_words = getUniqueWords(content)

        val res = Result(word_count,character_count_with_spaces,character_count_without_spaces,line_count,unique_words)
        return Gson().toJson(res)
    }

    fun getUniqueWords(text: String):Int{
        val list = text.replace("\n","").split(" ")
        var count = 0
        var uniqueWords : HashSet<String> = HashSet<String>(list)
        uniqueWords.forEach { it ->
            if (it == " ") {
                count++
            }
        }
        return uniqueWords.size - count
    }
    fun getLineCount(text: String):Int{
        var line = 1
        for(i in text.split("")){
            if(i == "\n"){ line++ }
        }
        return line
    }
    fun getCharacterCountWithoutSpaces(text: String):Int {
        var temp = text.replace("\n","")
        return temp.replace(" ","").length
    }
    fun getCharacterCountWithSpaces(text: String):Int {
        return text.replace("\n","").length
    }
    fun getWordCount(text: String): Int {
        return text.split(" ").size
    }
}