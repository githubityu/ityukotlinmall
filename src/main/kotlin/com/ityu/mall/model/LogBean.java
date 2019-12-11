package com.ityu.mall.model;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@Table(name = "log_bean")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "LogBean", description = "日志表")
public class LogBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    private Long id;

    /**
     * 操作用户
     */

    private String username;

    /**
     * 操作内容
     */

    private String operation;

    /**
     * 耗时
     */

    private Long time;

    /**
     * 操作方法
     */

    private String method;

    /**
     * 方法参数
     */

    private String params;

    /**
     * 操作者IP
     */

    private String ip;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 操作地点
     */

    private String location;

    private transient String createTimeFrom;
    private transient String createTimeTo;
}
