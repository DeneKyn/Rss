package com.nikita.rss.database;

import android.app.Application;
import android.os.AsyncTask;

import com.nikita.rss.dao.PostDAO;
import com.nikita.rss.model.Post;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PostRepository {

    private PostDAO postDAO;

    public PostRepository(Application application) {
        PostDatabase database = PostDatabase.getInstance(application);
        postDAO = database.postDAO();
    }


    public GetPostsAsyncTask get(String url) {
        GetPostsAsyncTask task;
        task = new GetPostsAsyncTask(this.postDAO);
        task.execute(url);
        return task;
    }

    public static class GetPostsAsyncTask extends AsyncTask<String, Void, List<Post>> {
        private PostDAO mPostDAO;

        private GetPostsAsyncTask(PostDAO postDAO) {
            this.mPostDAO = postDAO;
        }

        @Override
        protected List<Post> doInBackground(String... url) {
            return mPostDAO.getPosts(url[0]);
        }
    }

    public void insert(final Post post) {
        new InsertNoteAsyncTask(postDAO).execute(post);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDAO mPostDAO;

        private InsertNoteAsyncTask(PostDAO postDAO) {
            this.mPostDAO = postDAO;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            mPostDAO.insert(posts[0]);
            return null;
        }
    }

    public void clearPosts(String feedUrl) {
        new ClearPostAsyncTask(postDAO).execute(feedUrl);
    }

    private static class ClearPostAsyncTask extends AsyncTask<String, Void, Void> {
        private PostDAO mPostDAO;

        private ClearPostAsyncTask(PostDAO postDAO) {
            this.mPostDAO = postDAO;
        }

        @Override
        protected Void doInBackground(String... url) {
            mPostDAO.clearPosts(url[0]);
            return null;
        }
    }
}
