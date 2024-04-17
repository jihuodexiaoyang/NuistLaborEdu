CREATE
OR REPLACE VIEW sys_user_credits_v AS SELECT
                                          t.user_id,
                                          t.user_name,
                                          t.login_name,
                                          t.dept_id,
                                          t.dept_name,
                                          t.email,
                                          t.sex,
                                          t.STATUS,
                                          t.del_flag,
                                          t.class_type,
                                          t.complete_flag,
                                          sum( t.class_credit ) AS total_credit
                                      FROM
                                          (
                                              SELECT
                                                  t.user_id,
                                                  u.login_name,
                                                  u.user_name,
                                                  u.dept_id,
                                                  u.dept_name,
                                                  u.email,
                                                  u.sex,
                                                  u.STATUS,
                                                  u.del_flag,
                                                  t.class_id,
                                                  t.class_type,
                                                  c.class_title,
                                                  c.class_credit,
                                                  t.complete_flag,
                                                  t.complete_time
                                              FROM
                                                  ( SELECT * FROM sys_user_class WHERE class_type = '0' ) t
                                                      LEFT JOIN (
                                                      SELECT
                                                          u.user_id,
                                                          u.dept_id,
                                                          u.login_name,
                                                          u.user_name,
                                                          u.user_type,
                                                          u.email,
                                                          u.avatar,
                                                          u.phonenumber,
                                                          u.sex,
                                                          u.STATUS,
                                                          u.del_flag,
                                                          d.parent_id,
                                                          d.dept_name,
                                                          d.order_num,
                                                          d.leader,
                                                          d.STATUS AS dept_status,
                                                          r.role_id,
                                                          r.role_name,
                                                          r.role_key,
                                                          r.role_sort,
                                                          r.data_scope,
                                                          r.STATUS AS role_status
                                                      FROM
                                                          sys_user u
                                                              LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
                                                              LEFT JOIN sys_user_role ur ON u.user_id = ur.user_id
                                                              LEFT JOIN sys_role r ON r.role_id = ur.role_id
                                                  ) u ON t.user_id = u.user_id
                                                      LEFT JOIN sys_theory c ON t.class_id = c.class_id UNION
                                              SELECT
                                                  t.user_id,
                                                  u.login_name,
                                                  u.user_name,
                                                  u.dept_id,
                                                  u.dept_name,
                                                  u.email,
                                                  u.sex,
                                                  u.STATUS,
                                                  u.del_flag,
                                                  t.class_id,
                                                  t.class_type,
                                                  c.class_title,
                                                  c.class_credit,
                                                  t.complete_flag,
                                                  t.complete_time
                                              FROM
                                                  ( SELECT * FROM sys_user_class WHERE class_type = '1' ) t
                                                      LEFT JOIN (
                                                      SELECT
                                                          u.user_id,
                                                          u.dept_id,
                                                          u.login_name,
                                                          u.user_name,
                                                          u.user_type,
                                                          u.email,
                                                          u.avatar,
                                                          u.phonenumber,
                                                          u.sex,
                                                          u.STATUS,
                                                          u.del_flag,
                                                          d.parent_id,
                                                          d.dept_name,
                                                          d.order_num,
                                                          d.leader,
                                                          d.STATUS AS dept_status,
                                                          r.role_id,
                                                          r.role_name,
                                                          r.role_key,
                                                          r.role_sort,
                                                          r.data_scope,
                                                          r.STATUS AS role_status
                                                      FROM
                                                          sys_user u
                                                              LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
                                                              LEFT JOIN sys_user_role ur ON u.user_id = ur.user_id
                                                              LEFT JOIN sys_role r ON r.role_id = ur.role_id
                                                  ) u ON t.user_id = u.user_id
                                                      LEFT JOIN sys_practice c ON t.class_id = c.class_id UNION
                                              SELECT
                                                  t.user_id,
                                                  u.login_name,
                                                  u.user_name,
                                                  u.dept_id,
                                                  u.dept_name,
                                                  u.email,
                                                  u.sex,
                                                  u.STATUS,
                                                  u.del_flag,
                                                  t.class_id,
                                                  t.class_type,
                                                  c.class_title,
                                                  c.class_credit,
                                                  t.complete_flag,
                                                  t.complete_time
                                              FROM
                                                  ( SELECT * FROM sys_user_class WHERE class_type = '2' ) t
                                                      LEFT JOIN (
                                                      SELECT
                                                          u.user_id,
                                                          u.dept_id,
                                                          u.login_name,
                                                          u.user_name,
                                                          u.user_type,
                                                          u.email,
                                                          u.avatar,
                                                          u.phonenumber,
                                                          u.sex,
                                                          u.STATUS,
                                                          u.del_flag,
                                                          d.parent_id,
                                                          d.dept_name,
                                                          d.order_num,
                                                          d.leader,
                                                          d.STATUS AS dept_status,
                                                          r.role_id,
                                                          r.role_name,
                                                          r.role_key,
                                                          r.role_sort,
                                                          r.data_scope,
                                                          r.STATUS AS role_status
                                                      FROM
                                                          sys_user u
                                                              LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
                                                              LEFT JOIN sys_user_role ur ON u.user_id = ur.user_id
                                                              LEFT JOIN sys_role r ON r.role_id = ur.role_id
                                                  ) u ON t.user_id = u.user_id
                                                      LEFT JOIN sys_elearning c ON t.class_id = c.class_id
                                          ) t
                                      GROUP BY
                                          t.user_id,
                                          t.user_name,
                                          t.login_name,
                                          t.dept_id,
                                          t.dept_name,
                                          t.email,
                                          t.sex,
                                          t.STATUS,
                                          t.del_flag,
                                          t.class_type,
                                          t.complete_flag