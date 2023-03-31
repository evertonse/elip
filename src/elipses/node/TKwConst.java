/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class TKwConst extends Token
{
    public TKwConst()
    {
        super.setText("const");
    }

    public TKwConst(int line, int pos)
    {
        super.setText("const");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwConst(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwConst(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwConst text.");
    }
}
