package github.daniedev.holybible.service

import github.daniedev.holybible.model.bibleverse.BibleVerse
import github.daniedev.holybible.model.books.Books
import github.daniedev.holybible.model.chapter.Chapter
import github.daniedev.holybible.util.network.NetworkCallDurationMonitor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BibleService {
        @GET("/v1/bibles/{bibleId}/verses/{verseId}")
        suspend fun getVerseById(
            @Path("bibleId") bibleVersion: String,
            @Path("verseId") verse: String,
            @Query("content-type") contentType: String
        ): BibleVerse

        @GET("/v1/bibles/{bibleId}/chapters/{chapterId}")
        suspend fun getChapter(
            @Path("bibleId") bibleVersion: String,
            @Path("chapterId") chapterId: String,
            @Query("content-type") contentType: String,
            @Query("parallels") otherVersionBooksToFetch: String
        ): Chapter

        @GET("/v1/bibles/{bibleId}/books")
        suspend fun getAllBooks(
            @Path("bibleId") bibleVersion: String,
            @Query("include-chapters") includeChapters: Boolean = true
        ) : Books
    }

val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(NetworkCallDurationMonitor())
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .addInterceptor {
            chain ->
        val request = chain.request().newBuilder()
            .addHeader("api-key", "ddc4fac8a01872cad7f634794bb52646")
            .build()
        chain.proceed(request)
    }
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.scripture.api.bible")
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()
