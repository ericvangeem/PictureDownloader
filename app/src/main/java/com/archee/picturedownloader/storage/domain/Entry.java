package com.archee.picturedownloader.storage.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.archee.picturedownloader.utils.DateUtils;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * A parcel object to represent a single entry.
 */
public class Entry implements Parcelable {

    private String url;
    private Date date;

    public Entry(String url, Date date) {
        this.url = url;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.url,
                DateUtils.format(this.date)
        });
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Entry)) return false;

        return  Objects.equal(this.getUrl(), ((Entry) obj).getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.url);
    }

    public static final Parcelable.Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel source) {
            String[] data = new String[2];
            source.readStringArray(data);

            String url = data[0];
            Date date = DateUtils.parse(data[1]);

            return new Entry(url, date);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
}
