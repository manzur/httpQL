package com.httpQL;


import java.util.List;
import java.util.LinkedList;

import org.omg.CORBA.portable.Delegate;

import com.httpQL.QueryCondition;
import com.httpQL.Utils;

public class QueryProcessor {
	private IQueryDB queryDB;
	
	private String tag;
	private String site;
	private String attributeName;
	private String attributeValue;
	private StringBuilder builder;
	private QueryMethod method;
	
	private List<QueryCondition> attributes;
	
	private String toStringAndClean(){
		String result = builder.toString();
		builder = new StringBuilder();
		return result;
	}
	
	%%{
		machine querer;

		action memchar {
			builder.append(fc);
		}
		
		sp = [ ];
		
		methodType1 = /select/i @{method = QueryMethod.SELECT;} 			    
			   	    | /delete/i @{method = QueryMethod.DELETE;};
	
	    methodType2 = /update/i @{method = QueryMethod.UPDATE;};
		
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
							
		condition = name sp* "=" sp* value %{
			QueryCondition condition = new QueryCondition(attributeName, attributeValue);
			attributes.add(condition);
		};
		
		conditions = condition 
					 (sp+ /and/i  sp+ condition)*;
		
		conditions_where = /where/i sp+ conditions;
		
		tag = [*]
		    | (any - space)+ $memchar;
		
		site = (any - space)+ $memchar;
		
		main := methodType1 sp+ tag sp+ >{tag = toStringAndClean();} /from/i sp+ site  (sp+ conditions_where)? >{site = toStringAndClean();}
				%/{
					Utils.debugMsg("=================");
					Utils.debugMsg("method is " + method);
					Utils.debugMsg("tag is " + tag);
					Utils.debugMsg("site is " + site);
					Utils.debugMsg("=================");
				}
			  
			  | methodType2 sp+ site sp+ >{site = toStringAndClean(); } /set/i sp+ conditions sp+ /where/i sp+ condition 
				%/{
				  	Utils.debugMsg("=================");
				  	Utils.debugMsg("method is " + method);
				  	Utils.debugMsg("site is " + site);
				  	Utils.debugMsg("=================");
				};
		
	}%%

	public QueryProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}
	
	%% write data;
	
	public Integer process(String queryText) {
		return -1;
	}
	
	Query parse(String queryText) { 
		initFields();
		
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
		
		Query result = new Query();
		if(cs >= querer_first_final) {
			
			result.method = method;
			if(result.method != QueryMethod.UPDATE) {
				result.tag = tag;
			}
			result.page = site;

			result.conditions.addAll(attributes);
			
			Utils.debugMsg("Conditions");
			Utils.debugMsg("------------------");
			
			for(QueryCondition condition : attributes) {
				Utils.debugMsg(condition.toString());
			}
			
			Utils.debugMsg("------------------");
			
		}
		
		return result;
	}
	
	private void initFields() {
		tag = null;
		site = null;
		method = null;
		attributeName = null;
		attributeValue = null;
		
		builder = new StringBuilder();
		attributes = new LinkedList<>();
	}

}
