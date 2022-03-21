package ok.suxrob.utils;

import ok.suxrob.enums.FishGender;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    public static String nameRandom() {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        int targetStringLength = random.ints(3, 8).iterator().nextInt();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static FishGender genderRandom() {
        Random random = new Random();
        List<FishGender> genderList = new LinkedList<>();
        genderList.add(FishGender.MALE);
        genderList.add(FishGender.FEMALE);
        int index=random.ints(0, 2).iterator().nextInt();
        return genderList.get(index);
    }
}
