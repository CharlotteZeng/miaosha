package com.imooc.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix {
    private String prefix;
    private int expireSeconds;

    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds=expireSeconds;
        this.prefix=prefix;
    }
    //默认0代表永不过期
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}
