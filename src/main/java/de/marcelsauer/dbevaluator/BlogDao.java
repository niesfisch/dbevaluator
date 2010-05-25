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
    void persistOrUpdate(Blog blog);

    /**
     * @return the blog
     * @param title
     *            of the blog to load
     */
    Blog load(String title);
    
    
    /**
     * @param title of the blog to delete
     * @return blog that was deleted
     */
    Blog delete(String title);
}
