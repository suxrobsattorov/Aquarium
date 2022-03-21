package ok.suxrob.model;

import lombok.*;
import ok.suxrob.enums.FishGender;
import ok.suxrob.service.FishService;

import static ok.suxrob.service.Aquarium.*;
import static ok.suxrob.service.FishService.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fish extends Thread {
    private String fishName;
    private FishGender gender;
    private Integer pointX;
    private Integer pointY;
    private Integer pointZ;
    private Long lifeCycle;
    private int index;

    @SneakyThrows
    @Override
    public void run() {
        while (System.currentTimeMillis() <= lifeCycle) {
            if (quit) {
                lifeCycle = 0L;
                break;
            } else {

                Thread.sleep(1000);

                fishList.get(index).setPointX(random.nextInt(aquariumLengthX) + 1);
                fishList.get(index).setPointY(random.nextInt(aquariumLengthY) + 1);
                fishList.get(index).setPointZ(random.nextInt(aquariumLengthZ) + 1);
            }
        }
        if (!quit) {
            System.out.println(fishList.get(index).getFishName() + " ismli " +
                    (fishList.get(index).getGender().equals(FishGender.MALE)
                            ? "erkak " : "urg'ochi ") + "baliq o'ldi !!!!");
            fishList.get(index).setLifeCycle(0L);
            FishService.getInstance().showFish();
        }
    }
}
