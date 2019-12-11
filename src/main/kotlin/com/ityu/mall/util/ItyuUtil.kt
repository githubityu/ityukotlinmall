package com.ityu.mall.util

import com.ityu.mall.component.NonWebRequestAttributes
import org.apache.commons.lang3.CharUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.BeanUtils
import org.springframework.beans.BeanWrapperImpl
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.sql.Time
import java.util.*
import javax.servlet.http.HttpServletRequest


private fun getNullPropertyNames(source: Any): Array<String> {
    val emptyNames = mutableListOf<String>()
    val src = BeanWrapperImpl(source)
    val pds = src.propertyDescriptors
    for (pd in pds) {
        val srcValue = src.getPropertyValue(pd.name)
        if (srcValue == null)
            emptyNames.add(pd.name)
    }
    return emptyNames.toTypedArray()
}


fun copyPropertiesIgnoreNull(source: Any, target: Any) {
    BeanUtils.copyProperties(source, target, *getNullPropertyNames(source))
}

fun getRequestAttributesSafely(): RequestAttributes {
    return try {
        RequestContextHolder.currentRequestAttributes()
    } catch (e: IllegalStateException) {
        NonWebRequestAttributes();
    }
}


/**
 *
 * @Title: getCurrentRequest
 * @author：liuyx
 * @date：2016年1月13日下午6:14:43
 * @Description: 获取当前request
 * @return
 * @throws IllegalStateException 当前线程不是web请求抛出此异常.
 */
@Throws(IllegalStateException::class)
fun getCurrentRequest(): HttpServletRequest {
    val attrs = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?
            ?: throw IllegalStateException("当前线程中不存在 Request 上下文")

    return attrs.request
}


/**
 * 从Date类型的时间中提取日期部分
 */
fun getDate(date: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    return calendar.time
}

/**
 * 从Date类型的时间中提取时间部分
 */
fun getTime(date: Date): Time {
    return Time(date.time)
}

/**
 * 对象属性转换为字段  例如：userName to user_name
 * @param property 字段名
 * @return
 */
fun propertyToField(property: String?): String {
    if (null == property) {
        return ""
    }
    val chars = property.toCharArray()
    val sb = StringBuffer()
    for (c in chars) {
        if (CharUtils.isAsciiAlphaUpper(c)) {
            sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)))
        } else {
            sb.append(c)
        }
    }
    return sb.toString()
}

/**
 * 字段转换成对象属性 例如：user_name to userName
 * @param field
 * @return
 */
fun fieldToProperty(field: String?): String {
    if (null == field) {
        return ""
    }
    val chars = field.toCharArray()
    val sb = StringBuffer()
    var i = 0
    while (i < chars.size) {
        val c = chars[i]
        if (c == '_') {
            val j = i + 1
            if (j < chars.size) {
                sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])))
                i++
            }
        } else {
            sb.append(c)
        }
        i++
    }
    return sb.toString()
}

fun getHttpServletRequest(): HttpServletRequest? {
    return (Objects.requireNonNull(RequestContextHolder.getRequestAttributes()) as ServletRequestAttributes).request
}
fun main(args: Array<String>) {
    val time = getTime(Date())
    print(time)
}