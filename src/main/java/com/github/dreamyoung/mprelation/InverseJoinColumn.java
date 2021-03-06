package com.github.dreamyoung.mprelation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
 

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface InverseJoinColumn {

    /**
     * (Optional) The name of the foreign key column.
     * The table in which it is found depends upon the
     * context.
     * <ul>
     * <li>If the join is for a OneToOne or ManyToOne
     *  mapping using a foreign key mapping strategy,
     * the foreign key column is in the table of the
     * source entity or embeddable.
     * <li> If the join is for a unidirectional OneToMany mapping
     * using a foreign key mapping strategy, the foreign key is in the
     * table of the target entity.
     * <li> If the join is for a ManyToMany mapping or for a OneToOne
     * or bidirectional ManyToOne/OneToMany mapping using a join
     * table, the foreign key is in a join table.
     * <li> If the join is for an element collection, the foreign
     * key is in a collection table.
     *</ul>
     *
     * <p> Default (only applies if a single join column is used):
     * The concatenation of the following: the name of the
     * referencing relationship property or field of the referencing
     * entity or embeddable class; "_"; the name of the referenced
     * primary key column.
     * If there is no such referencing relationship property or
     * field in the entity, or if the join is for an element collection,
     * the join column name is formed as the
     * concatenation of the following: the name of the entity; "_";
     * the name of the referenced primary key column.
     */
    String name() default "";

    /**
     * (Optional) The name of the column referenced by this foreign
     * key column.
     * <ul>
     * <li> When used with entity relationship mappings other
     * than the cases described here, the referenced column is in the
     * table of the target entity.
     * <li> When used with a unidirectional OneToMany foreign key
     * mapping, the referenced column is in the table of the source
     * entity.
     * <li> When used inside a <code>JoinTable</code> annotation,
     * the referenced key column is in the entity table of the owning
     * entity, or inverse entity if the join is part of the inverse
     * join definition.
     * <li> When used in a <code>CollectionTable</code> mapping, the
     * referenced column is in the table of the entity containing the
     * collection.
     * </ul>
     *
     * <p> Default (only applies if single join column is being
     * used): The same name as the primary key column of the
     * referenced table.
     */
    String referencedColumnName() default "";
    
    String property() default "";

    /**
     * (Optional) Whether the property is a unique key.  This is a
     * shortcut for the <code>UniqueConstraint</code> annotation at
     * the table level and is useful for when the unique key
     * constraint is only a single field. It is not necessary to
     * explicitly specify this for a join column that corresponds to a
     * primary key that is part of a foreign key.
     */
    boolean unique() default false;

    /** (Optional) Whether the foreign key column is nullable. */
    boolean nullable() default true;

    /**
     * (Optional) Whether the column is included in
     * SQL INSERT statements generated by the persistence
     * provider.
     */
    boolean insertable() default true;

    /**
     * (Optional) Whether the column is included in
     * SQL UPDATE statements generated by the persistence
     * provider.
     */
    boolean updatable() default true;

    /**
     * (Optional) The SQL fragment that is used when
     * generating the DDL for the column.
     * <p> Defaults to the generated SQL for the column.
     */
    String columnDefinition() default "";

    /**
     * (Optional) The name of the table that contains
     * the column. If a table is not specified, the column
     * is assumed to be in the primary table of the
     * applicable entity.
     *
     * <p> Default:
     * <ul>
     * <li> If the join is for a OneToOne or ManyToOne mapping
     * using a foreign key mapping strategy, the name of the table of
     * the source entity or embeddable.
     * <li> If the join is for a unidirectional OneToMany mapping
     * using a foreign key mapping strategy, the name of the table of
     * the target entity.
     * <li> If the join is for a ManyToMany mapping or
     * for a OneToOne or bidirectional ManyToOne/OneToMany mapping
     * using a join table, the name of the join table.
     * <li> If the join is for an element collection, the name of the collection table.
     * </ul>
     */
    String table() default "";

	/**
	 * (Optional) The foreign key constraint specification for the join column. This is used only if table generation
	 * is in effect.  Default is provider defined.
	 *
	 * @return The foreign key specification
	 */
	ForeignKey foreignKey() default @ForeignKey();
}