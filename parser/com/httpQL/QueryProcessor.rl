package com.httpQL;


import java.util.List;
import java.util.LinkedList;

import com.httpQL.ConditionType;
import com.httpQL.IQueryDB;
import com.httpQL.QueryMethod;
import com.httpQL.Query.QueryBuilder;
import com.httpQL.QueryCondition;
import java.util.logging.*;

import java.util.EnumSet;

public class QueryProcessor {
	private Logger logger = CustomLogger.getLogger(QueryProcessor.class.getName());
	
	%%{
		machine QueryParser;

		action memchar {
			builder.append(fc);
		}
				
		sp = [ ];
		
		selectDeleteMethod = /select/i @{method = QueryMethod.SELECT;} 			    
			   	    	   | /delete/i @{method = QueryMethod.DELETE;};
	
	    insertMethod = /insert into/i @{method = QueryMethod.INSERT;};

	    updateMethod = /update/i @{method = QueryMethod.UPDATE;};
		
		name = (any - ( space | "<" | "<=" | "=" | ">=" | ">" ))+ $memchar;
		
		quoted = ['] 
		          (any - ['])+
				 ['];
				  
		value_type = alnum | "@" | "*" | "_" | quoted;
		
		value = (
				(["]
				(value_type+ $memchar) 
				["]) 
				
			  | (value_type+ $memchar))			  
			  ;							
				
		binop = ("<"  @{binaryOperation = ConditionType.LT; }
			  | "<="  @{binaryOperation = ConditionType.LE; }
			  | "="   @{binaryOperation = ConditionType.EQ; }
			  | ">"   @{binaryOperation = ConditionType.GT; } 
			  | ">="  @{binaryOperation = ConditionType.GE; } )
			  ;

	    condition = /limit/i sp+ name sp+ >{attributeName = toStringAndClean(); logger.info("name in " + attributeName);} /by/i sp+ value %{
	    				attributeValue = toStringAndClean();
	    				QueryCondition condition = new QueryCondition(attributeName, attributeValue, ConditionType.LIMIT);
		  				attributes.add(condition);
				  }
				
				  | name sp* >{attributeName = toStringAndClean();} binop sp* value 
				  %{
	    			  attributeValue = toStringAndClean();
					  QueryCondition condition = new QueryCondition(attributeName, attributeValue, binaryOperation);
					  attributes.add(condition);
				  };
		
		conditions = condition 
					 (sp+ /and/i  sp+ condition)*;
		
		conditions_where = /where/i sp+ conditions;
		
		tag = [*]
		    | (any - space)+ $memchar;
		
		site = (any - space)+ $memchar;
		
		values =  any+ $memchar;
 
		
		main := selectDeleteMethod sp+ tag sp+ >{tag = toStringAndClean();} /from/i sp+ site (sp+ conditions_where)? >{site = toStringAndClean();} 
				@{
					logger.info("=================");
					logger.info("method is " + method);
					logger.info("tag is " + tag);
					logger.info("site is " + site);
					logger.info("=================");
				}
			  
			  | insertMethod sp+ site sp+ >{site = toStringAndClean(); } /values/i "(" values %/{
				  	String value = toStringAndClean();
				  	int lastIndex = value.lastIndexOf(')');
				  	value = value.substring(0, lastIndex);

				  	logger.info("=================");
				  	logger.info("method is " + method);
				  	logger.info("site is " + site);
				  	logger.info("put content is " + value);
				  	logger.info("=================");
					
					QueryCondition condition = new QueryCondition("values", value);
	  				attributes.add(condition);
                }

			  | updateMethod sp+ site sp+ >{site = toStringAndClean();} /set/i sp+ conditions 
				%/{
				  	logger.info("=================");
				  	logger.info("method is " + method);
				  	logger.info("site is " + site);
				  	logger.info("=================");
				};
		
	}%%

	%% write data;

	private final IQueryDB queryDB;
	private StringBuilder builder;
	
	public QueryProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}
	
	public Integer process(String queryText) {
		Query query = parse(queryText);
		Integer result = queryDB.putQuery(query);
		
		return result;
	}
	
	private Query parse(String queryText) { 
		String tag, site, attributeName, attributeValue;
		tag = site = attributeName = attributeValue = null;
		
		QueryMethod method = null;
		ConditionType binaryOperation = null;
		builder = new StringBuilder();
		List<QueryCondition> attributes = new LinkedList<>();
		
		int cs;

		char[] data = queryText.toCharArray();
				
		int p = 0,
			pe = data.length,
			eof = pe;
		
		
		logger.info("-------------------------------");
		logger.info("=" + queryText  +"=");
		logger.info("-------------------------------");
		
		%% write init;
		%% write exec;
		
		QueryBuilder result = Query.queryBuilder();
		if(cs >= QueryParser_first_final) {
			
            result.setMethod(method);
			if(EnumSet.of(QueryMethod.SELECT, QueryMethod.DELETE).contains(method)) {
                result.setTag(tag);
			}
            result.setPage(site);

            logger.info("Conditions");
            logger.info("------------------");
			
			for(QueryCondition condition : attributes) {
    			result.addCondition(condition);
    			logger.info(condition.toString());
			}
			
			logger.info("------------------");			
		}
		
		return result.build();
	}

	private String toStringAndClean(){
		String result = builder.toString();
		builder = new StringBuilder();
		
		return result;
	}
	
	
}
