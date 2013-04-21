
// line 1 "parser/com/httpQL/QueryProcessor.rl"
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
	
	
// line 116 "parser/com/httpQL/QueryProcessor.rl"


	
// line 27 "parser/com/httpQL/QueryProcessor.java"
private static byte[] init__QueryParser_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,
	   11,    1,   12,    1,   13,    1,   14,    1,   15,    1,   16,    1,
	   17,    1,   18,    1,   19,    2,    0,   16,    2,   11,   20,    2,
	   12,    5,    2,   12,    7,    2,   12,    8,    2,   13,   20
	};
}

private static final byte _QueryParser_actions[] = init__QueryParser_actions_0();


private static short[] init__QueryParser_key_offsets_0()
{
	return new short [] {
	    0,    0,    8,   10,   12,   14,   16,   18,   19,   22,   25,   28,
	   30,   32,   34,   35,   38,   41,   43,   45,   47,   49,   50,   57,
	   63,   67,   79,   90,   99,  100,  101,  111,  114,  116,  118,  119,
	  120,  132,  140,  148,  156,  164,  170,  176,  181,  184,  186,  187,
	  198,  207,  208,  209,  219,  220,  221,  223,  225,  227,  229,  231,
	  232,  234,  236,  238,  240,  241,  244,  247,  250,  252,  254,  256,
	  258,  260,  261,  261,  263,  265,  267,  269,  271,  273,  275,  277,
	  279,  281,  282,  285,  288,  291,  293,  295,  296,  303,  309,  313,
	  325,  336,  345,  346,  347,  357,  360,  362,  364,  365,  366,  378,
	  386,  394,  402,  410,  416,  422,  427,  430,  432,  433,  444,  453,
	  454,  455,  465,  466,  467,  470,  471,  481,  482,  492,  492,  493,
	  503,  504
	};
}

private static final short _QueryParser_key_offsets[] = init__QueryParser_key_offsets_0();


private static char[] init__QueryParser_trans_keys_0()
{
	return new char [] {
	   68,   73,   83,   85,  100,  105,  115,  117,   69,  101,   76,  108,
	   69,  101,   84,  116,   69,  101,   32,   32,    9,   13,   32,    9,
	   13,   32,   70,  102,   82,  114,   79,  111,   77,  109,   32,   32,
	    9,   13,   32,   87,  119,   72,  104,   69,  101,   82,  114,   69,
	  101,   32,   32,   76,  108,    9,   13,   60,   62,   32,   60,   61,
	   62,    9,   13,   32,   60,   61,   62,   32,   34,   39,   42,   61,
	   95,   48,   57,   64,   90,   97,  122,   32,   34,   39,   42,   95,
	   48,   57,   64,   90,   97,  122,   39,   42,   95,   48,   57,   64,
	   90,   97,  122,   39,   39,   34,   39,   42,   95,   48,   57,   64,
	   90,   97,  122,   32,   65,   97,   78,  110,   68,  100,   39,   39,
	   32,   34,   39,   42,   61,   95,   48,   57,   64,   90,   97,  122,
	   32,   60,   61,   62,   73,  105,    9,   13,   32,   60,   61,   62,
	   77,  109,    9,   13,   32,   60,   61,   62,   73,  105,    9,   13,
	   32,   60,   61,   62,   84,  116,    9,   13,   32,   60,   61,   62,
	    9,   13,   32,   60,   61,   62,    9,   13,   32,    9,   13,   60,
	   62,   32,   66,   98,   89,  121,   32,   32,   34,   39,   42,   95,
	   48,   57,   64,   90,   97,  122,   39,   42,   95,   48,   57,   64,
	   90,   97,  122,   39,   39,   34,   39,   42,   95,   48,   57,   64,
	   90,   97,  122,   39,   39,   78,  110,   83,  115,   69,  101,   82,
	  114,   84,  116,   32,   73,  105,   78,  110,   84,  116,   79,  111,
	   32,   32,    9,   13,   32,    9,   13,   32,   86,  118,   65,   97,
	   76,  108,   85,  117,   69,  101,   83,  115,   40,   69,  101,   76,
	  108,   69,  101,   67,   99,   84,  116,   80,  112,   68,  100,   65,
	   97,   84,  116,   69,  101,   32,   32,    9,   13,   32,    9,   13,
	   32,   83,  115,   69,  101,   84,  116,   32,   32,   76,  108,    9,
	   13,   60,   62,   32,   60,   61,   62,    9,   13,   32,   60,   61,
	   62,   32,   34,   39,   42,   61,   95,   48,   57,   64,   90,   97,
	  122,   32,   34,   39,   42,   95,   48,   57,   64,   90,   97,  122,
	   39,   42,   95,   48,   57,   64,   90,   97,  122,   39,   39,   34,
	   39,   42,   95,   48,   57,   64,   90,   97,  122,   32,   65,   97,
	   78,  110,   68,  100,   39,   39,   32,   34,   39,   42,   61,   95,
	   48,   57,   64,   90,   97,  122,   32,   60,   61,   62,   73,  105,
	    9,   13,   32,   60,   61,   62,   77,  109,    9,   13,   32,   60,
	   61,   62,   73,  105,    9,   13,   32,   60,   61,   62,   84,  116,
	    9,   13,   32,   60,   61,   62,    9,   13,   32,   60,   61,   62,
	    9,   13,   32,    9,   13,   60,   62,   32,   66,   98,   89,  121,
	   32,   32,   34,   39,   42,   95,   48,   57,   64,   90,   97,  122,
	   39,   42,   95,   48,   57,   64,   90,   97,  122,   39,   39,   34,
	   39,   42,   95,   48,   57,   64,   90,   97,  122,   39,   39,   32,
	    9,   13,   32,   32,   39,   42,   95,   48,   57,   64,   90,   97,
	  122,   32,   32,   39,   42,   95,   48,   57,   64,   90,   97,  122,
	   32,   32,   39,   42,   95,   48,   57,   64,   90,   97,  122,   32,
	   32,   39,   42,   95,   48,   57,   64,   90,   97,  122,    0
	};
}

