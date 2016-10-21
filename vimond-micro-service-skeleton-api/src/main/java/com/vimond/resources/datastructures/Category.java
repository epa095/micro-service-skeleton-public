package com.vimond.resources.datastructures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by Thelo on 11/10/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

    @NotNull
    @JsonProperty("@id")
    private int id;

    @NotNull
    @JsonProperty("@uri")
    private String uri;

    @JsonProperty
    private String level;

    @JsonProperty
    private int levelTypeId;

    @JsonProperty
    private int parentId;

    @JsonProperty
    private String title;

    @JsonProperty
    private int priority;

    @JsonProperty
    private int programTypeId;

    @JsonProperty
    private String articles;

    @JsonProperty
    private String categoryPath;

    @JsonProperty
    private String assetSortBy;

    @JsonProperty
    private boolean published;

    @JsonProperty
    private boolean available;

    public int getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getLevel() {
        return level;
    }

    public int getLevelTypeId() {
        return levelTypeId;
    }

    public int getParentId() {
        return parentId;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public int getProgramTypeId() {
        return programTypeId;
    }

    public String getArticles() {
        return articles;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public String getAssetSortBy() {
        return assetSortBy;
    }

    public boolean isPublished() {
        return published;
    }

    public boolean isAvailable() {
        return available;
    }
}
