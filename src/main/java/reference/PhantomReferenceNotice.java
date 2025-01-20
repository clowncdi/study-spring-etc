package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.time.LocalDateTime;

public class PhantomReferenceNotice {

    public static void main(String[] args) {
        System.out.println("startTime = " + LocalDateTime.now());
        // 큰 메모리를 가진 Object 생성
        LargeObject largeObject = new LargeObject();

        // ReferenceQueue 생성
        ReferenceQueue<LargeObject> referenceQueue = new ReferenceQueue<>();

        // PhantomReference 생성
        PhantomReference<LargeObject> phantomReference = new PhantomReference<>(
            largeObject, referenceQueue);

        // largeObject 참조 해제
        largeObject = null;

        // 가비지 컬렉션 수행
        System.gc();

        // ReferenceQueue에서 PhantomReference를 가져와서 처리
        Reference<? extends LargeObject> referenceFromQueue;
        while ((referenceFromQueue = referenceQueue.poll()) != null) {
            if (referenceFromQueue == phantomReference) {
                // 여기에서 해당 객체의 리소스를 해제하거나, 반납하는 작업을 수행
                System.out.println("LargeObject 객체가 가비지 컬렉션 되었습니다.");
                System.out.println("collectedTime = " + LocalDateTime.now());
            }
        }
    }

    static class LargeObject {
        private final byte[] data = new byte[1024 * 1024 * 100]; // 100MB 크기 배열

    }
}
