package org.cdac.models;

import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by Sam on 7/14/2016.
 */

@SolrDocument(solrCoreName = "collection1")
public class Data {
    org.apache.solr.common.SolrDocument document;
    long localDateTime;

    public Data(org.apache.solr.common.SolrDocument document) {
        this.document = document;
        this.localDateTime = System.currentTimeMillis();
    }
}
