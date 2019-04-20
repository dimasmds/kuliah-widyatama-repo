package com.widyatama.moviedirectory.core.db.helper

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

import java.util.Objects

import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.AUTHORITY
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.CONTENT_URI

class DatabaseProvider : ContentProvider() {

    private var movieHelper: MovieHelper? = null

    override fun onCreate(): Boolean {
        movieHelper = MovieHelper(context!!)
        movieHelper!!.open()
        return true
    }

    override fun query(uri: Uri, strings: Array<String>?, s: String?, strings1: Array<String>?, s1: String?): Cursor? {
        val cursor: Cursor?
        when (uriMacther.match(uri)) {
            MOVIE -> cursor = movieHelper!!.queryProvider()
            MOVIE_ID -> cursor = movieHelper!!.queryByIdProvider(uri.lastPathSegment)
            else -> cursor = null
        }

        cursor?.setNotificationUri(Objects.requireNonNull<Context>(context).getContentResolver(), uri)

        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val added: Long

        when (uriMacther.match(uri)) {
            MOVIE -> added = movieHelper!!.insertProvider(contentValues)
            else -> added = 0
        }

        if (added > 0) {
            Objects.requireNonNull<Context>(context).getContentResolver().notifyChange(uri, null)
        }

        return Uri.parse(CONTENT_URI.toString() + "/" + added)
    }

    override fun update(uri: Uri, contentValues: ContentValues?, s: String?, strings: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        val delete: Int
        when (uriMacther.match(uri)) {
            MOVIE_ID -> delete = movieHelper!!.deleteProvider(uri.lastPathSegment)
            else -> delete = 0
        }

        if (delete > 0) {
            Objects.requireNonNull<Context>(context).getContentResolver().notifyChange(uri, null)
        }

        return delete
    }

    companion object {

        private val MOVIE = 100
        private val MOVIE_ID = 101

        private val uriMacther = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMacther.addURI(AUTHORITY, DatabaseContract.TABLE_FAVORITE, MOVIE)

            uriMacther.addURI(AUTHORITY, DatabaseContract.TABLE_FAVORITE + "/#", MOVIE_ID)
        }
    }
}
