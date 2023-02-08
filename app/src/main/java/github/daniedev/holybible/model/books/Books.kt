package github.daniedev.holybible.model.books


import com.google.gson.annotations.SerializedName

data class Books(
    @SerializedName("data")
    val books: List<Book>?
)