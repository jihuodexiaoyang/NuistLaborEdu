SELECT
    *
FROM
    (
        SELECT
            T1.user_id,
            T1.login_name,
            T1.user_name,
            T1.dept_id,
            T1.dept_name,
            T1.email,
            T1.sex,
            T1.user_status,
            T2.total_sign AS sign1,
            T2.total_credit AS credit1,
            T3.total_sign AS sign2,
            T3.total_credit AS credit2,
            T4.total_sign AS sign3,
            T4.total_credit AS credit3,
            T5.user_credit AS credit4,
            T6.user_credit AS credit5,
            round(( T5.user_credit + T6.user_credit )/ 2, 1 ) AS credit6
        FROM
            sys_ser_v T1
                LEFT JOIN sys_theory_sum_v T2 ON T1.user_id = T2.user_id
                LEFT JOIN sys_xyldsj_sum_v T3 ON T1.user_id = T3.user_id
                LEFT JOIN sys_shfwsj_sum_v T4 ON T1.user_id = T4.user_id
                LEFT JOIN sys_credit_online_v T5 ON T1.user_id = T5.user_id
                LEFT JOIN sys_credit_prac_v T6 ON T1.user_id = T6.user_id
    ) t
