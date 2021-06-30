package com.jl.common.enums;

public enum ActivityStatus {
    PENDINGREVIEW ("待审核", 0), PROCESSING("进行中", 1), OVER("已结束", 2);



    private String name;
    private Integer status;


    ActivityStatus(String name, Integer index) {
        this.name = name;
        this.status = index;
    }

    public static String getName(Integer status) {
        for (ActivityStatus c : ActivityStatus.values()) {
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
