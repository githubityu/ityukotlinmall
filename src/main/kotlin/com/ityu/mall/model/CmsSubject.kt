package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.util.Date
import javax.persistence.*

@Data
@Table(name = "cms_subject")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "CmsSubject", description = "专题表")
class CmsSubject : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    var id: Long? = null

    var categoryId: Long? = null

    var title: String? = null

    @ApiModelProperty(value = "专题主图")
    var pic: String? = null

    @ApiModelProperty(value = "关联产品数量")
    var productCount: Int? = null

    var recommendStatus: Int? = null

    var createTime: Date? = null

    var collectCount: Int? = null

    var readCount: Int? = null

    var commentCount: Int? = null

    @ApiModelProperty(value = "画册图片用逗号分割")
    var albumPics: String? = null

    var description: String? = null

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    var showStatus: Int? = null

    @ApiModelProperty(value = "转发数")
    var forwardCount: Int? = null

    @ApiModelProperty(value = "专题分类名称")
    var categoryName: String? = null

    var content: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", categoryId=").append(categoryId)
        sb.append(", title=").append(title)
        sb.append(", pic=").append(pic)
        sb.append(", productCount=").append(productCount)
        sb.append(", recommendStatus=").append(recommendStatus)
        sb.append(", createTime=").append(createTime)
        sb.append(", collectCount=").append(collectCount)
        sb.append(", readCount=").append(readCount)
        sb.append(", commentCount=").append(commentCount)
        sb.append(", albumPics=").append(albumPics)
        sb.append(", description=").append(description)
        sb.append(", showStatus=").append(showStatus)
        sb.append(", forwardCount=").append(forwardCount)
        sb.append(", categoryName=").append(categoryName)
        sb.append(", content=").append(content)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}