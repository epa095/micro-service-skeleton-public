package com.vimond.resources.datastructures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

/**
 * Created by Thelo on 11/10/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {


    private static final Logger LOG = LoggerFactory.getLogger(Asset.class);

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
}
