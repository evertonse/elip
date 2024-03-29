/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class TKwEntry extends Token
{
    public TKwEntry()
    {
        super.setText("entrada");
    }

    public TKwEntry(int line, int pos)
    {
        super.setText("entrada");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwEntry(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwEntry(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwEntry text.");
    }
}
