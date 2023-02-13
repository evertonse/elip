/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.lexer;

import java.io.*;
import elipses.node.*;

@SuppressWarnings("nls")
public class Lexer
{
    protected Token token;
    protected State state = State.INITIAL;

    private IPushbackReader in;
    private int line;
    private int pos;
    private boolean cr;
    private boolean eof;
    private final StringBuffer text = new StringBuffer();

    @SuppressWarnings("unused")
    protected void filter() throws LexerException, IOException
    {
        // Do nothing
    }

    public Lexer(@SuppressWarnings("hiding") final PushbackReader in)
    {
        this.in = new IPushbackReader() {

            private PushbackReader pushbackReader = in;
            
            @Override
            public void unread(int c) throws IOException {
                pushbackReader.unread(c);
            }
            
            @Override
            public int read() throws IOException {
                return pushbackReader.read();
            }
        };
    }
 
    public Lexer(@SuppressWarnings("hiding") IPushbackReader in)
    {
        this.in = in;
    }
 
    public Token peek() throws LexerException, IOException
    {
        while(this.token == null)
        {
            this.token = getToken();
            filter();
        }

        return this.token;
    }

    public Token next() throws LexerException, IOException
    {
        while(this.token == null)
        {
            this.token = getToken();
            filter();
        }

        Token result = this.token;
        this.token = null;
        return result;
    }

