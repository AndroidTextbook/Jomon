package org.android.textbook.lesson4.handlersample;

public class WorkerThread extends Thread {

    WorkerThreadListener mWorkerThreadListener;
    private boolean mRequestStop;

    public interface WorkerThreadListener {
        void onEventFromWorkerThread();
    }

    public WorkerThread(WorkerThreadListener threadInterface) {
        mWorkerThreadListener = threadInterface;
    }

    @Override
    public void run() {
        while (!mRequestStop) {
            // 1秒間隔でイベントを発生する
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // スレッド起動もとに通知
            mWorkerThreadListener.onEventFromWorkerThread();

        }
    }

    public void stopThread() {
        mRequestStop = true;
        interrupt();
    }
}
