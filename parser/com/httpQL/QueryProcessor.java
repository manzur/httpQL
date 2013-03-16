
// line 1 "./parser/com/httpQL/QueryProcessor.rl"
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
	
	
// line 60 "./parser/com/httpQL/QueryProcessor.rl"



	public QueryProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	
	
// line 36 "./parser/com/httpQL/QueryProcessor.java"
private static byte[] init__querer_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    2,
	    5,    6
	};
}

private static final byte _querer_actions[] = init__querer_actions_0();


private static short[] init__querer_key_offsets_0()
{
	return new short [] {
	    0,    0,    6,    8,   10,   12,   14,   16,   19,   22,   25,   30,
	   32,   34,   36,   39,   39,   41,   43,   45,   47,   49,   51,   53,
	   55,   57,   59,   62,   67,   72,   77,   82,   87,   90,   99,  107,
	  113,  117,  123,  132,  142,  145,  152,  157,  162,  172,  182,  192,
	  202,  210
	};
}

private static final short _querer_key_offsets[] = init__querer_key_offsets_0();


private static char[] init__querer_trans_keys_0()
{
	return new char [] {
	   68,   83,   85,  100,  115,  117,   69,  101,   76,  108,   69,  101,
	   84,  116,   69,  101,   32,    9,   13,   32,    9,   13,   32,    9,
	   13,   32,   70,  102,    9,   13,   82,  114,   79,  111,   77,  109,
	   32,    9,   13,   69,  101,   76,  108,   69,  101,   67,   99,   84,
	  116,   80,  112,   68,  100,   65,   97,   84,  116,   69,  101,   32,
	    9,   13,   32,   87,  119,    9,   13,   32,   72,  104,    9,   13,
	   32,   69,  101,    9,   13,   32,   82,  114,    9,   13,   32,   69,
	  101,    9,   13,   32,    9,   13,   32,   87,  119,    9,   13,   65,
	   90,   97,  122,   32,   61,    9,   13,   65,   90,   97,  122,   32,
	   61,   87,  119,    9,   13,   32,   34,    9,   13,   32,   34,   87,
	  119,    9,   13,   32,    9,   13,   48,   57,   65,   90,   97,  122,
	   32,   34,    9,   13,   48,   57,   65,   90,   97,  122,   32,    9,
	   13,   32,   65,   87,   97,  119,    9,   13,   32,   78,  110,    9,
	   13,   32,   68,  100,    9,   13,   32,   61,   72,  104,    9,   13,
	   65,   90,   97,  122,   32,   61,   69,  101,    9,   13,   65,   90,
	   97,  122,   32,   61,   82,  114,    9,   13,   65,   90,   97,  122,
	   32,   61,   69,  101,    9,   13,   65,   90,   97,  122,   32,   61,
	    9,   13,   65,   90,   97,  122,   32,   61,   87,  119,    9,   13,
	   65,   90,   97,  122,    0
	};
}

private static final char _querer_trans_keys[] = init__querer_trans_keys_0();


private static byte[] init__querer_single_lengths_0()
{
	return new byte [] {
	    0,    6,    2,    2,    2,    2,    2,    1,    1,    1,    3,    2,
	    2,    2,    1,    0,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    1,    3,    3,    3,    3,    3,    1,    3,    2,    4,
	    2,    4,    1,    2,    1,    5,    3,    3,    4,    4,    4,    4,
	    2,    4
	};
}

private static final byte _querer_single_lengths[] = init__querer_single_lengths_0();


private static byte[] init__querer_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    1,    1,    1,    1,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    1,    1,    1,    1,    1,    1,    3,    3,    1,
	    1,    1,    4,    4,    1,    1,    1,    1,    3,    3,    3,    3,
	    3,    3
	};
}

private static final byte _querer_range_lengths[] = init__querer_range_lengths_0();


private static short[] init__querer_index_offsets_0()
{
	return new short [] {
	    0,    0,    7,   10,   13,   16,   19,   22,   25,   28,   31,   36,
	   39,   42,   45,   48,   49,   52,   55,   58,   61,   64,   67,   70,
	   73,   76,   79,   82,   87,   92,   97,  102,  107,  110,  117,  123,
	  129,  133,  139,  145,  152,  155,  162,  167,  172,  180,  188,  196,
	  204,  210
	};
}

private static final short _querer_index_offsets[] = init__querer_index_offsets_0();


