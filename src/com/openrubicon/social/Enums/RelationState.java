package com.openrubicon.social.Enums;

/**
 * Created by Quinn on 9/30/2017.
 */
public enum RelationState {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    DENIED("Denied");

    private final String name;

    @Override
    public String toString() {return this.name;}

    RelationState(final String name){
        this.name = name;
    }

    public static RelationState fromString(String name){
        for(RelationState n : RelationState.values()){
            if(n.name.equalsIgnoreCase(name)){
                return n;
            }
        }
        return null;
    }

}
