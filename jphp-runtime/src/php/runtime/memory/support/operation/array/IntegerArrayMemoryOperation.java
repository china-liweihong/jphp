package php.runtime.memory.support.operation.array;

import php.runtime.Memory;
import php.runtime.common.HintType;
import php.runtime.env.Environment;
import php.runtime.env.TraceInfo;
import php.runtime.memory.ArrayMemory;
import php.runtime.memory.support.MemoryOperation;
import php.runtime.reflection.ParameterEntity;

public class IntegerArrayMemoryOperation extends MemoryOperation<int[]> {
    @Override
    public Class<?>[] getOperationClasses() {
        return new Class<?>[] { int[].class };
    }

    @Override
    public int[] convert(Environment env, TraceInfo trace, Memory arg) {
        return arg.toValue(ArrayMemory.class).toIntArray();
    }

    @Override
    public Memory unconvert(Environment env, TraceInfo trace, int[] arg) {
        return ArrayMemory.ofIntegers(arg).toConstant();
    }

    @Override
    public void applyTypeHinting(ParameterEntity parameter) {
        parameter.setType(HintType.ARRAY);
    }
}
