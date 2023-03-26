package edu.ntnu.idatt2105.g6.backend.metamodel.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.Category;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

    public static volatile SingularAttribute<Category, Long> categoryId;
    public static volatile SingularAttribute<Category, String> subCategory;
    public static volatile SetAttribute<Category, Category> subCategories;
    public static volatile SingularAttribute<Category, Category> mainCategory;
    public static volatile ListAttribute<Category, Item> itemsWithCategory;
    public final static String CATEGORYID = "categoryId";
    public final static String MAINCATEGORY = "mainCategory";



}
