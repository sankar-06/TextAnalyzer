package TextAnalyzer.text.analyze

import com.google.gson.Gson
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.json.tree.JsonObject

@Controller("/text/analyze")
class AnalyzeController {

    @Post(produces=[MediaType.APPLICATION_JSON])
    fun textanalyzer(text:JsonObject) : HttpResponse<*> {
        var content: String = text.get("content").coerceStringValue()
        val word_count = getWordCount(content)
        val character_count_with_spaces = getCharacterCountWithSpaces(content)
        val character_count_without_spaces = getCharacterCountWithoutSpaces(content)
        val line_count = getLineCount(content)
        val unique_words = getUniqueWords(content)

        val contents : Map<String,Int> = mapOf(
            "word_count" to word_count,
            "character_count_with_spaces" to character_count_with_spaces,
            "character_count_without_spaces" to character_count_without_spaces,
            "line_count" to line_count,
            "unique_words" to unique_words
        )
        val response = mapOf(
            "content" to contents
        )
        return HttpResponse.status<Any>(HttpStatus.UNAUTHORIZED).body(response)
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