/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class TKwThen extends Token
{
    public TKwThen()
    {
        super.setText("entao");
    }

    public TKwThen(int line, int pos)
    {
        super.setText("entao");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwThen(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwThen(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwThen text.");
    }
}
