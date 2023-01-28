/* This file was generated by SableCC (http://www.sablecc.org/). */

package elip.node;

import elip.analysis.*;

@SuppressWarnings("nls")
public final class TBinFracNumber extends Token
{
    public TBinFracNumber(String text)
    {
        setText(text);
    }

    public TBinFracNumber(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TBinFracNumber(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTBinFracNumber(this);
    }
}
