package info.devexchanges.carousellayout.model;

import java.io.Serializable;

public class HeroModel implements Serializable{
    private final String name;
    private final int age;
    private final int rscCat;

    public HeroModel(String name, int age, int rscCat) {
        this.name = name;
        this.age = age;
        this.rscCat = rscCat;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getRscCat() {
        return rscCat;
    }
}
