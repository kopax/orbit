package com.inmaytide.orbit.utils;


public class IdGenerator {

    private final SnowflakeIdWorker idWorker;

    private IdGenerator() {
        idWorker = new SnowflakeIdWorker(1, 1);
    }

    public Long nextId() {
        return idWorker.nextId();
    }

    public static IdGenerator getInstance() {
        return IdGeneratorHolder.INSTANCE;
    }

    private static final class IdGeneratorHolder {
        private static final IdGenerator INSTANCE = new IdGenerator();
    }

}
