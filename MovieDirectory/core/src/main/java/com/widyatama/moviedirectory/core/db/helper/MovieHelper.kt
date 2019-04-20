package com.widyatama.moviedirectory.core.db.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

import com.widyatama.moviedirectory.core.db.model.FavoriteMovie

import java.util.ArrayList

import android.provider.BaseColumns._ID
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_BACKDROP
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_ID
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_OVERVIEW
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_RATING
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_TITLE
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_VOTER
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.TABLE_FAVORITE

class MovieHelper(private val context: Context) {
    private var databaseHelper: DatabaseHelper? = null

    private var database: SQLiteDatabase? = null

    @Throws(SQLException::class)
    fun open() {
        databaseHelper = DatabaseHelper(context)
        database = databaseHelper!!.writableDatabase
    }

    fun close() {
        databaseHelper!!.close()
    }

    fun query(): ArrayList<FavoriteMovie> {
        val arrayList = ArrayList<FavoriteMovie>()
        val cursor = database!!.query(DATABASE_TABLE, null, null, null, null, null, "$_ID DESC", null)
        cursor.moveToFirst()
        var favoriteMovie: FavoriteMovie
        if (cursor.count > 0) {
            do {
                favoriteMovie = FavoriteMovie()
                favoriteMovie.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                favoriteMovie.movieId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_ID))
                favoriteMovie.movieTitle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_TITLE))
                favoriteMovie.movieOverview = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_OVERVIEW))
                favoriteMovie.movieRating = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_RATING))
                favoriteMovie.movieVoter = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_VOTER))
                favoriteMovie.movieBackdrop = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_BACKDROP))

                arrayList.add(favoriteMovie)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }

        cursor.close()
        return arrayList
    }

    fun queryById(movieId: Int): Cursor {
        return database!!.rawQuery("SELECT * FROM " + TABLE_FAVORITE + " WHERE "
                + COLUMN_MOVIE_ID + " = " + movieId, null)
    }

    fun insert(favoriteMovie: FavoriteMovie): Long {
        val initialValues = ContentValues()
        initialValues.put(COLUMN_MOVIE_ID, favoriteMovie.movieId)
        initialValues.put(COLUMN_MOVIE_BACKDROP, favoriteMovie.movieBackdrop)
        initialValues.put(COLUMN_MOVIE_OVERVIEW, favoriteMovie.movieOverview)
        initialValues.put(COLUMN_MOVIE_RATING, favoriteMovie.movieRating)
        initialValues.put(COLUMN_MOVIE_TITLE, favoriteMovie.movieTitle)
        initialValues.put(COLUMN_MOVIE_VOTER, favoriteMovie.movieVoter)
        return database!!.insert(DATABASE_TABLE, null, initialValues)
    }


    fun delete(movieId: Int): Int {
        return database!!.delete(TABLE_FAVORITE, "$COLUMN_MOVIE_ID = '$movieId'", null)
    }


    internal fun queryByIdProvider(id: String?): Cursor {
        return database!!.query(DATABASE_TABLE, null,
                "$_ID = ?",
                arrayOf(id), null, null, null, null)
    }

    internal fun queryProvider(): Cursor {
        return database!!.query(DATABASE_TABLE, null, null, null, null, null,
                "$_ID DESC")
    }

    internal fun insertProvider(values: ContentValues?): Long {
        return database!!.insert(DATABASE_TABLE, null, values)
    }

    internal fun deleteProvider(id: String?): Int {
        return database!!.delete(DATABASE_TABLE, "$_ID = ?", arrayOf(id))
    }

    companion object {

        private val DATABASE_TABLE = TABLE_FAVORITE
    }
}
