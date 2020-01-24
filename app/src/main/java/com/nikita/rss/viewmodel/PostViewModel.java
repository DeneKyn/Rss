package com.nikita.rss.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nikita.rss.database.PostRepository;
import com.nikita.rss.model.Post;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PostViewModel extends AndroidViewModel {

    private PostRepository postRepository;

    private MutableLiveData<List<Post>> posts = new MutableLiveData<>();

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public PostViewModel(Application application) {
        super(application);
        Picasso picasso = new Picasso.Builder(application.getApplicationContext())
                .downloader(new OkHttp3Downloader(application,Integer.MAX_VALUE))
                .build();
        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);
        postRepository = new PostRepository(application);
        isLoading.setValue(false);
    }

    public LiveData<List<Post>> getAll() {
        return posts;
    }


    public PostRepository.GetPostsAsyncTask getPosts(String feedUrl) {
        return postRepository.get(feedUrl);
    }

    public void setPosts(List<Post> p) {
        posts.setValue(p);
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public void setIsLoading(Boolean val) {
        isLoading.setValue(val);
    }

    public void savePost(Post post) {
        postRepository.insert(post);
    }

    public void clearPosts(String feedUrl) {
        postRepository.clearPosts(feedUrl);
    }
}
