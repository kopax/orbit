package com.inmaytide.orbit.exceptions;

/**
 * The version does not match when the data is modified.
 *
 * @author Moss
 * @since September 3, 2017
 */
public class VersionMatchedException extends RuntimeException {

    private static final long serialVersionUID = -22605139663792344L;

    private Object data;

    public VersionMatchedException(Object data) {
        super();
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
