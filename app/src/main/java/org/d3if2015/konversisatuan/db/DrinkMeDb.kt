package org.d3if2015.konversisatuan.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DrinkMeEntity::class], version = 1, exportSchema = false)
abstract class DrinkMeDb :  RoomDatabase(){

    abstract val dao: DrinkMeDao

    companion object{

        @Volatile
        private var INSTANCE: DrinkMeDb? = null

        fun getInstance(context: Context): DrinkMeDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DrinkMeDb::class.java,
                        "DrinkMe.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}