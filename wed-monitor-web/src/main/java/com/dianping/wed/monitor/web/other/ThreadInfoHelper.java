/**
 * 
 */
package com.dianping.wed.monitor.web.other;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author cong.yu
 * 
 */
public class ThreadInfoHelper {

    private static String lineSeperator=System.getProperty("line.separator");
    /**
     * Print the stack trace and thread info to System.out.
     */

    public static String getThreadDumps() {
       
        final StringBuilder dump = new StringBuilder();

        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        final ThreadInfo[] threadInfos =

        threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), 100);
        dump.append(lineSeperator);

        for (ThreadInfo threadInfo : threadInfos) {
            dump.append('"');
            dump.append(threadInfo.getThreadName());
            dump.append("\" ");
            final Thread.State state = threadInfo.getThreadState();
            dump.append(lineSeperator);
            dump.append("   State: ");
            dump.append(state);
            final StackTraceElement[] stackTraceElements = threadInfo .getStackTrace();
            for (final StackTraceElement stackTraceElement : stackTraceElements) {
                dump.append(lineSeperator);
                dump.append("        at ");
                dump.append(stackTraceElement);
            }
            dump.append(lineSeperator);
            dump.append(lineSeperator);

        }

        return dump.toString();
    }

    public static void main(String args[]) {
        System.out.println(ThreadInfoHelper.getThreadDumps());
    }
}
