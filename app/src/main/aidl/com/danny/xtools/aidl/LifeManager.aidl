// BookManager.aidl
package com.danny.xtools.aidl;

import com.danny.xtools.aidl.Life;

interface LifeManager {
    List<Life> getLifes();

    void lifeIn(in Life life);
//    void lifeOut(out Life life);
//    void lifeInout(inout Life life);
}