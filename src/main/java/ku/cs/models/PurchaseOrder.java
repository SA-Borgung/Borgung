package ku.cs.models;

public class PurchaseOrder {

    private String id,weight,species,age;

    public PurchaseOrder(String id, String weight, String species, String age) {
        this.id = id;
        this.weight = weight;
        this.species = species;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
