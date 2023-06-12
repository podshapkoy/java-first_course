package org.example.Description;

import java.awt.*;
import java.time.LocalDateTime;

public class SpaceMarine {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private float health;
    private Integer heartCount;
    private AstartesCategory category;
    private Weapon weaponType;
    private Chapter chapter;

    public SpaceMarine(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate,
                       Float health, Integer heartCount, AstartesCategory category, Weapon weaponType, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.category = category;
        this.weaponType = weaponType;
        this.chapter = chapter;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Chapter getChapter() {
        return chapter;
    }

    @Override
    public String toString() {
        if (id == null) {
            return "Коллекция пуста";
        }
        return "SpaceMarine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", health=" + health +
                ", heartCount=" + heartCount +
                ", category=" + category +
                ", weaponType=" + weaponType +
                ", chapter=" + chapter +
                '}';
    }

    //метод для получения значения поля heartCount
    public int getHeartCount() {
        return heartCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getHealth() {
        return (int) health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public Object getCreationDateAsString() {
        return creationDate;
    }
}
