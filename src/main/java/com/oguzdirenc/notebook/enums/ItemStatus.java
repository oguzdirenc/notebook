package com.oguzdirenc.notebook.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ItemStatus {

    NOTSTARTED (0, "Item not started"),
    INPROGRESS(1, "Item in progress"),
    DONE(2, "Item finished");

    private int value;
    private String description;

    ItemStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
