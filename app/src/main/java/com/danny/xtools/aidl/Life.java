package com.danny.xtools.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * AIDL parcelable实现类
 *
 * @author danny
 * @since 2020-11-26
 */
public class Life implements Parcelable {
    private String name;

    public Life() {}

    protected Life(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Life> CREATOR = new Creator<Life>() {
        @Override
        public Life createFromParcel(Parcel in) {
            return new Life(in);
        }

        @Override
        public Life[] newArray(int size) {
            return new Life[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {// in
        dest.writeString(name);
    }

    public void readToParcel(Parcel parcel) {// out
        name = parcel.readString();
    }
}
