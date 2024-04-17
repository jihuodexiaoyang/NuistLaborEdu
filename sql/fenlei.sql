CREATE
OR REPLACE VIEW sys_shfwsj_sum_v AS SELECT
                                        t.user_id,
                                        count( t.user_id ) AS total_sign,
                                        sum( t.class_credit ) AS total_credit
                                    FROM
                                        (
                                            SELECT
                                                t1.user_id AS user_id,
                                                t1.class_type AS class_type,
                                                t1.class_id AS class_id,
                                                t1.create_time AS create_time,
                                                t1.complete_flag AS complete_flag,
                                                t1.complete_time AS complete_time,
                                                t2.class_credit AS class_credit
                                            FROM
                                                ( SELECT user_id, class_type, class_id, create_time, complete_flag, complete_time FROM sys_user_class WHERE class_type = '2' AND complete_flag = '1' ) t1
                                                    LEFT JOIN sys_practice t2 ON t1.class_id = t2.class_id
                                        ) t
                                    GROUP BY
                                        t.user_id