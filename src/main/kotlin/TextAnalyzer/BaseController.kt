package TextAnalyzer

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class BaseController {
    @Get(produces = [MediaType.TEXT_PLAIN])
    fun home() : String{
        return "Welcome to Text analyzer.\nEnter the text into the payload in json format and call /text/analyze route to get the analysis :)"
    }

}