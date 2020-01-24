package com.nikita.rss.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.nikita.rss.dao.PostDAO;
import com.nikita.rss.model.Post;
import com.nikita.rss.util.TimestampConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database( entities = {Post.class}, version = 1, exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {

    private static volatile PostDatabase instance;
    public abstract PostDAO postDAO();

    static synchronized PostDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PostDatabase.class,
                    "post_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
