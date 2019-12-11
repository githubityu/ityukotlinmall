package com.ityu.mall.repo


import com.ityu.mall.model.UmsAdminRoleRelation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.persistence.Tuple

open interface UmsAdminRoleRelationRepository : JpaRepository<UmsAdminRoleRelation, Long> {
    fun deleteByAdminIdEquals(adminId: Long): Int
    /**
     * 获取用于所有角色
     */
    @Query("""
         select r.status,r.sort,r.name,r.create_time as createTime,r.id,r.admin_count as adminCount,r.description
        from ums_admin_role_relation ar left join ums_role r on ar.role_id = r.id
        where ar.admin_id = ?1
    """, nativeQuery = true
    )
    fun getRoleList(adminId: Long): List<Tuple>

    /**
     * 获取用户所有角色权限
     */
    @Query("""
          select p.*
        from ums_admin_role_relation ar left join ums_role r on ar.role_id = r.id
            left join ums_role_permission_relation rp on r.id = rp.role_id
            left join ums_permission p on rp.permission_id=p.id
            where ar.admin_id = ?1 and p.id is not null
    """, nativeQuery = true
    )
    fun getRolePermissionList(adminId: Long): List<Tuple>

    /**
     * 获取用户所有权限(包括+-权限)
     */
    @Query(""" SELECT
            p.*
        FROM
            ums_admin_role_relation ar
            LEFT JOIN ums_role r ON ar.role_id = r.id
            LEFT JOIN ums_role_permission_relation rp ON r.id = rp.role_id
            LEFT JOIN ums_permission p ON rp.permission_id = p.id
        WHERE
            ar.admin_id = ?1
            AND p.id IS NOT NULL
            AND p.id NOT IN (
                SELECT
                    p.id
                FROM
                    ums_admin_permission_relation pr
                    LEFT JOIN ums_permission p ON pr.permission_id = p.id
                WHERE
                    pr.type = - 1
                    AND pr.admin_id = ?1
            )
        UNION
        SELECT
            p.*
        FROM
            ums_admin_permission_relation pr
            LEFT JOIN ums_permission p ON pr.permission_id = p.id
        WHERE
            pr.type = 1
            AND pr.admin_id = ?1
    """, nativeQuery = true)
    fun getPermissionList(adminId: Long): List<Tuple>


}