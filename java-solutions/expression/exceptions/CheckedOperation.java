package expression.exceptions;

public class CheckedOperation {

    public static int abs(int l, String oper) {
        if (l >= 0) {
            return l;
        } else {
            return neg(l, oper);
        }
    }

    public static int add(int l, int r, String oper) {
        if (l >= 0 && r >= 0) {
            if (Integer.MAX_VALUE - l >= r) {
                return l + r;
            } else {
                throw new OverflowException(l + " + " + r);
            }
        } else if (l >= 0) {
            return l + r;
        } else if (r >= 0) {
            return l + r;
        } else {
            if (Integer.MIN_VALUE - l <= r) {
                return l + r;
            } else {
                throw new OverflowException(l + " " + oper + " " + r);
            }
        }
    }

    public static int div(int l, int r, String oper) {
        if (r == 0) {
            throw new DivNullExeption();
        } else if (l == Integer.MIN_VALUE && r == -1) {
            throw new OverflowException(l + " " + oper + " " + r);
        } else {
            return l / r;
        }
    }

    public static int gcd(int l, int r, String oper) {
        if (l == r && l == Integer.MIN_VALUE) {
            throw new OverflowException(l + " " + oper + " " + r);
        } else if ((r == 0 && l == Integer.MIN_VALUE) ||
                (l == 0 && r == Integer.MIN_VALUE)) {
            throw new OverflowException(l + " " + oper + " " + r);
        }
        while (r != 0) {
            int t = l % r;
            l = r;
            r = t;
        }
        return abs(l, oper);
    }

    public static int mul(int l, int r, String oper) {
        if (l == 0 || r == 0) {
            return 0;
        } else if (l > 0 && r > 0) {
            if (Integer.MAX_VALUE / l >= r) {
                return l * r;
            } else {
                throw new OverflowException(l + " " + oper + " " + r);
            }
        } else if (l < 0 && r < 0) {
            if (Integer.MAX_VALUE / l <= r) {
                return l * r;
            } else {
                throw new OverflowException(l + " " + oper + " " + r);
            }
        } else if (l > 0) {
            if (Integer.MIN_VALUE / l <= r) {
                return l * r;
            } else {
                throw new OverflowException(l + " " + oper + " " + r);
            }
        } else {
            if (Integer.MIN_VALUE / r <= l) {
                return l * r;
            } else {
                throw new OverflowException(l + " " + oper + " " + r);
            }
        }
    }

    public static int sub(int l, int r, String oper) {
        if (l >= 0 && r <= 0) {
            if (Integer.MAX_VALUE + r >= l) {
                return l - r;
            } else {
                throw new OverflowException(l + " - " + r);
            }
        } else if (l >= 0) {
            return l - r;
        } else if (r <= 0) {
            return l - r;
        } else {
            if (Integer.MIN_VALUE + r <= l) {
                return l - r;
            } else {
                throw new OverflowException(l + " " + oper + " " + r);
            }
        }
    }

    public static int neg(int l, String oper) {
        if (l != Integer.MIN_VALUE) {
            return -l;
        } else {
            throw new OverflowException(oper + " " + l);
        }
    }
}
