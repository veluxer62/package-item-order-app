package net.class101.server1;

import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;

import java.util.Arrays;

public class StorageRecoveryHelper {
    public static void recovery() {
        Storage.packageItems = Arrays.asList(
                new PackageItemEntity(16374, "클래스", "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950, 99999),
                new PackageItemEntity(26825, "클래스", "해금, 특별하고 아름다운 나만의 반려악기", 114500, 99999),
                new PackageItemEntity(65625, "클래스", "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패 드 드로잉", 174500, 99999),
                new PackageItemEntity(91008, "키트", "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10),
                new PackageItemEntity(9235, "키트", "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22),
                new PackageItemEntity(55527, "클래스", "코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실", 299500, 99999),
                new PackageItemEntity(2344, "클래스", "일상 유튜버 슛뚜의 감성을 그대로. 영화같은 브이로그 제작법", 184500, 99999),
                new PackageItemEntity(60538, "키트", "시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전", 135800, 7),
                new PackageItemEntity(78156, "키트", "일상을 따뜻하게 채우는 썬캐쳐와 무드등", 45000, 16),
                new PackageItemEntity(53144, "클래스", "여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다", 249500, 99999),
                new PackageItemEntity(78311, "클래스", "사각사각 손글씨의 낭만, 펜크래프트의 한글 정자체 펜글씨", 209500, 99999),
                new PackageItemEntity(79166, "키트", "이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이 기획한 3가지 수채화 키트", 95900, 5),
                new PackageItemEntity(83791, "클래스", "월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문", 178500, 99999),
                new PackageItemEntity(58395, "키트", "선과 여백으로 채우는 2020년 캘린더와 엽서", 18620, 31),
                new PackageItemEntity(39712, "키트", "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8),
                new PackageItemEntity(96558, "클래스", "사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸", 234500, 99999),
                new PackageItemEntity(42031, "키트", "나만의 음악을 만들기 위한 장비 패키지", 209000, 2),
                new PackageItemEntity(45947, "클래스", "일러스트레이터 집시의 매력적인 얼굴 그리기", 249500, 99999),
                new PackageItemEntity(74218, "클래스", "나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기", 191600, 99999),
                new PackageItemEntity(28448, "클래스", "당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의 모든 것", 152200, 99999)
        );

        Storage.orders.clear();
    }
}
