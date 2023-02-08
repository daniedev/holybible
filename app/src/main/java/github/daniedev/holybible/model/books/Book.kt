package github.daniedev.holybible.model.books


import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("abbreviation")
    val abbreviation: String?,
    @SerializedName("bibleId")
    val bibleId: String?,
    @SerializedName("chapters")
    val chapters: List<Chapter>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("nameLong")
    val nameLong: String
)