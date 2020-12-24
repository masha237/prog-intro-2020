package expression.exceptions;

public class CheckedOperation {

    private static OverflowException makeException(int l, int r, String oper) {
        return new OverflowException(l + " " + oper + " " + r);
    }

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
                throw makeException(l, r, oper);
            }
        } else if (l >= 0) {
            return l + r;
        } else if (r >= 0) {
            return l + r;
        } else {
            if (Integer.MIN_VALUE - l <= r) {
                return l + r;
            } else {
                throw makeException(l, r, oper);
            }
        }
    }

    public static int div(int l, int r, String oper) {
        if (r == 0) {
            throw new DivNullExeption(l + " " + oper + " " + r);
        } else if (l == Integer.MIN_VALUE && r == -1) {
            throw makeException(l, r, oper);
        } else {
            return l / r;
        }
    }

    public static int gcd(int l, int r, String oper) {
        l = abs(l, oper);
        r = abs(r, oper);
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
                throw makeException(l, r, oper);
            }
        } else if (l < 0 && r < 0) {
            if (Integer.MAX_VALUE / l <= r) {
                return l * r;
            } else {
                throw makeException(l, r, oper);
            }
        } else if (l > 0) {
            if (Integer.MIN_VALUE / l <= r) {
                return l * r;
            } else {
                throw makeException(l, r, oper);
            }
        } else {
            if (Integer.MIN_VALUE / r <= l) {
                return l * r;
            } else {
                throw makeException(l, r, oper);
            }
        }
    }

    public static int sub(int l, int r, String oper) {
        if (l >= 0 && r <= 0) {
            if (Integer.MAX_VALUE + r >= l) {
                return l - r;
            } else {
                throw makeException(l, r, oper);
            }
        } else if (l >= 0) {
            return l - r;
        } else if (r <= 0) {
            return l - r;
        } else {
            if (Integer.MIN_VALUE + r <= l) {
                return l - r;
            } else {
                throw makeException(l, r, oper);
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

    public static int lcm(int l, int r, String oper) {

        int gcd = CheckedOperation.gcd(l, r, oper);
        if (gcd == 0) {
            return 0;
        } else {
            return CheckedOperation.mul(CheckedOperation.div(l, gcd, oper), r, oper);
        }
    }
}
