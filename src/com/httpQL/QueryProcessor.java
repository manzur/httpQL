
// line 1 "parser/com/httpQL/QueryProcessor.rl"
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
	
	
// line 90 "parser/com/httpQL/QueryProcessor.rl"


	public QueryProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}
	
	
// line 42 "parser/com/httpQL/QueryProcessor.java"
private static byte[] init__querer_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,   10,    2,    6,    9,
	    2,    6,   11,    2,    8,    9
	};
}

private static final byte _querer_actions[] = init__querer_actions_0();


private static short[] init__querer_key_offsets_0()
{
	return new short [] {
	    0,    0,    6,    8,   10,   12,   14,   16,   17,   20,   23,   26,
	   28,   30,   32,   33,   36,   39,   41,   43,   45,   47,   48,   52,
	   56,   58,   60,   66,   73,   76,   78,   80,   82,   84,   86,   88,
	   90,   92,   94,   96,   98,  100,  101,  104,  107,  110,  112,  114,
	  115,  119,  123,  125,  127,  133,  140,  141,  146,  148,  150,  152,
	  154,  156,  158,  159,  163,  167,  169,  171,  177,  184,  187,  188
	};
}

private static final short _querer_key_offsets[] = init__querer_key_offsets_0();


private static char[] init__querer_trans_keys_0()
{
	return new char [] {
	   68,   83,   85,  100,  115,  117,   69,  101,   76,  108,   69,  101,
	   84,  116,   69,  101,   32,   32,    9,   13,   32,    9,   13,   32,
	   70,  102,   82,  114,   79,  111,   77,  109,   32,   32,    9,   13,
	   32,   87,  119,   72,  104,   69,  101,   82,  114,   69,  101,   32,
	   32,   61,    9,   13,   32,   61,    9,   13,   32,   61,   32,   34,
	   48,   57,   65,   90,   97,  122,   34,   48,   57,   65,   90,   97,
	  122,   32,   65,   97,   78,  110,   68,  100,   69,  101,   76,  108,
	   69,  101,   67,   99,   84,  116,   80,  112,   68,  100,   65,   97,
	   84,  116,   69,  101,   32,   32,    9,   13,   32,    9,   13,   32,
	   83,  115,   69,  101,   84,  116,   32,   32,   61,    9,   13,   32,
	   61,    9,   13,   32,   61,   32,   34,   48,   57,   65,   90,   97,
	  122,   34,   48,   57,   65,   90,   97,  122,   32,   32,   65,   87,
	   97,  119,   78,  110,   68,  100,   72,  104,   69,  101,   82,  114,
	   69,  101,   32,   32,   61,    9,   13,   32,   61,    9,   13,   32,
	   61,   32,   34,   48,   57,   65,   90,   97,  122,   34,   48,   57,
	   65,   90,   97,  122,   32,    9,   13,   32,    0
	};
}

private static final char _querer_trans_keys[] = init__querer_trans_keys_0();


private static byte[] init__querer_single_lengths_0()
{
	return new byte [] {
	    0,    6,    2,    2,    2,    2,    2,    1,    1,    1,    3,    2,
	    2,    2,    1,    1,    3,    2,    2,    2,    2,    1,    2,    2,
	    2,    2,    0,    1,    3,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    1,    1,    1,    3,    2,    2,    1,
	    2,    2,    2,    2,    0,    1,    1,    5,    2,    2,    2,    2,
	    2,    2,    1,    2,    2,    2,    2,    0,    1,    1,    1,    0
	};
}

private static final byte _querer_single_lengths[] = init__querer_single_lengths_0();


private static byte[] init__querer_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    1,    0,    0,
	    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    1,    1,
	    0,    0,    3,    3,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    1,    1,    0,    0,    0,    0,
	    1,    1,    0,    0,    3,    3,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    1,    1,    0,    0,    3,    3,    1,    0,    0
	};
}

private static final byte _querer_range_lengths[] = init__querer_range_lengths_0();