private static final char _QueryParser_trans_keys[] = init__QueryParser_trans_keys_0();


private static byte[] init__QueryParser_single_lengths_0()
{
	return new byte [] {
	    0,    8,    2,    2,    2,    2,    2,    1,    1,    1,    3,    2,
	    2,    2,    1,    1,    3,    2,    2,    2,    2,    1,    3,    4,
	    4,    6,    5,    3,    1,    1,    4,    3,    2,    2,    1,    1,
	    6,    6,    6,    6,    6,    4,    4,    1,    3,    2,    1,    5,
	    3,    1,    1,    4,    1,    1,    2,    2,    2,    2,    2,    1,
	    2,    2,    2,    2,    1,    1,    1,    3,    2,    2,    2,    2,
	    2,    1,    0,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    1,    1,    1,    3,    2,    2,    1,    3,    4,    4,    6,
	    5,    3,    1,    1,    4,    3,    2,    2,    1,    1,    6,    6,
	    6,    6,    6,    4,    4,    1,    3,    2,    1,    5,    3,    1,
	    1,    4,    1,    1,    1,    1,    4,    1,    4,    0,    1,    4,
	    1,    4
	};
}

private static final byte _QueryParser_single_lengths[] = init__QueryParser_single_lengths_0();


private static byte[] init__QueryParser_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    1,    0,    0,
	    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    2,    1,
	    0,    3,    3,    3,    0,    0,    3,    0,    0,    0,    0,    0,
	    3,    1,    1,    1,    1,    1,    1,    2,    0,    0,    0,    3,
	    3,    0,    0,    3,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    1,    0,    0,    0,    0,    2,    1,    0,    3,
	    3,    3,    0,    0,    3,    0,    0,    0,    0,    0,    3,    1,
	    1,    1,    1,    1,    1,    2,    0,    0,    0,    3,    3,    0,
	    0,    3,    0,    0,    1,    0,    3,    0,    3,    0,    0,    3,
	    0,    3
	};
}

private static final byte _QueryParser_range_lengths[] = init__QueryParser_range_lengths_0();


private static short[] init__QueryParser_index_offsets_0()
{
	return new short [] {
	    0,    0,    9,   12,   15,   18,   21,   24,   26,   29,   32,   36,
	   39,   42,   45,   47,   50,   54,   57,   60,   63,   66,   68,   74,
	   80,   85,   95,  104,  111,  113,  115,  123,  127,  130,  133,  135,
	  137,  147,  155,  163,  171,  179,  185,  191,  195,  199,  202,  204,
	  213,  220,  222,  224,  232,  234,  236,  239,  242,  245,  248,  251,
	  253,  256,  259,  262,  265,  267,  270,  273,  277,  280,  283,  286,
	  289,  292,  294,  295,  298,  301,  304,  307,  310,  313,  316,  319,
	  322,  325,  327,  330,  333,  337,  340,  343,  345,  351,  357,  362,
	  372,  381,  388,  390,  392,  400,  404,  407,  410,  412,  414,  424,
	  432,  440,  448,  456,  462,  468,  472,  476,  479,  481,  490,  497,
	  499,  501,  509,  511,  513,  516,  518,  526,  528,  536,  537,  539,
	  547,  549
	};
}