    protected Token getToken() throws IOException, LexerException
    {
        int dfa_state = 0;

        int start_pos = this.pos;
        int start_line = this.line;

        int accept_state = -1;
        int accept_token = -1;
        int accept_length = -1;
        int accept_pos = -1;
        int accept_line = -1;

        @SuppressWarnings("hiding") int[][][] gotoTable = Lexer.gotoTable[this.state.id()];
        @SuppressWarnings("hiding") int[] accept = Lexer.accept[this.state.id()];
        this.text.setLength(0);

        while(true)
        {
            int c = getChar();

            if(c != -1)
            {
                switch(c)
                {
                case 10:
                    if(this.cr)
                    {
                        this.cr = false;
                    }
                    else
                    {
                        this.line++;
                        this.pos = 0;
                    }
                    break;
                case 13:
                    this.line++;
                    this.pos = 0;
                    this.cr = true;
                    break;
                default:
                    this.pos++;
                    this.cr = false;
                    break;
                }

                this.text.append((char) c);

                do
                {
                    int oldState = (dfa_state < -1) ? (-2 -dfa_state) : dfa_state;

                    dfa_state = -1;

                    int[][] tmp1 =  gotoTable[oldState];
                    int low = 0;
                    int high = tmp1.length - 1;

                    while(low <= high)
                    {
                        // int middle = (low + high) / 2;
                        int middle = (low + high) >>> 1;
                        int[] tmp2 = tmp1[middle];

                        if(c < tmp2[0])
                        {
                            high = middle - 1;
                        }
                        else if(c > tmp2[1])
                        {
                            low = middle + 1;
                        }
                        else
                        {
                            dfa_state = tmp2[2];
                            break;
                        }
                    }
                }while(dfa_state < -1);
            }
            else
            {
                dfa_state = -1;
            }

            if(dfa_state >= 0)
            {
                if(accept[dfa_state] != -1)
                {
                    accept_state = dfa_state;
                    accept_token = accept[dfa_state];
                    accept_length = this.text.length();
                    accept_pos = this.pos;
                    accept_line = this.line;
                }
            }
            else
            {
                if(accept_state != -1)
                {
                    switch(accept_token)
                    {
                    case 0:
                        {
                            @SuppressWarnings("hiding") Token token = new0(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 1:
                        {
                            @SuppressWarnings("hiding") Token token = new1(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 2:
                        {
                            @SuppressWarnings("hiding") Token token = new2(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 3:
                        {
                            @SuppressWarnings("hiding") Token token = new3(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 4:
                        {
                            @SuppressWarnings("hiding") Token token = new4(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 5:
                        {
                            @SuppressWarnings("hiding") Token token = new5(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 6:
                        {
                            @SuppressWarnings("hiding") Token token = new6(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 7:
                        {
                            @SuppressWarnings("hiding") Token token = new7(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 8:
                        {
                            @SuppressWarnings("hiding") Token token = new8(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 9:
                        {
                            @SuppressWarnings("hiding") Token token = new9(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 10:
                        {
                            @SuppressWarnings("hiding") Token token = new10(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 11:
                        {
                            @SuppressWarnings("hiding") Token token = new11(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 12:
                        {
                            @SuppressWarnings("hiding") Token token = new12(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 13:
                        {
                            @SuppressWarnings("hiding") Token token = new13(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 14:
                        {
                            @SuppressWarnings("hiding") Token token = new14(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 15:
                        {
                            @SuppressWarnings("hiding") Token token = new15(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 16:
                        {
                            @SuppressWarnings("hiding") Token token = new16(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 17:
                        {
                            @SuppressWarnings("hiding") Token token = new17(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 18:
                        {
                            @SuppressWarnings("hiding") Token token = new18(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 19:
                        {
                            @SuppressWarnings("hiding") Token token = new19(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 20:
                        {
                            @SuppressWarnings("hiding") Token token = new20(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 21:
                        {
                            @SuppressWarnings("hiding") Token token = new21(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 22:
                        {
                            @SuppressWarnings("hiding") Token token = new22(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 23:
                        {
                            @SuppressWarnings("hiding") Token token = new23(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 24:
                        {
                            @SuppressWarnings("hiding") Token token = new24(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 25:
                        {
                            @SuppressWarnings("hiding") Token token = new25(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 26:
                        {
                            @SuppressWarnings("hiding") Token token = new26(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 27:
                        {
                            @SuppressWarnings("hiding") Token token = new27(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 28:
                        {
                            @SuppressWarnings("hiding") Token token = new28(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 29:
                        {
                            @SuppressWarnings("hiding") Token token = new29(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 30:
                        {
                            @SuppressWarnings("hiding") Token token = new30(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 31:
                        {
                            @SuppressWarnings("hiding") Token token = new31(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 32:
                        {
                            @SuppressWarnings("hiding") Token token = new32(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    }
                }
                else
                {
                    if(this.text.length() > 0)
                    {
                        throw new LexerException(
                            new InvalidToken(this.text.substring(0, 1), start_line + 1, start_pos + 1),
                            "[" + (start_line + 1) + "," + (start_pos + 1) + "]" +
                            " Unknown token: " + this.text);
                    }

                    @SuppressWarnings("hiding") EOF token = new EOF(
                        start_line + 1,
                        start_pos + 1);
                    return token;
                }
            }
        }
    }

    Token new0(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TBlank(text, line, pos); }
    Token new1(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TComment(text, line, pos); }
    Token new2(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwInterger(line, pos); }
    Token new3(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwReal(line, pos); }
    Token new4(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwBool(line, pos); }
    Token new5(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwTrue(line, pos); }
    Token new6(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwFalse(line, pos); }
    Token new7(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwEntry(line, pos); }
    Token new8(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwIf(line, pos); }
    Token new9(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwThen(line, pos); }
    Token new10(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwElse(line, pos); }
    Token new11(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwAnd(line, pos); }
    Token new12(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwNot(line, pos); }
    Token new13(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwOr(line, pos); }
    Token new14(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKwLambda(line, pos); }
    Token new15(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TPlus(line, pos); }
    Token new16(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TMinus(line, pos); }
    Token new17(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TMult(line, pos); }
    Token new18(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TDiv(line, pos); }
    Token new19(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TMod(line, pos); }
    Token new20(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TLParen(line, pos); }
    Token new21(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TRParen(line, pos); }
    Token new22(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TLBrack(line, pos); }
    Token new23(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TRBrack(line, pos); }
    Token new24(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TPipe(line, pos); }
    Token new25(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TColon(line, pos); }
    Token new26(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TEq(line, pos); }
    Token new27(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TLt(line, pos); }
    Token new28(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TGt(line, pos); }
    Token new29(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TIdentifier(text, line, pos); }
    Token new30(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TNumber(text, line, pos); }
    Token new31(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TFracNumber(text, line, pos); }
    Token new32(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TBinNumber(text, line, pos); }

    private int getChar() throws IOException
    {
        if(this.eof)
        {
            return -1;
        }

        int result = this.in.read();

        if(result == -1)
        {
            this.eof = true;
        }

        return result;
    }

    private void pushBack(int acceptLength) throws IOException
    {
        int length = this.text.length();
        for(int i = length - 1; i >= acceptLength; i--)
        {
            this.eof = false;

            this.in.unread(this.text.charAt(i));
        }
    }

    protected void unread(@SuppressWarnings("hiding") Token token) throws IOException
    {
        @SuppressWarnings("hiding") String text = token.getText();
        int length = text.length();

        for(int i = length - 1; i >= 0; i--)
        {
            this.eof = false;

            this.in.unread(text.charAt(i));
        }

        this.pos = token.getPos() - 1;
        this.line = token.getLine() - 1;
    }

    private String getText(int acceptLength)
    {
        StringBuffer s = new StringBuffer(acceptLength);
        for(int i = 0; i < acceptLength; i++)
        {
            s.append(this.text.charAt(i));
        }

        return s.toString();
    }

    private static int[][][][] gotoTable;
/*  {
        { // INITIAL
            {{9, 9, 1}, {10, 10, 2}, {13, 13, 3}, {32, 32, 4}, {35, 35, 5}, {37, 37, 6}, {40, 40, 7}, {41, 41, 8}, {42, 42, 9}, {43, 43, 10}, {45, 45, 11}, {47, 47, 12}, {48, 48, 13}, {49, 57, 14}, {58, 58, 15}, {60, 60, 16}, {61, 61, 17}, {62, 62, 18}, {65, 90, 19}, {91, 91, 20}, {93, 93, 21}, {95, 95, 19}, {97, 97, 19}, {98, 98, 22}, {99, 100, 19}, {101, 101, 23}, {102, 102, 24}, {103, 104, 19}, {105, 105, 25}, {106, 107, 19}, {108, 108, 26}, {109, 109, 19}, {110, 110, 27}, {111, 111, 28}, {112, 113, 19}, {114, 114, 29}, {115, 115, 30}, {116, 117, 19}, {118, 118, 31}, {119, 122, 19}, {123, 123, 32}, {124, 124, 33}, },
            {{9, 32, -2}, },
            {{9, 32, -2}, },
            {{9, 32, -2}, },
            {{9, 32, -2}, },
            {{0, 9, 34}, {10, 10, 35}, {11, 12, 34}, {13, 13, 36}, {14, 65535, 34}, },
            {},
            {},
            {},
            {},
            {},
            {},
            {},
            {{44, 44, 37}, {48, 57, 14}, {98, 98, 38}, },
            {{44, 57, -15}, },
            {},
            {},
            {},
            {},
            {{65, 90, 19}, {95, 95, 19}, {97, 122, 19}, },
            {},
            {},
            {{65, 95, -21}, {97, 110, 19}, {111, 111, 39}, {112, 122, 19}, },
            {{65, 95, -21}, {97, 109, 19}, {110, 110, 40}, {111, 122, 19}, },
            {{65, 95, -21}, {97, 97, 41}, {98, 122, 19}, },
            {{65, 109, -25}, {110, 110, 42}, {111, 122, 19}, },
            {{65, 95, -21}, {97, 97, 43}, {98, 122, 19}, },
            {{65, 95, -21}, {97, 97, 44}, {98, 122, 19}, },
            {{65, 95, -21}, {97, 116, 19}, {117, 117, 45}, {118, 122, 19}, },
            {{65, 95, -21}, {97, 100, 19}, {101, 101, 46}, {102, 122, 19}, },
            {{65, 100, -31}, {101, 101, 47}, {102, 122, 19}, },
            {{65, 100, -31}, {101, 101, 48}, {102, 122, 19}, },
            {{45, 45, 49}, },
            {},
            {{0, 65535, -7}, },
            {},
            {{10, 10, 50}, },
            {{48, 57, 51}, },
            {{48, 48, 52}, {49, 49, 53}, },
            {{65, 110, -24}, {111, 111, 54}, {112, 122, 19}, },
            {{65, 95, -21}, {97, 115, 19}, {116, 116, 55}, {117, 122, 19}, },
            {{65, 95, -21}, {97, 107, 19}, {108, 108, 56}, {109, 122, 19}, },
            {{65, 115, -42}, {116, 116, 57}, {117, 122, 19}, },
            {{65, 95, -21}, {97, 108, 19}, {109, 109, 58}, {110, 122, 19}, },
            {{65, 110, -24}, {111, 111, 59}, {112, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 95, -21}, {97, 97, 60}, {98, 122, 19}, },
            {{65, 109, -25}, {110, 110, 61}, {111, 122, 19}, },
            {{65, 95, -21}, {97, 113, 19}, {114, 114, 62}, {115, 122, 19}, },
            {{45, 45, 63}, },
            {},
            {{48, 57, 51}, },
            {{48, 49, -40}, },
            {{48, 49, -40}, },
            {{65, 107, -43}, {108, 108, 64}, {109, 122, 19}, },
            {{65, 95, -21}, {97, 97, 65}, {98, 113, 19}, {114, 114, 66}, {115, 122, 19}, },
            {{65, 95, -21}, {97, 114, 19}, {115, 115, 67}, {116, 122, 19}, },
            {{65, 100, -31}, {101, 101, 68}, {102, 122, 19}, },
            {{65, 95, -21}, {97, 97, 19}, {98, 98, 69}, {99, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 107, -43}, {108, 108, 70}, {109, 122, 19}, },
            {{65, 95, -21}, {97, 97, 71}, {98, 122, 19}, },
            {{65, 95, -21}, {97, 99, 19}, {100, 100, 72}, {101, 122, 19}, },
            {{0, 44, 73}, {45, 45, 74}, {46, 65535, 73}, },
            {{65, 100, -31}, {101, 101, 75}, {102, 122, 19}, },
            {{65, 110, -24}, {111, 111, 76}, {112, 122, 19}, },
            {{65, 95, -21}, {97, 97, 77}, {98, 122, 19}, },
            {{65, 100, -31}, {101, 101, 78}, {102, 122, 19}, },
            {{65, 95, -21}, {97, 104, 19}, {105, 105, 79}, {106, 122, 19}, },
            {{65, 99, -64}, {100, 100, 80}, {101, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 110, -24}, {111, 111, 81}, {112, 122, 19}, },
            {{65, 95, -21}, {97, 97, 82}, {98, 122, 19}, },
            {{0, 65535, -65}, },
            {{0, 44, 83}, {45, 45, 84}, {46, 124, 83}, {126, 65535, 83}, },
            {{65, 95, -21}, {97, 97, 85}, {98, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 99, -64}, {100, 100, 86}, {101, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 113, -50}, {114, 114, 87}, {115, 122, 19}, },
            {{65, 95, -21}, {97, 97, 88}, {98, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 99, -64}, {100, 100, 89}, {101, 122, 19}, },
            {{0, 44, 90}, {45, 45, 91}, {46, 65535, 90}, },
            {{0, 124, -76}, {125, 125, 92}, {126, 65535, 83}, },
            {{65, 109, -25}, {110, 110, 93}, {111, 122, 19}, },
            {{65, 95, -21}, {97, 97, 94}, {98, 122, 19}, },
            {{65, 110, -24}, {111, 111, 95}, {112, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 100, -31}, {101, 101, 96}, {102, 122, 19}, },
            {{0, 65535, -85}, },
            {{0, 44, 83}, {45, 45, 97}, {46, 65535, -76}, },
            {},
            {{65, 110, -24}, {111, 111, 98}, {112, 122, 19}, },
            {{65, 122, -21}, },
            {{65, 122, -21}, },
            {{65, 104, -70}, {105, 105, 99}, {106, 122, 19}, },
            {{0, 124, -93}, {125, 65535, -86}, },
            {{65, 122, -21}, },
            {{65, 113, -50}, {114, 114, 100}, {115, 122, 19}, },
            {{65, 110, -24}, {111, 111, 101}, {112, 122, 19}, },
            {{65, 122, -21}, },
        }
    };*/

    private static int[][] accept;
/*  {
        // INITIAL
        {-1, 0, 0, 0, 0, 1, 19, 20, 21, 17, 15, 16, 18, 30, 30, 25, 27, 26, 28, 29, 22, 23, 29, 11, 29, 29, 29, 29, 29, 29, 29, 29, -1, 24, 1, 1, 1, -1, -1, 29, 29, 29, 29, 29, 29, 13, 29, 8, 29, -1, 1, 31, 32, 32, 29, 29, 29, 29, 29, 12, 29, 29, 29, -1, 29, 29, 29, 29, 29, 29, 3, 29, 29, -1, -1, 29, 9, 29, 6, 29, 29, 10, 29, -1, -1, 29, 29, 29, 14, 29, -1, -1, 1, 29, 7, 2, 29, -1, 4, 29, 29, 5, },

    };*/

    public static class State
    {
        public final static State INITIAL = new State(0);

        private int id;

        private State(@SuppressWarnings("hiding") int id)
        {
            this.id = id;
        }

        public int id()
        {
            return this.id;
        }
    }

    static 
    {
        try
        {
            DataInputStream s = new DataInputStream(
                new BufferedInputStream(
                Lexer.class.getResourceAsStream("lexer.dat")));

            // read gotoTable
            int length = s.readInt();
            gotoTable = new int[length][][][];
            for(int i = 0; i < gotoTable.length; i++)
            {
                length = s.readInt();
                gotoTable[i] = new int[length][][];
                for(int j = 0; j < gotoTable[i].length; j++)
                {
                    length = s.readInt();
                    gotoTable[i][j] = new int[length][3];
                    for(int k = 0; k < gotoTable[i][j].length; k++)
                    {
                        for(int l = 0; l < 3; l++)
                        {
                            gotoTable[i][j][k][l] = s.readInt();
                        }
                    }
                }
            }

            // read accept
            length = s.readInt();
            accept = new int[length][];
            for(int i = 0; i < accept.length; i++)
            {
                length = s.readInt();
                accept[i] = new int[length];
                for(int j = 0; j < accept[i].length; j++)
                {
                    accept[i][j] = s.readInt();
                }
            }

            s.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException("The file \"lexer.dat\" is either missing or corrupted.");
        }
    }
}
