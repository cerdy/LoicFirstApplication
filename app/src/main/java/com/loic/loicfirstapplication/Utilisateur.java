package com.loic.loicfirstapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Utilisateur implements Parcelable {
    public String login;
    public String id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public String site_admin;
    public String score;

    protected Utilisateur(Parcel in) {
        login = in.readString();
        id = in.readString();
        node_id = in.readString();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        gists_url = in.readString();
        starred_url = in.readString();
        subscriptions_url = in.readString();
        organizations_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        site_admin = in.readString();
        score = in.readString();
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(id);
        dest.writeString(node_id);
        dest.writeString(avatar_url);
        dest.writeString(gravatar_id);
        dest.writeString(url);
        dest.writeString(html_url);
        dest.writeString(followers_url);
        dest.writeString(following_url);
        dest.writeString(gists_url);
        dest.writeString(starred_url);
        dest.writeString(subscriptions_url);
        dest.writeString(organizations_url);
        dest.writeString(repos_url);
        dest.writeString(events_url);
        dest.writeString(received_events_url);
        dest.writeString(type);
        dest.writeString(site_admin);
        dest.writeString(score);
    }
};
