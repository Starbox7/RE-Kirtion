package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PageSettingDto {
    private String uuid;
    @JsonProperty("page_uuid")
    private String pageUuid;
    @JsonProperty("font_family")
    private String fontFamily;
    @JsonProperty("font_size")
    private boolean fontSize;  
    private boolean expansion;
    @JsonProperty("page_lock")
    private boolean pageLock;
    private boolean comment;
    @JsonProperty("up_comment")
    private boolean upComment;
    @JsonProperty("back_link")
    private String backLink;
    @JsonProperty("is_wiki")
    private boolean isWiki;
    private boolean alert;
}

