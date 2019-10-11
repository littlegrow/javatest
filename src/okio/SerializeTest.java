package okio;

import java.io.Serializable;

public class SerializeTest {
    static class TestObj implements Serializable {
        private int a;
        private int b;

        TestObj(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TestObj)) {
                return false;
            }
            TestObj testObj = (TestObj) obj;
            return testObj.a == a && testObj.b == b;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + a;
            return 31 * result + b;
        }

        @Override
        public String toString() {
            return String.format("[TestObj: a=%s, b=%s]", this.a, this.b);
        }
    }

    public static void main(String[] args) {
        TestObj testObj = new TestObj(1, 2);
        System.out.println("origin obj: " + testObj);
        System.out.println("serialize string: " + OkioSerializeUtils.serializeString(testObj));
        System.out.println("serialize hexString: " + OkioSerializeUtils.serializeHexString(testObj));
        System.out.println("serialize base64: " + OkioSerializeUtils.serializeBase64(testObj));
        System.out.println("deserialize string: " + OkioSerializeUtils.deserializeHexString("aced00057372001a6f6b696f2e53657269616c697a655465737424546573744f626a8719308efba7bd66020002490001614900016278700000000100000002"));
        System.out.println("deserialize hexString: " + OkioSerializeUtils.deserializeHexString("aced00057372001a6f6b696f2e53657269616c697a655465737424546573744f626a8719308efba7bd66020002490001614900016278700000000100000002"));
        System.out.println("deserialize base64: " + OkioSerializeUtils.deserializeBase64("rO0ABXNyABpva2lvLlNlcmlhbGl6ZVRlc3QkVGVzdE9iaocZMI77p71mAgACSQABYUkAAWJ4cAAAAAEAAAAC"));
    }
}
