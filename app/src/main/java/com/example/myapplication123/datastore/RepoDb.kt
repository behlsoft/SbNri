package com.example.myapplication123.datastore

import android.content.Context
import android.util.Log
import androidx.paging.DataSource
import androidx.room.*
import com.example.myapplication123.model.SbModel

@Database(
    entities = [SbModel::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDb : RoomDatabase() {
    abstract fun reposDao(): RepoDao
    companion object {
        @Volatile
        private var INSTANCE: RepoDb? = null
        fun getInstance(context: Context): RepoDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }.also { Log.d("In DB","DB") }

            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                RepoDb::class.java, "SBNri.db")
                .build()
    }
}

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<SbModel>)

    @Query("SELECT * FROM repos")
    fun getRepos(): DataSource.Factory<Int, SbModel>
}