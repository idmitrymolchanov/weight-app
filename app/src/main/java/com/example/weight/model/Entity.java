package com.example.weight.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Objects;

@androidx.room.Entity
public class Entity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "current_weight")
    public double current_weight;
    @ColumnInfo(name = "current_date")
    public String current_date;

    @Override
    public String toString() {
        return "" + current_weight+ "";
    }

    public Entity() {
    }

    protected Entity(Parcel in) {
        uid = in.readInt();
        current_weight = in.readDouble();
        current_date = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return uid == entity.uid && Double.compare(entity.current_weight, current_weight) == 0 && Objects.equals(current_date, entity.current_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, current_weight, current_date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeDouble(current_weight);
        parcel.writeSerializable(current_date);
    }

    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        @Override
        public Entity createFromParcel(Parcel parcel) {
            return new Entity(parcel);
        }

        @Override
        public Entity[] newArray(int i) {
            return new Entity[i];
        }
    };
}
