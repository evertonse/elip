/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class TKwIf extends Token
{
    public TKwIf()
    {
        super.setText("se");
    }

    public TKwIf(int line, int pos)
    {
        super.setText("se");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwIf(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwIf(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwIf text.");
    }
}
