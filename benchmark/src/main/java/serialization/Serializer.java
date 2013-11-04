package serialization;

/*
 * <pre>
 * A super unsafeSerializer should support:
 *   - all kinds of value objects as below
 *   - fields of all visibility
 *   - transient fields should be excluded from the serialized state
 *   - value objects may with any constructors
 *   - members of primitives, wrapped primitives and Strings
 *   - arrays of types above is optional but gives bonus points :-)
 *
 * Does not have to support:
 *   - inheritance for value objects
 *   - final fields
 *   - member objects other than those mentioned above
 * </pre>
 */
public interface Serializer {

    public byte[] serialize(Object obj);

    public Object deserialize(byte[] arr);
}
