create view sys_credit_prac_v  as SELECT
                                      t.user_id,
                                      t.login_name,
                                      t.user_name,
                                      t.dept_id,
                                      t.dept_name,
                                      t.email,
                                      t.sex,
                                      t.STATUS,
                                      c.user_credit
                                  FROM
                                      (
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
                                      ) t
                                          LEFT JOIN sys_credit_prac c ON t.login_name = c.user_xuehao