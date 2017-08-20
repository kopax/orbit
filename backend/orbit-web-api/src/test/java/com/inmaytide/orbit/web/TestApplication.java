package com.inmaytide.orbit.web;

public class TestApplication {

    public static void main(String... args) {
        System.out.println("select distinct(r.code) code from sys_role r " +
                "left join sys_user_role ur on ur.r_id = r.id " +
                        "left join sys_user u on u.id = ur.u_id " +
                        "where u.username = ?1");
    }
}
