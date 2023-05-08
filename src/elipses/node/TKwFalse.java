/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class TKwFalse extends Token
{
    public TKwFalse()
    {
        super.setText("falso");
    }

    public TKwFalse(int line, int pos)
    {
        super.setText("falso");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwFalse(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwFalse(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwFalse text.");
    }
}