private static short[] init__querer_index_offsets_0()
{
	return new short [] {
	    0,    0,    7,   10,   13,   16,   19,   22,   24,   27,   30,   34,
	   37,   40,   43,   45,   48,   52,   55,   58,   61,   64,   66,   70,
	   74,   77,   80,   84,   89,   93,   96,   99,  102,  105,  108,  111,
	  114,  117,  120,  123,  126,  129,  131,  134,  137,  141,  144,  147,
	  149,  153,  157,  160,  163,  167,  172,  174,  180,  183,  186,  189,
	  192,  195,  198,  200,  204,  208,  211,  214,  218,  223,  226,  228
	};
}

private static final short _querer_index_offsets[] = init__querer_index_offsets_0();


private static byte[] init__querer_indicies_0()
{
	return new byte [] {
	    0,    2,    3,    0,    2,    3,    1,    4,    4,    1,    5,    5,
	    1,    6,    6,    1,    7,    7,    1,    8,    8,    1,    9,    1,
	    9,    1,   10,   11,    1,   10,   12,   13,   13,    1,   14,   14,
	    1,   15,   15,    1,   16,   16,    1,   17,    1,   17,    1,   18,
	   19,   20,   20,    1,   21,   21,    1,   22,   22,    1,   23,   23,
	    1,   24,   24,    1,   25,    1,   25,    1,    1,   26,   27,   28,
	    1,   26,   27,   28,    1,   28,   29,    1,   30,   30,   30,    1,
	   31,   30,   30,   30,    1,   32,   33,   33,    1,   34,   34,    1,
	   24,   24,    1,   35,   35,    1,   36,   36,    1,   37,   37,    1,
	   38,   38,    1,   39,   39,    1,   40,   40,    1,   41,   41,    1,
	   42,   42,    1,   43,   43,    1,   44,   44,    1,   45,    1,   45,
	    1,   46,   47,    1,   46,   48,   49,   49,    1,   50,   50,    1,
	   51,   51,    1,   52,    1,   52,    1,    1,   53,   54,   55,    1,
	   53,   54,   55,    1,   55,   56,    1,   57,   57,   57,    1,   58,
	   57,   57,   57,    1,   59,    1,   60,   61,   62,   61,   62,    1,
	   63,   63,    1,   51,   51,    1,   64,   64,    1,   65,   65,    1,
	   66,   66,    1,   67,   67,    1,   68,    1,   68,    1,    1,   69,
	   70,   71,    1,   69,   70,   71,    1,   71,   72,    1,   73,   73,
	   73,    1,   74,   73,   73,   73,    1,   75,    1,   18,   76,    1,
	    1,    0
	};
}

private static final byte _querer_indicies[] = init__querer_indicies_0();


private static byte[] init__querer_trans_targs_0()
{
	return new byte [] {
	    2,    0,   31,   36,    3,    4,    5,    6,    7,    8,    9,   10,
	   10,   11,   12,   13,   14,   15,   69,   16,   17,   18,   19,   20,
	   21,   22,   23,   24,   25,   26,   27,   70,   28,   29,   30,   32,
	   33,   34,   35,    7,   37,   38,   39,   40,   41,   42,   43,   44,
	   44,   45,   46,   47,   48,   49,   50,   51,   52,   53,   54,   55,
	   55,   56,   58,   57,   59,   60,   61,   62,   63,   64,   65,   66,
	   67,   68,   71,   16,   28
	};
}

private static final byte _querer_trans_targs[] = init__querer_trans_targs_0();


private static byte[] init__querer_trans_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    5,    0,    1,   15,
	    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    9,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    3,    0,    0,    0,    0,    7,    0,    1,   19,
	    0,    0,    0,    0,    0,    1,    0,    0,    9,    1,    0,   13,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
	    9,    1,    0,   17,   13
	};
}

private static final byte _querer_trans_actions[] = init__querer_trans_actions_0();


private static byte[] init__querer_to_state_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   11,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   11,   11
	};
}

private static final byte _querer_to_state_actions[] = init__querer_to_state_actions_0();


