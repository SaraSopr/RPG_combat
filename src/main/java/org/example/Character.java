package org.example;

public class Character {
    int health = 1000;
    int level = 1;
    boolean alive = true;

    public Character() {
    }

    public Character(int health, int level, boolean alive) {
        this.health = health;
        this.level = level;
        this.alive = alive;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Character that = (Character) obj;
        return this.health == that.health &&
                this.level == that.level &&
                this.alive == that.alive;

    }
}