private static byte[] init__querer_indicies_0()
{
	return new byte [] {
	    0,    2,    3,    0,    2,    3,    1,    4,    4,    1,    5,    5,
	    1,    6,    6,    1,    7,    7,    1,    8,    8,    1,    9,    9,
	    1,    9,    9,   10,   11,   11,   10,   11,   12,   12,   11,    1,
	   13,   13,    1,   14,   14,    1,   15,   15,    1,   16,   16,    1,
	   17,   18,   18,    1,   19,   19,    1,   20,   20,    1,   21,   21,
	    1,   22,   22,    1,   23,   23,    1,   24,   24,    1,   25,   25,
	    1,   26,   26,    1,   27,   27,    1,   28,   28,   17,   28,   29,
	   29,   28,   17,   28,   30,   30,   28,   17,   28,   31,   31,   28,
	   17,   28,   32,   32,   28,   17,   28,   33,   33,   28,   17,   34,
	   34,   17,   28,   36,   36,   28,   35,   35,   17,   37,   38,   37,
	   35,   35,   17,   37,   38,   29,   29,   37,   17,   39,   40,   39,
	   17,   39,   40,   29,   29,   39,   17,   28,   28,   41,   41,   41,
	   17,   28,   42,   28,   41,   41,   41,   17,   43,   43,   17,   28,
	   44,   29,   44,   29,   28,   17,   28,   45,   45,   28,   17,   28,
	   33,   33,   28,   17,   37,   38,   46,   46,   37,   35,   35,   17,
	   37,   38,   47,   47,   37,   35,   35,   17,   37,   38,   48,   48,
	   37,   35,   35,   17,   37,   38,   49,   49,   37,   35,   35,   17,
	   50,   38,   50,   35,   35,   17,   37,   38,   36,   36,   37,   35,
	   35,   17,    0
	};
}

private static final byte _querer_indicies[] = init__querer_indicies_0();


private static byte[] init__querer_trans_targs_0()
{
	return new byte [] {
	    2,    0,   16,   21,    3,    4,    5,    6,    7,    8,    9,   10,
	   11,   12,   13,   14,   15,   26,   17,   18,   19,   20,    7,   22,
	   23,   24,   25,    7,   27,   28,   29,   30,   31,   32,   33,   34,
	   44,   35,   36,   37,   38,   39,   40,   41,   42,   43,   45,   46,
	   47,   48,   49
	};
}

private static final byte _querer_trans_targs[] = init__querer_trans_targs_0();


private static byte[] init__querer_trans_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    7,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,    0,
	    0,    0,    0,    5,    0,    0,    0,    0,    0,    0,    0,    1,
	    1,    0,    0,    0,    0,    1,    0,    0,    0,    0,    1,    1,
	    1,    1,    0
	};
}

private static final byte _querer_trans_actions[] = init__querer_trans_actions_0();


private static byte[] init__querer_to_state_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    9,    0,
	    0,    0,    0,    0,   11,    0,    0,    0,    9,    9,    9,    9,
	    9,    0
	};
}

private static final byte _querer_to_state_actions[] = init__querer_to_state_actions_0();


static final int querer_start = 1;
static final int querer_first_final = 26;
static final int querer_error = 0;

static final int querer_en_main = 1;


// line 69 "./parser/com/httpQL/QueryProcessor.rl"
	
	public Integer process(String queryText) {
		int cs;
		
		char[] data = queryText.toCharArray();
				
		int p = 0;
		int pe = data.length;
		int eof = pe;
		
		
// line 221 "./parser/com/httpQL/QueryProcessor.java"
	{
	cs = querer_start;
	}

// line 80 "./parser/com/httpQL/QueryProcessor.rl"
		
// line 228 "./parser/com/httpQL/QueryProcessor.java"
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
// line 25 "./parser/com/httpQL/QueryProcessor.rl"
	{
			builder.append(data[p]);
			System.out.println("mm " + builder.toString());
		}
	break;
	case 1:
// line 34 "./parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.SELECT;}
	break;
	case 2:
// line 35 "./parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.UPDATE;}
	break;
	case 3:
// line 36 "./parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.DELETE;}
	break;
// line 327 "./parser/com/httpQL/QueryProcessor.java"
			}
		}
	}

case 2:
	_acts = _querer_to_state_actions[cs];
	_nacts = (int) _querer_actions[_acts++];
	while ( _nacts-- > 0 ) {
		switch ( _querer_actions[_acts++] ) {
	case 4:
// line 38 "./parser/com/httpQL/QueryProcessor.rl"
	{attributeName = toStringAndClean();}
	break;
	case 5:
// line 44 "./parser/com/httpQL/QueryProcessor.rl"
	{
					attributeValue = toStringAndClean();
					System.out.println("aValue " + attributeValue);
				}
	break;
	case 6:
// line 49 "./parser/com/httpQL/QueryProcessor.rl"
	{attributes.put(attributeName, attributeValue);}
	break;
// line 352 "./parser/com/httpQL/QueryProcessor.java"
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
case 5:
	}
	break; }
	}

// line 81 "./parser/com/httpQL/QueryProcessor.rl"
		
		if(cs >= querer_first_final) {
			System.out.println("query got accepted");
			
			for(String key : attributes.keySet()) {
				System.out.println("attr-name: " + key + " = " + attributes.get(key));
			}
		}

		return -1;
	}
}
