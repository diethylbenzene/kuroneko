package com.sciencemeetspianos.kuroneko;

import java.util.logging.LogRecord;

public class LogBasicFormatter extends FormatterSuper{

	  public StringBuffer formatLog(LogRecord arg0)
	  {
	    StringBuffer sb = new StringBuffer();
	    String s = super.getDate(arg0, "MMM d yyyy HH:mm:ss");
	    sb.append("[");
	    sb.append(s);
	    sb.append("] ");
	    sb.append(super.getLogLevel(arg0));
	    sb.append(": ");
	    sb.append(formatMessage(arg0));
	    sb.append("\n");
	    return sb;
	  }
	
}