private static final short _QueryParser_index_offsets[] = init__QueryParser_index_offsets_0();


private static short[] init__QueryParser_indicies_0()
{
	return new short [] {
	    0,    2,    3,    4,    0,    2,    3,    4,    1,    5,    5,    1,
	    6,    6,    1,    7,    7,    1,    8,    8,    1,    9,    9,    1,
	   10,    1,   10,    1,   11,   12,    1,   11,   13,   14,   14,    1,
	   15,   15,    1,   16,   16,    1,   17,   17,    1,   18,    1,   18,
	    1,   19,   20,   21,   21,    1,   22,   22,    1,   23,   23,    1,
	   24,   24,    1,   25,   25,    1,   26,    1,   26,   28,   28,    1,
	    1,   27,   29,   30,   31,   32,    1,   27,   33,   34,   35,   36,
	    1,   37,   38,   39,   40,   41,   40,   40,   40,   40,    1,   37,
	   38,   39,   40,   40,   40,   40,   40,    1,   42,   43,   43,   43,
	   43,   43,    1,    1,   44,   43,   44,   45,   42,   43,   43,   43,
	   43,   43,    1,   46,   47,   47,    1,   48,   48,    1,   25,   25,
	    1,    1,   49,   40,   49,   37,   38,   39,   40,   50,   40,   40,
	   40,   40,    1,   29,   30,   31,   32,   51,   51,    1,   27,   29,
	   30,   31,   32,   52,   52,    1,   27,   29,   30,   31,   32,   53,
	   53,    1,   27,   29,   30,   31,   32,   54,   54,    1,   27,   55,
	   30,   31,   32,    1,   27,   57,   34,   35,   36,    1,   56,   58,
	    1,    1,   56,   59,   60,   60,    1,   61,   61,    1,   62,    1,
	   62,   63,   64,   65,   65,   65,   65,   65,    1,   66,   67,   67,
	   67,   67,   67,    1,    1,   68,   67,   68,   69,   66,   67,   67,
	   67,   67,   67,    1,    1,   70,   65,   70,   71,   71,    1,   72,
	   72,    1,   73,   73,    1,   74,   74,    1,   75,   75,    1,   76,
	    1,   77,   77,    1,   78,   78,    1,   79,   79,    1,   80,   80,
	    1,   81,    1,   81,    1,   82,   83,    1,   82,   84,   85,   85,
	    1,   86,   86,    1,   87,   87,    1,   88,   88,    1,   89,   89,
	    1,   90,   90,    1,   91,    1,   92,   93,   93,    1,   94,   94,
	    1,   95,   95,    1,   96,   96,    1,   97,   97,    1,   98,   98,
	    1,   99,   99,    1,  100,  100,    1,  101,  101,    1,  102,  102,
	    1,  103,    1,  103,    1,  104,  105,    1,  104,  106,  107,  107,
	    1,  108,  108,    1,  109,  109,    1,  110,    1,  110,  112,  112,
	    1,    1,  111,  113,  114,  115,  116,    1,  111,  117,  118,  119,
	  120,    1,  121,  122,  123,  124,  125,  124,  124,  124,  124,    1,
	  121,  122,  123,  124,  124,  124,  124,  124,    1,  126,  127,  127,
	  127,  127,  127,    1,    1,  128,  127,  128,  129,  126,  127,  127,
	  127,  127,  127,    1,  130,  131,  131,    1,  132,  132,    1,  109,
	  109,    1,    1,  133,  124,  133,  121,  122,  123,  124,  134,  124,
	  124,  124,  124,    1,  113,  114,  115,  116,  135,  135,    1,  111,
	  113,  114,  115,  116,  136,  136,    1,  111,  113,  114,  115,  116,
	  137,  137,    1,  111,  113,  114,  115,  116,  138,  138,    1,  111,
	  139,  114,  115,  116,    1,  111,  141,  118,  119,  120,    1,  140,
	  142,    1,    1,  140,  143,  144,  144,    1,  145,  145,    1,  146,
	    1,  146,  147,  148,  149,  149,  149,  149,  149,    1,  150,  151,
	  151,  151,  151,  151,    1,    1,  152,  151,  152,  153,  150,  151,
	  151,  151,  151,  151,    1,    1,  154,  149,  154,  155,    1,   19,
	  156,    1,  156,   39,   40,   40,   40,   40,   40,    1,  157,    1,
	  157,   64,   65,   65,   65,   65,   65,    1,   92,  158,    1,  158,
	  123,  124,  124,  124,  124,  124,    1,  159,    1,  159,  148,  149,
	  149,  149,  149,  149,    1,    0
	};
}

