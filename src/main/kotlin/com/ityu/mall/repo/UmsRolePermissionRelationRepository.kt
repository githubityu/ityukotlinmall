package com.ityu.mall.repo


import com.ityu.mall.model.UmsRolePermissionRelation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.persistence.Tuple

open interface UmsRolePermissionRelationRepository : JpaRepository<UmsRolePermissionRelation, Long> {

    /**
     * 获取指定角色权限
     */
    @Query("""
        SELECT
            p.id,p.pid,p.name,p.value,p.icon,p.type,p.uri,p.status,p.create_time as createTime,p.sort
        FROM
            ums_role_permission_relation r
            LEFT JOIN ums_permission p ON r.permission_id = p.id
        WHERE
            r.role_id = ?1;
    """, nativeQuery = true
    )
    fun getPermissionList(roleId: Long?): List<Tuple>


    fun deleteAllByRoleId(roleId: Long)



}