package com.beadwallet.beautymirror.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.WindowManager;

import com.beadwallet.beautymirror.di.component.ApplicationComponent;
import com.beadwallet.beautymirror.di.component.base.BaseComponent;
import com.beadwallet.data.BuildConfig;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.DefaultFlattener;
import com.elvishew.xlog.formatter.border.DefaultBorderFormatter;
import com.elvishew.xlog.formatter.message.json.DefaultJsonFormatter;
import com.elvishew.xlog.formatter.message.throwable.DefaultThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.DefaultXmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.DefaultStackTraceFormatter;
import com.elvishew.xlog.formatter.thread.DefaultThreadFormatter;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;

public class GlobleApplication extends Application {
    private final String TAG = this.getClass().getSimpleName();
    private ApplicationComponent mApplicationComponent;
    private BaseComponent mBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initXLog();
    }

    private void initXLog() {
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL
                        // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
                        : LogLevel.NONE)
                .tag("XLog")                                         // Specify TAG, default: "X-LOG"
                .t()                                                   // Enable thread info, disabled by default
                .st(2)                                                 // Enable stack trace info with depth 2, disabled by default
                .b()                                                   // Enable border, disabled by default
                .jsonFormatter(new DefaultJsonFormatter())                  // Default: DefaultJsonFormatter
                .xmlFormatter(new DefaultXmlFormatter())                    // Default: DefaultXmlFormatter
                .throwableFormatter(
                        new DefaultThrowableFormatter())        // Default: DefaultThrowableFormatter
                .threadFormatter(
                        new DefaultThreadFormatter())              // Default: DefaultThreadFormatter
                .stackTraceFormatter(
                        new DefaultStackTraceFormatter())      // Default: DefaultStackTraceFormatter
                .borderFormatter(
                        new DefaultBorderFormatter())               // Default: DefaultBorderFormatter
                .build();

        Printer androidPrinter = new AndroidPrinter();             // Printer that print the log using android.util.Log
        Printer consolePrinter = new ConsolePrinter();             // Printer that print the log to console using System.top_out
        Printer filePrinter = new FilePrinter                      // Printer that print the log to the file system
                .Builder("/sdcard/xlog/")                              // Specify the path to save log file
                .fileNameGenerator(
                        new DateFileNameGenerator())        // Default: ChangelessFileNameGenerator("log")
                .backupStrategy(
                        new NeverBackupStrategy())             // Default: FileSizeBackupStrategy(1024 * 1024)
                .logFlattener(new DefaultFlattener())                       // Default: DefaultFlattener
                .build();

        XLog.init(                                                 // Initialize XLog
                config,
                // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
                androidPrinter,
                // Specify printers, if no printer is specified, AndroidPrinter(for Android)/ConsolePrinter(for java) will be used.
                consolePrinter,
                filePrinter);
    }

    //通过registerActivityLifecycleCallbacks 实现屏幕常亮
    private void registerCallback() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }


}