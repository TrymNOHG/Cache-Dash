package edu.ntnu.idatt2105.g6.backend.metamodel.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.listing.KeyInfo;
import edu.ntnu.idatt2105.g6.backend.model.users.Bookmark;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {

    public static volatile SingularAttribute<Item, Long> itemId;
    public static volatile SingularAttribute<Item, String> briefDesc;
    public static volatile SingularAttribute<Item, String> fullDesc;
    public static volatile SingularAttribute<Item, String> address;
    public static volatile SingularAttribute<Item, String> county;
    public static volatile SingularAttribute<Item, Category> category;
    public static volatile SingularAttribute<Item, Double> price;
    public static volatile SetAttribute<Item, KeyInfo> keyInfoList;
    public static volatile SetAttribute<Item, Bookmark> bookmarkerList;
    public final static String CATEGORY = "category";

}
