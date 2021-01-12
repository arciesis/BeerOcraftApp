package xyz.beerocraft.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xyz.beerocraft.controller.MainCtrl;

import java.io.Serializable;
import java.util.Objects;
import java.util.ResourceBundle;


public class Fermentable implements Serializable {

    //private static final long serialVersionUID = 18273654748474L;
    //private int id;
    private String name;
    private float ebc;
    private float lovibond;
    private float potential;
    private String type;
    //private static  ResourceBundle bundle;

    /**
     * Tab trhat contains all the type ossible of a fermentable
     */
    public static final String[] TYPE_POSSIBLE = {"grain", "extract", "sugar", "adjunct", "other"};

    public static ObservableList<String> malts = FXCollections.observableArrayList();

    public static ObservableList<String> searchingMalts = FXCollections.observableArrayList();

    public static ObservableList<String> maltTypeChoices = FXCollections.observableArrayList();



/*    public Fermentable(String name, float ebc, float lovibond, float potential, String type, ResourceBundle resourceBundle) {
        bundle = resourceBundle;

        TYPE_POSSIBLE[0] = bundle.getString("grain");
        TYPE_POSSIBLE[1] = bundle.getString("extract");
        TYPE_POSSIBLE[2] = bundle.getString("sugar");
        TYPE_POSSIBLE[3] = bundle.getString("adjunct");
        TYPE_POSSIBLE[4] = bundle.getString("other");

        this.name = name;
        this.ebc = ebc;
        this.lovibond = lovibond;
        if (potential > 0 && potential <= 100)
            this.potential = potential;

        for (String item : TYPE_POSSIBLE) {
            if (item.trim().equalsIgnoreCase(type)) {
                this.type = type;
            }
        }
    }*/

    public Fermentable(String name, float ebc, float lovibond, float potential, String type) {

        this.name = name;
        this.ebc = ebc;
        this.lovibond = lovibond;
        if (potential > 0 && potential <= 100)
            this.potential = potential;

        for (String item : Fermentable.TYPE_POSSIBLE) {
            if (item.trim().equalsIgnoreCase(type)) {
                this.type = type;
            }
        }
    }

/*    public Fermentable(ResourceBundle resourceBundle) {
        this.bundle = resourceBundle;

        TYPE_POSSIBLE[0] = bundle.getString("grain");
        TYPE_POSSIBLE[1] = bundle.getString("extract");
        TYPE_POSSIBLE[2] = bundle.getString("sugar");
        TYPE_POSSIBLE[3] = bundle.getString("adjunct");
        TYPE_POSSIBLE[4] = bundle.getString("other");

        this.name = "";
        setEbc(0);
        setLovibond(0);
        setPotential(0);
        setType(TYPE_POSSIBLE[0]);
    }*/

    public Fermentable() {

        this.name = "";
        setEbc(0);
        setLovibond(0);
        setPotential(0);
        setType(Fermentable.TYPE_POSSIBLE[0]);
    }

/*    public int getId() {
        return this.id;
    }*/

    public String getName() {
        return name;
    }

    public float getEbc() {
        return ebc;
    }

    public float getLovibond() {
        return lovibond;
    }

    public float getPotential() {
        return potential;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEbc(float ebc) {
        this.ebc = ebc;
    }

    public void setLovibond(float lovibond) {
        this.lovibond = lovibond;
    }

    public void setPotential(float potential) {
        if (potential >= 0 || potential <= 100)
            this.potential = potential;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Malts{" +
                "name='" + name + '\'' +
                ", ebc=" + ebc +
                ", lovibond=" + lovibond +
                ", potentiel=" + potential +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fermentable)) return false;
        Fermentable malt = (Fermentable) o;
        return ebc == malt.ebc &&
                lovibond == malt.lovibond &&
                potential == malt.potential &&
                name.equals(malt.name) &&
                type.equals(malt.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ebc, lovibond, potential, type);
    }
}
