package com.govey.controller.users.storeitems;

import com.govey.service.store.domain.StoreItemStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreItemRequest {
    @ApiModelProperty(example = "스타벅스", notes = "", required = true)
    private String category;

    @ApiModelProperty(example = "아메리카노", notes = "", required = true)
    private String name;

    @ApiModelProperty(example = "맛있는 아메리카노", notes = "", required = true)
    private String description;

    @ApiModelProperty(example = "유의사항입니다.", notes = "", required = true)
    private String notice;

    @ApiModelProperty(example = "10000", notes = "", required = true)
    private Integer price;

    @ApiModelProperty(example = "http~.png", notes = "", required = true)
    private String imageUrl;

    @ApiModelProperty(example = "1", notes = "", required = true)
    private Integer stockCount;

    @ApiModelProperty(example = "stop", notes = "", required = true)
    private StoreItemStatus status;
}
