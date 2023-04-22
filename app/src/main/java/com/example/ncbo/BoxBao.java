
package com.example.ncbo;

import java.util.List;



public class BoxBao {


    private String status;

    private Integer totalResults;

    private List<Article> articles;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BoxBao() {
    }

    /**
     * 
     * @param totalResults
     * @param articles
     * @param status
     */
    public BoxBao(String status, Integer totalResults, List<Article> articles) {
        super();
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
