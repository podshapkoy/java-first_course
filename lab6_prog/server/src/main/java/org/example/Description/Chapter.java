package org.example.Description;

import java.util.Objects;

public class Chapter {

    private String name;
    private String parentLegion;
    private Long marinesCount;
    private String world;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Chapter other = (Chapter) obj;
        return Objects.equals(name, other.name) &&
                Objects.equals(world, other.world) &&
                Objects.equals(marinesCount, other.marinesCount) &&
                Objects.equals(parentLegion, other.parentLegion);
    }

    public Chapter(String name, String parentLegion, Long marinesCount, String world) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Поле name не может быть null или пустым");
        }
        if (marinesCount != null && marinesCount <= 0) {
            throw new IllegalArgumentException("Значение поля marinesCount должно быть больше 0");
        }
        if (marinesCount != null && marinesCount > 1000) {
            throw new IllegalArgumentException("Значение поля marinesCount не может быть больше 1000");
        }
        this.name = name;
        this.parentLegion = parentLegion;
        this.marinesCount = marinesCount;
        this.world = world;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", parentLegion='" + parentLegion + '\'' +
                ", marinesCount=" + marinesCount +
                ", world='" + world + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getParentLegion() {
        return parentLegion;
    }

    public int getMarinesCount() {
        return Math.toIntExact(marinesCount);
    }

    public String getWorld() {
        return world;
    }
}

