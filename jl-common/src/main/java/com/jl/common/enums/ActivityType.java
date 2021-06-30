package com.jl.common.enums;

public enum ActivityType {

    SCHOLARSHIP ("奖/助学金", 0), OTHERFUNDS("其他基金", 1);



    private String name;
    private Integer status;


    ActivityType(String name, Integer index) {
        this.name = name;
        this.status = index;
    }

    public static String getName(Integer status) {
        for (ActivityType c : ActivityType.values()) {
            if (c.getStatus().equals(status)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
