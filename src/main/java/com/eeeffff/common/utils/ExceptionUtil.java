package com.eeeffff.common.utils;

/**
 * @author simon.cheng
 * 2013-07-29
 * ExceptionUtil
 * */
public class ExceptionUtil {
	
	/**
	 * get exception detail information
	 * */
	public static String getExceptionTrace(Exception e){
		
		StackTraceElement[] strackTraceElementArray=e.getStackTrace();
		StringBuffer sb=new StringBuffer(255);
		sb.append(e.getClass().toString());
		sb.append("\n");
		int round=10;
		for(StackTraceElement stackTraceElement:strackTraceElementArray){
			sb.append("at File:");
			sb.append(stackTraceElement.getFileName()+"|");
			sb.append(stackTraceElement.getClassName());
			sb.append(".");
			sb.append(stackTraceElement.getMethodName());
			sb.append("(");
			sb.append("Line:");
			sb.append(stackTraceElement.getLineNumber());
			sb.append(")");
			sb.append("\n");
			if(--round==0){
				break;
			}
			
		}
		return sb.toString();
	}
	
	/**
	 * get exception detail information
	 * */
	public static String getThrowableTrace(Throwable e){
		
		StackTraceElement[] strackTraceElementArray=e.getStackTrace();
		StringBuffer sb=new StringBuffer(255);
		sb.append(e.getClass().toString());
		sb.append("\n");
		for(StackTraceElement stackTraceElement:strackTraceElementArray){
			sb.append("at File:");
			sb.append(stackTraceElement.getFileName()+"|");
			sb.append(stackTraceElement.getClassName());
			sb.append(".");
			sb.append(stackTraceElement.getMethodName());
			sb.append("(");
			sb.append("Line:");
			sb.append(stackTraceElement.getLineNumber());
			sb.append(")");
			sb.append("\n");
		}
		return sb.toString();
	}
}
