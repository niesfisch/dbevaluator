package de.marcelsauer.dbevaluator;

import de.marcelsauer.dbevaluator.model.Blog;

/**
 * @author msauer
 * 
 */
public interface BlogDao {

    /**
     * persists the blog to the datastore
     * 
     * @param blog
     *            to persist, must not be null
     */
    void persist(Blog blog);

    /**
     * @return the blog
     * @param name
     *            of the blog to load
     */
    Blog load(String name);
}
