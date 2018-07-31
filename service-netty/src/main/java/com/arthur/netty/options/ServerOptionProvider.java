
package com.arthur.netty.options;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerOptionProvider {

    private Map<ServerOptions<?>, Object> options = new ConcurrentHashMap<>();

    public <T> ServerOptionProvider option(ServerOptions<T> option, T value) {
        this.options.put(option, value);
        return this;
    }

    /**
     * return specified option name's value if this option is setted. or named option
     * default value.
     *
     * @param option named option already defined
     * @return setted value or default value
     */
    @SuppressWarnings("unchecked")
    public <T> T option(ServerOptions<T> option) {
        T value;
        if ((value = (T) options.get(option)) != null)
            return value;
        else
            return option.defaultValue();
    }
}
