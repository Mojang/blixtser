package serialization;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class SampleValue implements Serializable {

    String aString;
    int aInt;
    Long aWrappedLong;
/*
    String[] strings;
*/

    private final static Random rnd = new Random();

    private SampleValue() {
    }

    public static SampleValue createRandom() {
        SampleValue val = new SampleValue();
        val.aString = UUID.randomUUID().toString();
        val.aInt = rnd.nextInt();
        val.aWrappedLong = rnd.nextLong();
/*
        val.strings = new String[2];
        for (int i=0; i<val.strings.length; i++) {
            val.strings[i] = UUID.randomUUID().toString();
        }
*/
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SampleValue other = (SampleValue) obj;
        if (!Objects.equals(this.aString, other.aString)) {
            return false;
        }
        if (this.aInt != other.aInt) {
            return false;
        }
        if (!Objects.equals(this.aWrappedLong, other.aWrappedLong)) {
            return false;
        }
/*
        if (this.strings.length != other.strings.length) {
            return false;
        }
        for (int i=0; i<this.strings.length; i++) {
            if (!this.strings[i].equals(other.strings[i])) {
                return false;
            }
        }
*/
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.aInt;
        return hash;
    }

}
