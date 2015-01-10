package com.sciencemeetspianos.kuroneko;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public abstract class FormatterSuper extends Formatter
{
	  public abstract StringBuffer formatLog(LogRecord paramLogRecord);

	  public String getDate(LogRecord arg0)
	  {
	    Date date = new Date(arg0.getMillis());
	    return date.toString();
	  }

	  public String getDate(LogRecord arg0, String arg1)
	  {
	    Date date = new Date(arg0.getMillis());
	    String s = new SimpleDateFormat(arg1).format(date);
	    return s;
	  }

	  public String getLogLevel(LogRecord arg0)
	  {
	    return arg0.getLevel().getName();
	  }

	  public String format(LogRecord record)
	  {
	    return formatLog(record).toString();
	  }
	
	
}
