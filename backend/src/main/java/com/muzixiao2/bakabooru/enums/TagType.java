package com.muzixiao2.bakabooru.enums;

public enum TagType {
    ARTIST("artist", "#E91E63"),
    SOURCE("source", "#9C27B0"),
    CHARACTER("character", "#3F51B5"),
    RATING("rating", "#009688"),
    ACTION("action", "#4CAF50"),
    SCENE("scene", "#FF9800"),
    CLOTHING("clothing", "#795548"),
    GENERAL("general", "#607D8B");

    private final String name;
    private final String color;

    TagType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public static TagType fromName(String name) {
        for (TagType type : values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的标签类型: " + name);
    }
}
