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
            // 1�b�Ԋu�ŃC�x���g�𔭐�����
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // �X���b�h�N�����Ƃɒʒm
            mWorkerThreadListener.onEventFromWorkerThread();

        }
    }

    public void stopThread() {
        mRequestStop = true;
        interrupt();
    }
}
