package com.httpQL;

/**
 * 
 * Contains processed queries that was given by users. All clients of this DB
 * should extract data by ID.
 * 
 * @author manzur
 * 
 * 
 */
// TODO: implementor should be singleton

public interface IQueryDB {

	boolean putQuery(Query query);

	Query getQuery(Integer id);

}
