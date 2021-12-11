package com.yosep.spring.sequence;

import com.yosep.spring.sequence.Sequence;

public interface SequenceDao {
    Sequence getSequence(String sequenceId);
    int getNextValue(String sequenceId);
}
