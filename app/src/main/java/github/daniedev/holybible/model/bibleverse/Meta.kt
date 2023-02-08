package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("fums")
    val fums: String?,
    @SerializedName("fumsId")
    val fumsId: String?,
    @SerializedName("fumsJs")
    val fumsJs: String?,
    @SerializedName("fumsJsInclude")
    val fumsJsInclude: String?,
    @SerializedName("fumsNoScript")
    val fumsNoScript: String?
)