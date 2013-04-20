package com.httpQL;

import java.util.List;
import java.util.LinkedList;

import com.httpQL.ConditionType;
import com.httpQL.IQueryDB;
import com.httpQL.Query.QueryBuilder;
import com.httpQL.QueryCondition;
import com.httpQL.Utils;

public class QueryProcessor {
	
	%%{
		machine QueryParser;

		action memchar {
			builder.append(fc);
		}
				
		sp = [ ];
		
		selectDeleteMethod = /select/i @{method = QueryMethod.SELECT;} 			    
			   	    	   | /delete/i @{method = QueryMethod.DELETE;};
	
	    updateMethod = /update/i @{method = QueryMethod.UPDATE;};
		
		name = (any - ( space | "=" ))+ $memchar;
		
		value = (["]
				(alnum+ $memchar) 
				["])
				
				>{
					attributeName = toStringAndClean();
				}
				
				%~{
					attributeValue = toStringAndClean();
				};
							
		binop = "<"  @{binaryOperation = ConditionType.LT; }
			  | "<=" @{binaryOperation = ConditionType.LE; }
			  | "="  @{binaryOperation = ConditionType.EQ; }
			  | ">"  @{binaryOperation = ConditionType.GT; } 
			  | ">=" @{binaryOperation = ConditionType.GE; };
			  
		
	    condition = /limit/i sp* name sp* /by/ sp* value %{
	    				QueryCondition condition = new QueryCondition(attributeName, attributeValue, ConditionType.LIMIT);
		  				attributes.add(condition);
				  }
				
				  | name sp* binop sp* value %{
					  	QueryCondition condition = new QueryCondition(attributeName, attributeValue, binaryOperation);
					  	attributes.add(condition);
				  };
		
		conditions = condition 
					 (sp+ /and/i  sp+ condition)*;
		
		conditions_where = /where/i sp+ conditions;
		
		tag = [*]
		    | (any - space)+ $memchar;
		
		site = (any - space)+ $memchar;
		
		main := selectDeleteMethod sp+ tag sp+ >{tag = toStringAndClean();} /from/i sp+ site (sp+ conditions_where)? >{site = toStringAndClean();}
				%/{
					Utils.debugMsg("=================");
					Utils.debugMsg("method is " + method);
					Utils.debugMsg("tag is " + tag);
					Utils.debugMsg("site is " + site);
					Utils.debugMsg("=================");
				}
			  
			  | updateMethod sp+ site sp+ >{site = toStringAndClean(); } /set/i sp+ conditions sp+ /where/i sp+ condition 
				%/{
				  	Utils.debugMsg("=================");
				  	Utils.debugMsg("method is " + method);
				  	Utils.debugMsg("site is " + site);
				  	Utils.debugMsg("=================");
				};
		
	}%%

	%% write data;

	
	private final IQueryDB queryDB;
	private StringBuilder builder;
	
	public QueryProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
		Utils.turnOnVerboseMode();
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
		
		
		Utils.debugMsg("-------------------------------");
		Utils.debugMsg("=" + queryText  +"=");
		Utils.debugMsg("-------------------------------");
		
		%% write init;
		%% write exec;
		
		QueryBuilder result = Query.queryBuilder();
		if(cs >= QueryParser_first_final) {
			
            result.setMethod(method);
			if(method != QueryMethod.UPDATE) {
                result.setTag(tag);
			}
            result.setPage(site);

			Utils.debugMsg("Conditions");
			Utils.debugMsg("------------------");
			
			for(QueryCondition condition : attributes) {
    			result.addCondition(condition);
				Utils.debugMsg(condition.toString());
			}
			
			Utils.debugMsg("------------------");			
		}
		
		return result.build();
	}

	private String toStringAndClean(){
		String result = builder.toString();
		builder = new StringBuilder();
		
		return result;
	}
	
	
}
