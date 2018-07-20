package com.jvm.weakreference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 软弱虚引用都可以使用ReferenceQueue队列来监控对象垃圾回收过程
 * 软引用:SoftReference-当堆空间不足时，就会被回收
 * 弱引用:WeakReference-当发生GC时，不管空间内存状况，都会将对象回收
 * 虚引用:PhantomReference-和没有引用一样，只是用ReferenceQueue来监控垃圾回收过程
 */
/**
 * 虚引用的作用
 */
public class TraceCanReliveObj {
    public static TraceCanReliveObj obj;
    static ReferenceQueue<TraceCanReliveObj> phantomQueue = null;
    public static class CheckRefQueue extends Thread{
        @Override
        public void run(){
            while(true){
                if(phantomQueue != null){
                    PhantomReference<TraceCanReliveObj> objt = null;
                    try {
                        objt = (PhantomReference<TraceCanReliveObj>)phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(objt != null){
                        System.out.println("TraceCanReliveObj is delete by GC");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;
    }

    @Override
    public String toString(){
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();

        phantomQueue = new ReferenceQueue<>();
        obj = new TraceCanReliveObj();

        PhantomReference<TraceCanReliveObj> phantomRef = new PhantomReference<>(obj,phantomQueue);

        obj = null;
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("Obj 是 null");
        }else{
            System.out.println("Obj 可用");
        }
        System.out.println("第2次GC");
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
    }
}
