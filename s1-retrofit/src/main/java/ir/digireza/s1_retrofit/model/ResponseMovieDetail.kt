package ir.digireza.s1_retrofit.model


import com.google.gson.annotations.SerializedName

data class ResponseMovieDetail(
    @SerializedName("actors")
    val actors: String?, // Marlon Brando, Al Pacino, James Caan, Richard S. Castellano
    @SerializedName("awards")
    val awards: String?, // Won 3 Oscars. Another 23 wins & 27 nominations.
    @SerializedName("country")
    val country: String?, // USA
    @SerializedName("director")
    val director: String?, // Francis Ford Coppola
    @SerializedName("genres")
    val genres: List<String?>?,
    @SerializedName("id")
    val id: Int?, // 2
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("imdb_id")
    val imdbId: String?, // tt0068646
    @SerializedName("imdb_rating")
    val imdbRating: String?, // 9.2
    @SerializedName("imdb_votes")
    val imdbVotes: String?, // 1,186,027
    @SerializedName("metascore")
    val metascore: String?, // 100
    @SerializedName("plot")
    val plot: String?, // The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.
    @SerializedName("poster")
    val poster: String?, // https://moviesapi.ir/images/tt0068646_poster.jpg
    @SerializedName("rated")
    val rated: String?, // R
    @SerializedName("released")
    val released: String?, // 24 Mar 1972
    @SerializedName("runtime")
    val runtime: String?, // 175 min
    @SerializedName("title")
    val title: String?, // The Godfather
    @SerializedName("type")
    val type: String?, // movie
    @SerializedName("writer")
    val writer: String?, // Mario Puzo (screenplay), Francis Ford Coppola (screenplay), Mario Puzo (novel)
    @SerializedName("year")
    val year: String? // 1972
)