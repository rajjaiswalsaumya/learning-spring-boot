package org.cdac.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by raj on 10/09/14.
 */

@ConfigurationProperties(prefix = "watcher", ignoreUnknownFields = true)
public class DirectoryConfiguration {

    private String errorDir;
    private String indexedDir;
    private String unIndexedDir;

    public String getErrorDir() {
        return errorDir;
    }

    public void setErrorDir(String errorDir) {
        this.errorDir = errorDir;
    }

    public String getIndexedDir() {
        return indexedDir;
    }

    public void setIndexedDir(String indexedDir) {
        this.indexedDir = indexedDir;
    }

    public String getUnIndexedDir() {
        return unIndexedDir;
    }

    public void setUnIndexedDir(String unIndexedDir) {
        this.unIndexedDir = unIndexedDir;
    }
}
