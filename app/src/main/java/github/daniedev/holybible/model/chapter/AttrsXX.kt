package github.daniedev.holybible.model.chapter


import com.google.gson.annotations.SerializedName

data class AttrsXX(
    @SerializedName("verseId")
    val verseId: String?,
    @SerializedName("verseOrgIds")
    val verseOrgIds: List<String>?
)