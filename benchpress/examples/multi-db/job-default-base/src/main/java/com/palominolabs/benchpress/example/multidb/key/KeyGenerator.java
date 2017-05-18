package com.palominolabs.benchpress.example.multidb.key;

import javax.annotation.concurrent.NotThreadSafe;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;
import java.util.UUID;

/**
 * Each thread gets its own KeyGenerator to avoid contention.
 */
@NotThreadSafe
public interface KeyGenerator {

    /**
     * @param buf         byte buf to write into
     * @param workerId    UUID representing the current worker running
     * @param threadId    thread id of the thread requesting a key
     * @param partitionId partition id
     * @param counter     a presumably incrementing counter representing how many keys the thread has needed to
     *                    generate
     * @throws BufferOverflowException if buf overflows while writing the key
     */
    void writeKey(CharBuffer buf, UUID workerId, long threadId, int partitionId,
                  int counter) throws BufferOverflowException;
}
