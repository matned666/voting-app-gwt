package com.herokuapp.mrndesign.matned.client.model.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModelExecutor {

    private volatile static ModelExecutor modelInstance;
    private volatile static ModelExecutor viewInstance;

    public static void runInModel(Runnable task) {
        if (modelInstance == null) {
            synchronized (ModelExecutor.class) {
                if (modelInstance == null) {
                    modelInstance = new ModelExecutor();
                }
            }
        }
        modelInstance.executor.submit(task);
    }

    public static void runInView(Runnable task) {
        if (viewInstance == null) {
            synchronized (ModelExecutor.class) {
                if (viewInstance == null) {
                    viewInstance = new ModelExecutor();
                }
            }
        }
        viewInstance.executor.submit(task);
    }

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private ModelExecutor() {
    }

}
