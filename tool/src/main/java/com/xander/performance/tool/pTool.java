package com.xander.performance.tool;

import android.util.Log;
import com.swift.sandhook.SandHookConfig;

public class pTool {

  public static void startPerformance() {
    //first set debuggable
    SandHookConfig.DEBUG = BuildConfig.DEBUG;
    startCheckThread();
    startCheckANR();
  }

  private static void startCheckANR() {
    ANRTool.start();
  }

  private static void startCheckThread() {
    ThreadTool.init();
  }


  static String STACK_LOG_FORMAT = "\t%s.%s():%s";

  /**
   * 打印指定线程的方法调用栈
   */
  static void printThreadStackTrace(String tag, Thread thread) {
    printThreadStackTrace(tag, thread, true);
  }

  static void printThreadStackTrace(String tag, Thread thread, boolean allTrace) {
    if (null == thread) {
      return;
    }
    StackTraceElement[] stacks = thread.getStackTrace();
    // 没有执行完，说明 ui 线程阻塞了，打印方法堆栈
    for (int i = 0; i < stacks.length; i++) {
      Log.e(
          tag,
          String.format(
              STACK_LOG_FORMAT,
              stacks[i].getClassName(),
              stacks[i].getMethodName(),
              stacks[i].getLineNumber()
          )
      );
    }
  }

}
