package com.ityu.mall.dto


import com.ityu.mall.validator.FlagValidator
import io.swagger.annotations.ApiModelProperty


import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

/**
 * 品牌传递参数
 * Created by macro on 2018/4/26.
 */
class PmsBrandParam {
    @ApiModelProperty(value = "品牌名称", required = true)
    @NotEmpty(message = "名称不能为空")
    var name: String? = null
    @ApiModelProperty(value = "品牌首字母")
    var firstLetter: String? = null
    @ApiModelProperty(value = "排序字段")
    @Min(value = 0, message = "排序最小为0")
    var sort: Int? = null
    @ApiModelProperty(value = "是否为厂家制造商")
    @FlagValidator(value = ["0", "1"], message = "厂家状态不正确")
    var factoryStatus: Int? = null
    @ApiModelProperty(value = "是否进行显示")
    @FlagValidator(value = ["0", "1"], message = "显示状态不正确")
    var showStatus: Int? = null
    @ApiModelProperty(value = "品牌logo", required = true)
    @NotEmpty(message = "品牌logo不能为空")
    var logo: String? = null
    @ApiModelProperty(value = "品牌大图")
    var bigPic: String? = null
    @ApiModelProperty(value = "品牌故事")
    var brandStory: String? = null
}
