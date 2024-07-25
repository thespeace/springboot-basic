package memory;

/**
 * <ul>
 *     <li>used : 사용중인 메모리</li>
 *     <li>max : 최대 메모리</li>
 *     <li>쉽게 이야기해서 used 가 max 를 넘게 되면 메모리 부족 오류가 발생한다.</li>
 * </ul>
 */
public class Memory {
    private long used;
    private long max;

    public Memory(long used, long max) {
        this.used = used;
        this.max = max;
    }

    public long getUsed() {
        return used;
    }

    public long getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "used=" + used +
                ", max=" + max +
                '}';
    }
}
