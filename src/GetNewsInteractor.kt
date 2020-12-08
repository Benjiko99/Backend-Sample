class GetNewsInteractor {

    data class Request(
        val lang: String
    )

    sealed class Response {
        data class Success(val news: List<String>) : Response()
        object Failure : Response()
    }

    fun execute(request: Request): Response {
        return Response.Success(listOf("Article #1", "Article #2"))
    }
}