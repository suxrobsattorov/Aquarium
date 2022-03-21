package ok.suxrob.service;

import ok.suxrob.enums.FishGender;
import ok.suxrob.model.Fish;
import ok.suxrob.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class FishService implements Aquarium {

    public static Random random = new Random();
    public static List<Fish> fishList = new ArrayList<>();
    public static boolean quit = false; // baliq o'lmagan
    public static boolean isShowFish = true; // baliqlarni ko'rsatish

    public static FishService fishService;

    public FishService() {
    }

    public static FishService getInstance() {
        if (Objects.isNull(fishService))
            fishService = new FishService();
        return fishService;
    }

    public void run() {

        int male = random.nextInt(100) + 1;
        int female = random.nextInt(100) + 1;

        for (int i = 0; i < male; i++) {
            Fish fishMale = randomFish();

            fishMale.setGender(FishGender.MALE);
            fishMale.setIndex(i);
            fishMale.start();
            fishList.add(fishMale);
        }
        for (int i = 0; i < female; i++) {
            Fish fishFemale = randomFish();

            fishFemale.setGender(FishGender.FEMALE);
            fishFemale.setIndex(male + i);
            fishFemale.start();
            fishList.add(fishFemale);
        }
        System.out.println("Akvariumda " + male + " ta erkak : va " + female + " ta urg'ochi baliq bor.");

    }

    // Tirik baliqlar soni
    public int liveFishCount() {
        int male = 0;
        int femalee = 0;
        for (Fish fish : fishList) {
            if (fish.getGender().equals(FishGender.MALE) && fish.getLifeCycle() > 0) {
                male++;
            } else if (fish.getGender().equals(FishGender.FEMALE) && fish.getLifeCycle() > 0) {
                femalee++;
            }
        }

        return male != 0 && femalee != 0 ? (male + femalee) : 0;

    }

    // Baliq dunyoga kelishi
    public void fishBorn() {
        if (!quit) {
            List<Fish> bornFishList = fishList;
            for (int i = 0; i < bornFishList.size(); i++) {
                for (int j = i + 1; j < bornFishList.size(); j++) {

                    if (bornFishList.get(i).getPointX().equals(bornFishList.get(j).getPointX())
                            && bornFishList.get(i).getPointY().equals(bornFishList.get(j).getPointY())
                            && bornFishList.get(i).getPointZ().equals(bornFishList.get(j).getPointZ())
                            && (bornFishList.get(i).getGender().equals(FishGender.MALE)
                            && bornFishList.get(j).getGender().equals(FishGender.FEMALE))
                            || (bornFishList.get(i).getGender().equals(FishGender.FEMALE)
                            && bornFishList.get(j).getGender().equals(FishGender.MALE))
                            && bornFishList.get(i).getLifeCycle() > 0 && bornFishList.get(j).getLifeCycle() > 0) {
                        if (!quit) {
                            System.out.println(bornFishList.get(i).getFishName() +
                                    (bornFishList.get(i).getGender().equals(FishGender.MALE) ?
                                            " erkak " : " urg'ochi ") +
                                    "va " + bornFishList.get(j).getFishName() +
                                    (bornFishList.get(j).getGender().equals(FishGender.MALE) ?
                                            " erkak " : " urg'ochi ") + " baliq uchrashdi ---->");

                            newFishBorn();

                        }
                    }
                }
            }
        }
    }

    public void newFishBorn() {

        boolean endFishBorn = false;
        int male = random.nextInt(20) + 1;
        int female = random.nextInt(20) + 1;
        if ((liveFishCount() + male + female) <= aquariumFishCount && !quit) {
            for (int i = 0; i < male; i++) {
                Fish fishMale = randomFish();
                fishMale.setGender(FishGender.MALE);
                System.out.println("Male -> " + fishMale.getFishName());
                fishMale.start();
                fishList.add(fishMale);
            }
            for (int i = 0; i < female; i++) {
                Fish fishFemale = randomFish();
                fishFemale.setGender(FishGender.FEMALE);
                System.out.println("Female -> " + fishFemale.getFishName());
                fishFemale.start();
                fishList.add(fishFemale);
            }
            System.out.println("----> Jami: " + male + " ta erkak, " + female + " ta urg'ochi baliq tug'ildi");
        } else {
            quit = true;
            endFishBorn = true;
            System.out.println("TUG'ILGAN BALIQLAR AKVARIUMGA SIG'ISHI UCHUN BO'SH JOY MAVJUD EMAS!");
            System.out.println(quit);
        }
        if (!endFishBorn || isShowFish) {
            showFish();
            isShowFish = true;
        }
    }

    public void showFish() {
        int male = 0;
        int female = 0;
        for (Fish fish : fishList) {
            if (fish.getGender().equals(FishGender.MALE) && fish.getLifeCycle() > 0) {
                male++;
            } else if (fish.getGender().equals(FishGender.FEMALE) && fish.getLifeCycle() > 0) {
                female++;
            }
        }
        System.out.println("******************************************************************************");
        System.out.println("* Akvariumda " + male + " ta erkak, " + female + " ta urg'ochi baliq mavjud. *");
        System.out.println("******************************************************************************");
    }

    // Randomda baliq yaratish
    public Fish randomFish() {
        Fish fish = new Fish();
        fish.setPointX(random.nextInt(aquariumLengthX) + 1);
        fish.setPointY(random.nextInt(aquariumLengthY) + 1);
        fish.setPointZ(random.nextInt(aquariumLengthZ) + 1);
        fish.setFishName(fishList.size() + 1 + "");
        fish.setLifeCycle(System.currentTimeMillis() + 10000);
        return fish;
    }
}
