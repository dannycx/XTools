// BookManager.aidl
package com.danny.xtools.ipc.aidl;

import com.danny.xtools.ipc.aidl.Life;

interface LifeManager {
    List<Life> getLifes();

    void lifeIn(in Life life);
//    void lifeOut(out Life life);
//    void lifeInout(inout Life life);
}