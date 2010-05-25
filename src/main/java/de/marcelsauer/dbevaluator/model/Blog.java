package de.marcelsauer.dbevaluator.model;

import java.util.ArrayList;
import java.util.List;

public class Blog {

    public String title;
    public List<Post> posts = new ArrayList<Post>();

    public Blog(String title) {
        this.title = title;
    }

    public void add(Post post) {
        this.posts.add(post);
    }

}
