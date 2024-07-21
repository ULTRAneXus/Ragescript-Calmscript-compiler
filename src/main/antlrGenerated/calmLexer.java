// Generated from calm.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class calmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, NUMBER=2, SEP=3, COMMENT=4, PRINT=5, ARITHMETIC=6, BRANCH=7, BRANCHSPLIT=8, 
		BRANCHEND=9, LOOP=10, LOOPEND=11, NEWLINE=12, WHITESPACE=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VAR", "NUMBER", "SEP", "COMMENT", "PRINT", "ARITHMETIC", "BRANCH", "BRANCHSPLIT", 
			"BRANCHEND", "LOOP", "LOOPEND", "NUM", "TEXT", "NEWLINE", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'code:'", null, null, null, null, "'else'", "'endif'", 
			null, "'endwhile'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VAR", "NUMBER", "SEP", "COMMENT", "PRINT", "ARITHMETIC", "BRANCH", 
			"BRANCHSPLIT", "BRANCHEND", "LOOP", "LOOPEND", "NEWLINE", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public calmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "calm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\r\u0096\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0003\u0001\'\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0004\u00035\b\u0003\u000b\u0003\f\u0003"+
		"6\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"P\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"V\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\\\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tp\b\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\tv\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0004\u000b\u0082\b\u000b\u000b"+
		"\u000b\f\u000b\u0083\u0001\f\u0004\f\u0087\b\f\u000b\f\f\f\u0088\u0001"+
		"\r\u0004\r\u008c\b\r\u000b\r\f\r\u008d\u0001\u000e\u0004\u000e\u0091\b"+
		"\u000e\u000b\u000e\f\u000e\u0092\u0001\u000e\u0001\u000e\u0000\u0000\u000f"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\u0000\u0019\u0000\u001b"+
		"\f\u001d\r\u0001\u0000\u0006\u0002\u0000ccnn\u0003\u0000ggllnn\u0001\u0000"+
		"09\u0007\u0000\t\t !,.09==AZaz\u0002\u0000\n\n\r\r\u0002\u0000\t\t  \u00a1"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0003&\u0001"+
		"\u0000\u0000\u0000\u0005*\u0001\u0000\u0000\u0000\u00070\u0001\u0000\u0000"+
		"\u0000\t8\u0001\u0000\u0000\u0000\u000bO\u0001\u0000\u0000\u0000\rQ\u0001"+
		"\u0000\u0000\u0000\u000f]\u0001\u0000\u0000\u0000\u0011b\u0001\u0000\u0000"+
		"\u0000\u0013h\u0001\u0000\u0000\u0000\u0015w\u0001\u0000\u0000\u0000\u0017"+
		"\u0081\u0001\u0000\u0000\u0000\u0019\u0086\u0001\u0000\u0000\u0000\u001b"+
		"\u008b\u0001\u0000\u0000\u0000\u001d\u0090\u0001\u0000\u0000\u0000\u001f"+
		" \u0005v\u0000\u0000 !\u0005a\u0000\u0000!\"\u0005r\u0000\u0000\"#\u0001"+
		"\u0000\u0000\u0000#$\u0003\u0017\u000b\u0000$\u0002\u0001\u0000\u0000"+
		"\u0000%\'\u0005-\u0000\u0000&%\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0003\u0017\u000b\u0000)\u0004"+
		"\u0001\u0000\u0000\u0000*+\u0005c\u0000\u0000+,\u0005o\u0000\u0000,-\u0005"+
		"d\u0000\u0000-.\u0005e\u0000\u0000./\u0005:\u0000\u0000/\u0006\u0001\u0000"+
		"\u0000\u000001\u0005/\u0000\u000012\u0005/\u0000\u000024\u0001\u0000\u0000"+
		"\u000035\u0003\u0019\f\u000043\u0001\u0000\u0000\u000056\u0001\u0000\u0000"+
		"\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u00007\b\u0001\u0000"+
		"\u0000\u000089\u0005p\u0000\u00009:\u0005r\u0000\u0000:;\u0005i\u0000"+
		"\u0000;<\u0005n\u0000\u0000<=\u0005t\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">?\u0007\u0000\u0000\u0000?\n\u0001\u0000\u0000\u0000@A\u0005a\u0000\u0000"+
		"AB\u0005d\u0000\u0000BP\u0005d\u0000\u0000CD\u0005s\u0000\u0000DE\u0005"+
		"u\u0000\u0000EP\u0005b\u0000\u0000FG\u0005m\u0000\u0000GH\u0005u\u0000"+
		"\u0000HP\u0005l\u0000\u0000IJ\u0005d\u0000\u0000JK\u0005i\u0000\u0000"+
		"KP\u0005v\u0000\u0000LM\u0005m\u0000\u0000MN\u0005o\u0000\u0000NP\u0005"+
		"d\u0000\u0000O@\u0001\u0000\u0000\u0000OC\u0001\u0000\u0000\u0000OF\u0001"+
		"\u0000\u0000\u0000OI\u0001\u0000\u0000\u0000OL\u0001\u0000\u0000\u0000"+
		"P\f\u0001\u0000\u0000\u0000QR\u0005i\u0000\u0000RS\u0005f\u0000\u0000"+
		"SU\u0001\u0000\u0000\u0000TV\u0007\u0001\u0000\u0000UT\u0001\u0000\u0000"+
		"\u0000UV\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WX\u0005e\u0000"+
		"\u0000XY\u0005q\u0000\u0000Y[\u0001\u0000\u0000\u0000Z\\\u00050\u0000"+
		"\u0000[Z\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\\u000e\u0001"+
		"\u0000\u0000\u0000]^\u0005e\u0000\u0000^_\u0005l\u0000\u0000_`\u0005s"+
		"\u0000\u0000`a\u0005e\u0000\u0000a\u0010\u0001\u0000\u0000\u0000bc\u0005"+
		"e\u0000\u0000cd\u0005n\u0000\u0000de\u0005d\u0000\u0000ef\u0005i\u0000"+
		"\u0000fg\u0005f\u0000\u0000g\u0012\u0001\u0000\u0000\u0000hi\u0005w\u0000"+
		"\u0000ij\u0005h\u0000\u0000jk\u0005i\u0000\u0000kl\u0005l\u0000\u0000"+
		"lm\u0005e\u0000\u0000mo\u0001\u0000\u0000\u0000np\u0007\u0001\u0000\u0000"+
		"on\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000"+
		"\u0000qr\u0005e\u0000\u0000rs\u0005q\u0000\u0000su\u0001\u0000\u0000\u0000"+
		"tv\u00050\u0000\u0000ut\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"v\u0014\u0001\u0000\u0000\u0000wx\u0005e\u0000\u0000xy\u0005n\u0000\u0000"+
		"yz\u0005d\u0000\u0000z{\u0005w\u0000\u0000{|\u0005h\u0000\u0000|}\u0005"+
		"i\u0000\u0000}~\u0005l\u0000\u0000~\u007f\u0005e\u0000\u0000\u007f\u0016"+
		"\u0001\u0000\u0000\u0000\u0080\u0082\u0007\u0002\u0000\u0000\u0081\u0080"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0081"+
		"\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0018"+
		"\u0001\u0000\u0000\u0000\u0085\u0087\u0007\u0003\u0000\u0000\u0086\u0085"+
		"\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0086"+
		"\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u001a"+
		"\u0001\u0000\u0000\u0000\u008a\u008c\u0007\u0004\u0000\u0000\u008b\u008a"+
		"\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008b"+
		"\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u001c"+
		"\u0001\u0000\u0000\u0000\u008f\u0091\u0007\u0005\u0000\u0000\u0090\u008f"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0090"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0006\u000e\u0000\u0000\u0095\u001e"+
		"\u0001\u0000\u0000\u0000\f\u0000&6OU[ou\u0083\u0088\u008d\u0092\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}