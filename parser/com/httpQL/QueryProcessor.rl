package com.httpQL;

import java.util.HashMap;
import java.util.Map;

public class QueryProcessor {
	private IQueryDB queryDB;
	
	private QueryMethod method;
	private StringBuilder builder = new StringBuilder();
	private String attributeName;
	private String attributeValue;
	
	private Map<String, String> attributes = new HashMap<>();
	
	private String toStringAndClean(){
		String result = builder.toString();
		builder = new StringBuilder();
		return result;
	}
	
	%%{
		machine querer;
		
		action memchar {
			builder.append(fc);
			System.out.println("mm " + builder.toString());
		}
		
		action debug {
			System.out.println("debug message");
		}

		method = /select/i @{method = QueryMethod.SELECT;} 
			   | /update/i @{method = QueryMethod.UPDATE;} 
			   | /delete/i @{method = QueryMethod.DELETE;};
		
		name = alpha+ $memchar %~{attributeName = toStringAndClean();};
		
		value = ["]
				(alnum+ $memchar) 
				["]
				
				%~{
					attributeValue = toStringAndClean();
					System.out.println("aValue " + attributeValue);
				};
							
		condition = name space* "=" space* value %~{attributes.put(attributeName, attributeValue);}; 
		
		conditions = /where/i space condition 
					 (space /and/i  space condition)*;
		
		tag = "*" | (any - space)+;
		
		site = any+	;
		
		main := method space+ tag space+ /from/i space+ site (space+ conditions)?; 
		
	}%%


	public QueryProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	
	%% write data;
	
	public Integer process(String queryText) {
		int cs;
		
		char[] data = queryText.toCharArray();
				
		int p = 0;
		int pe = data.length;
		int eof = pe;
		
		%% write init;
		%% write exec;
		
		if(cs >= querer_first_final) {
			System.out.println("query got accepted");
			
			for(String key : attributes.keySet()) {
				System.out.println("attr-name: " + key + " = " + attributes.get(key));
			}
		}

		return -1;
	}
}