private static byte[] init__querer_eof_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   27,   21,   24
	};
}

private static final byte _querer_eof_actions[] = init__querer_eof_actions_0();


static final int querer_start = 1;
static final int querer_first_final = 69;
static final int querer_error = 0;

static final int querer_en_main = 1;


// line 97 "parser/com/httpQL/QueryProcessor.rl"
	
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
		
		
// line 261 "parser/com/httpQL/QueryProcessor.java"
	{
	cs = querer_start;
	}

// line 119 "parser/com/httpQL/QueryProcessor.rl"
		
// line 268 "parser/com/httpQL/QueryProcessor.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _querer_key_offsets[cs];
	_trans = _querer_index_offsets[cs];
	_klen = _querer_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _querer_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _querer_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _querer_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _querer_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _querer_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _querer_indicies[_trans];
	cs = _querer_trans_targs[_trans];

	if ( _querer_trans_actions[_trans] != 0 ) {
		_acts = _querer_trans_actions[_trans];
		_nacts = (int) _querer_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _querer_actions[_acts++] )
			{
	case 0:
// line 33 "parser/com/httpQL/QueryProcessor.rl"
	{
			builder.append(data[p]);
		}
	break;
	case 1:
// line 39 "parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.SELECT;}
	break;
	case 2:
// line 40 "parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.DELETE;}
	break;
	case 3:
// line 42 "parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.UPDATE;}
	break;
	case 4:
// line 50 "parser/com/httpQL/QueryProcessor.rl"
	{
					attributeName = toStringAndClean();
				}
	break;
	case 6:
// line 58 "parser/com/httpQL/QueryProcessor.rl"
	{
			QueryCondition condition = new QueryCondition(attributeName, attributeValue);
			attributes.add(condition);
		}
	break;
	case 7:
// line 73 "parser/com/httpQL/QueryProcessor.rl"
	{tag = toStringAndClean();}
	break;
	case 8:
// line 73 "parser/com/httpQL/QueryProcessor.rl"
	{site = toStringAndClean();}
	break;
	case 10:
// line 82 "parser/com/httpQL/QueryProcessor.rl"
	{site = toStringAndClean(); }
	break;
// line 391 "parser/com/httpQL/QueryProcessor.java"
			}
		}
	}

case 2:
	_acts = _querer_to_state_actions[cs];
	_nacts = (int) _querer_actions[_acts++];
	while ( _nacts-- > 0 ) {
		switch ( _querer_actions[_acts++] ) {
	case 5:
// line 54 "parser/com/httpQL/QueryProcessor.rl"
	{
					attributeValue = toStringAndClean();
				}
	break;
// line 407 "parser/com/httpQL/QueryProcessor.java"
		}
	}

	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	int __acts = _querer_eof_actions[cs];
	int __nacts = (int) _querer_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _querer_actions[__acts++] ) {
	case 6:
// line 58 "parser/com/httpQL/QueryProcessor.rl"
	{
			QueryCondition condition = new QueryCondition(attributeName, attributeValue);
			attributes.add(condition);
		}
	break;
	case 8:
// line 73 "parser/com/httpQL/QueryProcessor.rl"
	{site = toStringAndClean();}
	break;
	case 9:
// line 74 "parser/com/httpQL/QueryProcessor.rl"
	{
					Utils.debugMsg("=================");
					Utils.debugMsg("method is " + method);
					Utils.debugMsg("tag is " + tag);
					Utils.debugMsg("site is " + site);
					Utils.debugMsg("=================");
				}
	break;
	case 11:
// line 83 "parser/com/httpQL/QueryProcessor.rl"
	{
				  	Utils.debugMsg("=================");
				  	Utils.debugMsg("method is " + method);
				  	Utils.debugMsg("site is " + site);
				  	Utils.debugMsg("=================");
				}
	break;
// line 456 "parser/com/httpQL/QueryProcessor.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 120 "parser/com/httpQL/QueryProcessor.rl"
		
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
