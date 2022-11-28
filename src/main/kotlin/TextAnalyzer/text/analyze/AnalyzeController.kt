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
        
        return "Endpoint working!"
    }

}