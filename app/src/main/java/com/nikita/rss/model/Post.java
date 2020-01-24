package com.nikita.rss.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.nikita.rss.util.TimestampConverter;

import java.util.UUID;
import java.util.Date;

@Entity(
        tableName = "post",
        indices = {
                @Index(value = "id", unique = true)
        }
)
public class Post {

        @NonNull
        @PrimaryKey
        private String id = UUID.randomUUID().toString();
        private String title;
        private String link;
        private String description;
        private String mediaUrl;
        @TypeConverters({TimestampConverter.class})
        private Date pubDate;
        private String feedUrl;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getLink() {
                return link;
        }

        public void setLink(String link) {
                this.link = link;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getMediaUrl() {
                return mediaUrl;
        }

        public void setMediaUrl(String mediaUrl) {
                this.mediaUrl = mediaUrl;
        }

        public Date getPubDate() {
                return pubDate;
        }

        public void setPubDate(Date pubDate) {
                this.pubDate = pubDate;
        }

        public String getFeedUrl() {
                return feedUrl;
        }

        public void setFeedUrl(String feedUrl) {
                this.feedUrl = feedUrl;
        }
}
