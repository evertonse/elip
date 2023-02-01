/* This file was generated by SableCC (http://www.sablecc.org/). */

package Elipses.node;

import Elipses.analysis.*;

@SuppressWarnings("nls")
public final class TFracNumber extends Token
{
    public TFracNumber(String text)
    {
        setText(text);
    }

    public TFracNumber(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TFracNumber(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTFracNumber(this);
    }
}
