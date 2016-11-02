package com.vimond.resources.datastructures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by Thelo on 11/10/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {

    @NotNull
    @JsonProperty("@id")
    private int id;

    @NotNull
    @JsonProperty("@uri")
    private String uri;

    @JsonProperty("@categoryId")
    private int categoryId;

    @JsonProperty("@channelId")
    private int channelId;

    @JsonProperty
    private boolean archive;

    @JsonProperty
    private boolean aspect16x9;

    @JsonProperty
    private int assetTypeId;

    @JsonProperty
    private String assetTypeName;

    @JsonProperty
    private String categoryPath;

    @JsonProperty
    private boolean copyLiveStream;

    @JsonProperty
    private String createTime;

    @JsonProperty
    private boolean deleted;

    @JsonProperty
    private String description;

    @JsonProperty
    private boolean drmProtected;

    @JsonProperty
    private float accurateDuration;

    @JsonProperty
    private int duration;

    @JsonProperty
    private int encoderGroupId;

    @JsonProperty
    private String imageUrl;

    @JsonProperty
    private boolean itemPublished;

    @JsonProperty
    private boolean labeledAsFree;

    @JsonProperty
    private boolean live;

    @JsonProperty
    private String liveBroadcastTime;

    @JsonProperty
    private int onDemandTimeBegin;

    @JsonProperty
    private int onDemandTimeEnd;

    @JsonProperty
    private String title;

    @JsonProperty
    private String categoryTitle;

    @JsonProperty
    private boolean downloadable;

    @JsonProperty
    private boolean livePublished;

    @JsonProperty
    private String publish;

    @JsonProperty
    private boolean published;

    @JsonProperty
    private String level;

    public int getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getChannelId() {
        return channelId;
    }

    public boolean isArchive() {
        return archive;
    }

    public boolean isAspect16x9() {
        return aspect16x9;
    }

    public int getAssetTypeId() {
        return assetTypeId;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public boolean isCopyLiveStream() {
        return copyLiveStream;
    }

    public String getCreateTime() {
        return createTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDrmProtected() {
        return drmProtected;
    }

    public float getAccurateDuration() {
        return accurateDuration;
    }

    public int getDuration() {
        return duration;
    }

    public int getEncoderGroupId() {
        return encoderGroupId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isItemPublished() {
        return itemPublished;
    }

    public boolean isLabeledAsFree() {
        return labeledAsFree;
    }

    public boolean isLive() {
        return live;
    }

    public String getLiveBroadcastTime() {
        return liveBroadcastTime;
    }

    public int getOnDemandTimeBegin() {
        return onDemandTimeBegin;
    }

    public int getOnDemandTimeEnd() {
        return onDemandTimeEnd;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public boolean isDownloadable() {
        return downloadable;
    }

    public boolean isLivePublished() {
        return livePublished;
    }

    public String getPublish() {
        return publish;
    }

    public boolean isPublished() {
        return published;
    }

    public String getLevel() {
        return level;
    }
}
