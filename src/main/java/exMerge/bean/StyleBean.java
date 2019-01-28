package exMerge;

class StyleBean {
    private short f; // 前景色
    private short b; // 后景色

    static boolean isDefaultStyle(short b, short f) {
        return b == 64 && f == 64;
    }

    StyleBean(short b, short f) {
        this.b = b;
        this.f = f;
    }

    public short getF() {
        return f;
    }

    public void setF(short f) {
        this.f = f;
    }

    public short getB() {
        return b;
    }

    public void setB(short b) {
        this.b = b;
    }
}