private static final short _QueryParser_indicies[] = init__QueryParser_indicies_0();


private static short[] init__QueryParser_trans_targs_0()
{
	return new short [] {
	    2,    0,   54,   75,   80,    3,    4,    5,    6,    7,    8,    9,
	   10,   10,   11,   12,   13,   14,   15,  124,   16,   17,   18,   19,
	   20,   21,   22,   23,   37,   24,   25,   26,   36,   24,   25,   26,
	   36,   26,   27,   34,  126,   26,   28,   30,   29,  125,   31,   32,
	   33,   35,   26,   38,   39,   40,   41,   42,   43,   42,   44,   44,
	   45,   46,   47,   48,   52,  128,   49,   51,   50,  127,   53,   55,
	   56,   57,   58,   59,   60,   61,   62,   63,   64,   65,   66,   67,
	   67,   68,   69,   70,   71,   72,   73,   74,  129,   76,   77,   78,
	   79,    7,   81,   82,   83,   84,   85,   86,   87,   88,   88,   89,
	   90,   91,   92,   93,  107,   94,   95,   96,  106,   94,   95,   96,
	  106,   96,   97,  104,  131,   96,   98,  100,   99,  130,  101,  102,
	  103,  105,   96,  108,  109,  110,  111,  112,  113,  112,  114,  114,
	  115,  116,  117,  118,  122,  133,  119,  121,  120,  132,  123,   16,
	   31,   31,  101,  101
	};
}

private static final short _QueryParser_trans_targs[] = init__QueryParser_trans_targs_0();


private static byte[] init__QueryParser_trans_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    5,    0,    1,
	   29,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
	    0,    0,    0,    1,    1,   25,   47,   50,   53,    0,   11,   15,
	   17,    0,    0,    1,   41,   13,    1,    1,    1,   33,    0,    0,
	    0,    1,   19,    1,    1,    1,    1,   25,    1,    0,   21,    0,
	    0,    0,    0,    0,    1,   41,    1,    1,    1,   33,    1,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    7,    0,    1,   35,
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
	    0,    3,    0,    0,    0,    0,    9,    0,    1,   39,    0,    0,
	    0,    0,    0,    1,    1,   25,   47,   50,   53,    0,   11,   15,
	   17,    0,    0,    1,    1,   13,    1,    1,    1,    0,    0,    0,
	    0,    1,   19,    1,    1,    1,    1,   25,    1,    0,   21,    0,
	    0,    0,    0,    0,    1,    1,    1,    1,    1,    0,    1,   31,
	   27,   23,   27,   23
	};
}

private static final byte _QueryParser_trans_actions[] = init__QueryParser_trans_actions_0();


private static byte[] init__QueryParser_eof_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,   31,   27,   27,   23,   23,   37,   56,   56,
	   44,   44
	};
}

private static final byte _QueryParser_eof_actions[] = init__QueryParser_eof_actions_0();


static final int QueryParser_start = 1;
static final int QueryParser_first_final = 124;
static final int QueryParser_error = 0;

static final int QueryParser_en_main = 1;


// line 119 "parser/com/httpQL/QueryProcessor.rl"

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
		
		
// line 347 "parser/com/httpQL/QueryProcessor.java"
	{
	cs = QueryParser_start;
	}

// line 157 "parser/com/httpQL/QueryProcessor.rl"
		
