package com.widyatama.moviedirectory.core.db.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import android.provider.BaseColumns._ID
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_BACKDROP
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_ID
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_OVERVIEW
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_RATING
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_TITLE
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.MovieColumn.Companion.COLUMN_MOVIE_VOTER
import com.widyatama.moviedirectory.core.db.helper.DatabaseContract.TABLE_FAVORITE

class DatabaseHelper internal constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FAVORITE")
        onCreate(db)
    }

    companion object {

        private val DATABASE_NAME = "dbmovie"
        private val DATABASE_VERSION = 1

        private val CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s " +
                "(%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s TEXT, " +
                "%s REAL, %s INTEGER, %s TEXT, UNIQUE (%s) ON CONFLICT REPLACE)",
                TABLE_FAVORITE, _ID, COLUMN_MOVIE_ID, COLUMN_MOVIE_TITLE, COLUMN_MOVIE_OVERVIEW, COLUMN_MOVIE_RATING,
                COLUMN_MOVIE_VOTER, COLUMN_MOVIE_BACKDROP, COLUMN_MOVIE_ID)
    }
}