// line 354 "parser/com/httpQL/QueryProcessor.java"
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
	_keys = _QueryParser_key_offsets[cs];
	_trans = _QueryParser_index_offsets[cs];
	_klen = _QueryParser_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _QueryParser_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _QueryParser_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _QueryParser_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _QueryParser_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _QueryParser_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _QueryParser_indicies[_trans];
	cs = _QueryParser_trans_targs[_trans];

	if ( _QueryParser_trans_actions[_trans] != 0 ) {
		_acts = _QueryParser_trans_actions[_trans];
		_nacts = (int) _QueryParser_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _QueryParser_actions[_acts++] )
			{
	case 0:
// line 22 "parser/com/httpQL/QueryProcessor.rl"
	{
			builder.append(data[p]);
		}
	break;
	case 1:
// line 28 "parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.SELECT;}
	break;
	case 2:
// line 29 "parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.DELETE;}
	break;
	case 3:
// line 31 "parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.INSERT;}
	break;
	case 4:
// line 33 "parser/com/httpQL/QueryProcessor.rl"
	{method = QueryMethod.UPDATE;}
	break;
	case 5:
// line 51 "parser/com/httpQL/QueryProcessor.rl"
	{binaryOperation = ConditionType.LT; }
	break;
	case 6:
// line 52 "parser/com/httpQL/QueryProcessor.rl"
	{binaryOperation = ConditionType.LE; }
	break;
	case 7:
// line 53 "parser/com/httpQL/QueryProcessor.rl"
	{binaryOperation = ConditionType.EQ; }
	break;
	case 8:
// line 54 "parser/com/httpQL/QueryProcessor.rl"
	{binaryOperation = ConditionType.GT; }
	break;
	case 9:
// line 55 "parser/com/httpQL/QueryProcessor.rl"
	{binaryOperation = ConditionType.GE; }
	break;
	case 10:
// line 58 "parser/com/httpQL/QueryProcessor.rl"
	{attributeName = toStringAndClean(); logger.info("name in " + attributeName);}
	break;
	case 11:
// line 58 "parser/com/httpQL/QueryProcessor.rl"
	{
	    				attributeValue = toStringAndClean();
	    				QueryCondition condition = new QueryCondition(attributeName, attributeValue, ConditionType.LIMIT);
		  				attributes.add(condition);
				  }
	break;
	case 12:
// line 64 "parser/com/httpQL/QueryProcessor.rl"
	{attributeName = toStringAndClean();}
	break;
	case 13:
// line 65 "parser/com/httpQL/QueryProcessor.rl"
	{
	    			  attributeValue = toStringAndClean();
					  QueryCondition condition = new QueryCondition(attributeName, attributeValue, binaryOperation);
					  attributes.add(condition);
				  }
	break;
	case 14:
// line 84 "parser/com/httpQL/QueryProcessor.rl"
	{tag = toStringAndClean();}
	break;
	case 15:
// line 84 "parser/com/httpQL/QueryProcessor.rl"
	{site = toStringAndClean();}
	break;
	case 16:
// line 85 "parser/com/httpQL/QueryProcessor.rl"
	{
					logger.info("=================");
					logger.info("method is " + method);
					logger.info("tag is " + tag);
					logger.info("site is " + site);
					logger.info("=================");
				}
	break;
	case 17:
// line 93 "parser/com/httpQL/QueryProcessor.rl"
	{site = toStringAndClean(); }
	break;
	case 19:
// line 108 "parser/com/httpQL/QueryProcessor.rl"
	{site = toStringAndClean();}
	break;
// line 526 "parser/com/httpQL/QueryProcessor.java"
			}
		}
	}

case 2:
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
	int __acts = _QueryParser_eof_actions[cs];
	int __nacts = (int) _QueryParser_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _QueryParser_actions[__acts++] ) {
	case 11:
// line 58 "parser/com/httpQL/QueryProcessor.rl"
	{
	    				attributeValue = toStringAndClean();
	    				QueryCondition condition = new QueryCondition(attributeName, attributeValue, ConditionType.LIMIT);
		  				attributes.add(condition);
				  }
	break;
	case 13:
// line 65 "parser/com/httpQL/QueryProcessor.rl"
	{
	    			  attributeValue = toStringAndClean();
					  QueryCondition condition = new QueryCondition(attributeName, attributeValue, binaryOperation);
					  attributes.add(condition);
				  }
	break;
	case 15:
// line 84 "parser/com/httpQL/QueryProcessor.rl"
	{site = toStringAndClean();}
	break;
	case 18:
// line 93 "parser/com/httpQL/QueryProcessor.rl"
	{
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
	break;
	case 20:
// line 109 "parser/com/httpQL/QueryProcessor.rl"
	{
				  	logger.info("=================");
				  	logger.info("method is " + method);
				  	logger.info("site is " + site);
				  	logger.info("=================");
				}
	break;
// line 593 "parser/com/httpQL/QueryProcessor.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 158 "parser/com/httpQL/QueryProcessor.rl"
		
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
